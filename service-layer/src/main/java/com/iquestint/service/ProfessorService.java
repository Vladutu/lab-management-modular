package com.iquestint.service;

import com.iquestint.dto.*;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.ServiceInvalidSemesterException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author vladu
 */
public interface ProfessorService {

    LaboratoryWithStudentsDto getCurrentLaboratory(String professorPnc, LocalDateTime date)
        throws ServiceInvalidSemesterException, ServiceEntityNotFoundException;

    void saveStudentGrade(Integer laboratoryId, String studentPnc, GradeDto gradeDto)
        throws ServiceEntityAlreadyExistsException;

    void saveStudentAttendance(Integer laboratoryId, String studentPnc, AttendanceDto attendanceDto)
        throws ServiceEntityAlreadyExistsException;

    List<LaboratoryDto> getLaboratories(String professorPnc);

    List<StudentGradingDto> getStudentsWithGradesByLaboratory(int laboratoryId, LocalDate date);
}
