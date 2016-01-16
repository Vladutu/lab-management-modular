package com.iquestint.service.impl;

import com.iquestint.dao.AttendanceDao;
import com.iquestint.dao.GradeDao;
import com.iquestint.dao.LaboratoryDao;
import com.iquestint.dto.AttendanceDto;
import com.iquestint.dto.GradeDto;
import com.iquestint.dto.LaboratoryDto;
import com.iquestint.model.Attendance;
import com.iquestint.model.Grade;
import com.iquestint.model.Laboratory;
import com.iquestint.service.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vladu
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private LaboratoryDao laboratoryDao;

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LaboratoryDto> getStudentLaboratories(String studentPnc) {
        List<Laboratory> laboratories = laboratoryDao.getLaboratoriesByStudent(studentPnc);

        return modelMapper.map(laboratories, new TypeToken<List<LaboratoryDto>>() {
        }.getType());
    }

    @Override
    public List<GradeDto> getStudentGradesByLaboratory(String studentPnc, int laboratoryId) {
        List<Grade> grades = gradeDao.getStudentGradesByLaboratory(studentPnc, laboratoryId);

        return modelMapper.map(grades, new TypeToken<List<GradeDto>>() {
        }.getType());
    }

    @Override
    public List<AttendanceDto> getStudentAttendancesByLaboratory(String studentPnc, int laboratoryId) {
        List<Attendance> attendances = attendanceDao.getStudentAttendancesByLaboratory(studentPnc, laboratoryId);

        return modelMapper.map(attendances, new TypeToken<List<AttendanceDto>>() {
        }.getType());
    }
}
