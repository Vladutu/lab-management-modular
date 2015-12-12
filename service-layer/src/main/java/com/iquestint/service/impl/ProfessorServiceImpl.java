package com.iquestint.service.impl;

import com.iquestint.dao.AttendanceDao;
import com.iquestint.dao.GradeDao;
import com.iquestint.dao.LaboratoryDao;
import com.iquestint.dao.StudentDao;
import com.iquestint.dto.*;
import com.iquestint.exception.*;
import com.iquestint.model.*;
import com.iquestint.service.ProfessorService;
import com.iquestint.utils.WeekCalculator;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class implements the ProfessorService interface.
 *
 * @author Georgian Vladutu
 */
@Service("professorService")
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private LaboratoryDao laboratoryDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private WeekCalculator weekCalculator;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LaboratoryWithStudentsDto getCurrentLaboratory(String professorPnc, LocalDateTime date)
        throws ServiceInvalidSemesterException, ServiceEntityNotFoundException {
        Laboratory laboratory = getLaboratory(professorPnc, date);
        List<Student> students = studentDao.getStudents(laboratory);

        LaboratoryWithStudentsDto laboratoryWithStudentsDto = new LaboratoryWithStudentsDto();
        laboratoryWithStudentsDto.setLaboratory(modelMapper.map(laboratory, LaboratoryDto.class));
        laboratoryWithStudentsDto.setStudents(modelMapper.map(students, new TypeToken<List<StudentDto>>() {
        }.getType()));

        return laboratoryWithStudentsDto;
    }

    @Override
    public void saveStudentGrade(Integer laboratoryId, String studentPnc, GradeDto gradeDto)
        throws ServiceEntityAlreadyExistsException {
        Laboratory laboratory = null;
        Student student = null;
        try {
            student = studentDao.getStudentByPnc(studentPnc);
            laboratory = laboratoryDao.getLaboratoryById(laboratoryId);
        } catch (DaoEntityNotFoundException ignored) {
        }

        Grade grade = modelMapper.map(gradeDto, Grade.class);
        grade.setLaboratory(laboratory);
        grade.setStudent(student);

        try {
            gradeDao.saveGrade(grade);
        } catch (DaoEntityAlreadyExists e) {
            throw new ServiceEntityAlreadyExistsException(e);
        }

    }

    @Override
    public void saveStudentAttendance(Integer laboratoryId, String studentPnc, AttendanceDto attendanceDto)
        throws ServiceEntityAlreadyExistsException {
        Laboratory laboratory = null;
        Student student = null;
        try {
            student = studentDao.getStudentByPnc(studentPnc);
            laboratory = laboratoryDao.getLaboratoryById(laboratoryId);
        } catch (DaoEntityNotFoundException ignored) {
        }

        Attendance attendance = modelMapper.map(attendanceDto, Attendance.class);
        attendance.setLaboratory(laboratory);
        attendance.setStudent(student);

        try {
            attendanceDao.saveAttendance(attendance);
        } catch (DaoEntityAlreadyExists e) {
            throw new ServiceEntityAlreadyExistsException(e);
        }

    }

    @Override
    public List<LaboratoryDto> getLaboratories(String professorPnc) {
        List<Laboratory> laboratories = laboratoryDao.getLaboratoriesByProfessor(professorPnc);

        return modelMapper.map(laboratories, new TypeToken<List<LaboratoryDto>>() {
        }.getType());
    }

    @Override
    public List<StudentGradingDto> getStudentsWithGradesByLaboratory(int laboratoryId, LocalDate date) {
        List<StudentGradingDto> studentGradings = new ArrayList<>();
        Laboratory laboratory = new Laboratory();
        laboratory.setId(laboratoryId);

        List<Student> students = studentDao.getStudents(laboratory);
        for (Student student : students) {
            List<Grade> grades = student.getGrades();
            StudentGradingDto studentGrading = modelMapper.map(student, StudentGradingDto.class);

            Optional<Grade> matchGrade = grades.stream().filter(grade -> grade.getDate().equals(date)).findFirst();
            if (matchGrade.isPresent()) {
                studentGrading.setGrade(matchGrade.get().getValue().toString());
            }
            else {
                studentGrading.setGrade("N/A");
            }

            studentGradings.add(studentGrading);
        }

        return studentGradings;
    }

    private Laboratory getLaboratory(String professorPnc, LocalDateTime date)
        throws ServiceInvalidSemesterException, ServiceEntityNotFoundException {
        Hour from = new Hour(date.getHour() % 2 == 1 ? date.getHour() - 1 : date.getHour());
        Day day = new Day(date.getDayOfWeek().getValue());
        int week = weekCalculator.getWeek(date.toLocalDate());

        List<Laboratory> laboratories = laboratoryDao.getLaboratoriesByDateAndTime(professorPnc, from, day);

        if (laboratories.isEmpty()) {
            throw new ServiceEntityNotFoundException();
        }
        else if (laboratories.size() == 1) {
            return laboratories.get(0);
        }

        Laboratory first = laboratories.get(0);
        Laboratory second = laboratories.get(1);

        if (week % 2 == 0) {
            if (first.getWeeklyOccurrence().getName().equals("Even Week")) {
                return first;
            }
            else {
                return second;
            }
        }
        else {
            if (first.getWeeklyOccurrence().getName().equals("Odd Week")) {
                return first;
            }
            else {
                return second;
            }
        }
    }
}
