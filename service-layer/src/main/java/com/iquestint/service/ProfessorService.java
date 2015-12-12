package com.iquestint.service;

import com.iquestint.dto.*;
import com.iquestint.exception.ServiceEntityAlreadyExistsException;
import com.iquestint.exception.ServiceEntityNotFoundException;
import com.iquestint.exception.ServiceInvalidSemesterException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This interfaces provides methods that are accessible by a professor.
 *
 * @author Georgian Vladutu
 */
public interface ProfessorService {

    /**
     * Returns the laboratory and the corresponding students given it's professor pnc and when does it take place.
     *
     * @param professorPnc pnc of the professor
     * @param date         date and time when the laboratory take place
     * @return LaboratoryWithStudentsDto
     * @throws ServiceInvalidSemesterException if the date is not part of a university semester
     * @throws ServiceEntityNotFoundException  if the laboratory is not found in the repository
     */
    LaboratoryWithStudentsDto getCurrentLaboratory(String professorPnc, LocalDateTime date)
        throws ServiceInvalidSemesterException, ServiceEntityNotFoundException;

    /**
     * Saves a student grade into the repository.
     *
     * @param laboratoryId id of the laboratory on which the student takes the grade
     * @param studentPnc   pnc of the student
     * @param gradeDto     grade which the student takes
     * @throws ServiceEntityAlreadyExistsException if the grade already exists in the repository
     */
    void saveStudentGrade(Integer laboratoryId, String studentPnc, GradeDto gradeDto)
        throws ServiceEntityAlreadyExistsException;

    /**
     * Saves a student attendance into the repository.
     *
     * @param laboratoryId  id of the laboratory on which the student takes the attendace
     * @param studentPnc    pnc of the student
     * @param attendanceDto student's attendance
     * @throws ServiceEntityAlreadyExistsException if the attendance already exists
     */
    void saveStudentAttendance(Integer laboratoryId, String studentPnc, AttendanceDto attendanceDto)
        throws ServiceEntityAlreadyExistsException;

    /**
     * Returns all laboratories which belong to the professor whose pnc is studentPnc.
     *
     * @param professorPnc pnc of the professor
     * @return List<LaboratoryDto>
     */
    List<LaboratoryDto> getLaboratories(String professorPnc);

    /**
     * Returns the students and their grades given a laboratory and the date it take place.
     *
     * @param laboratoryId laboratory id
     * @param date         date on which the laboratory take place
     * @return List<StudentGradingDto>
     */
    List<StudentGradingDto> getStudentsWithGradesByLaboratory(int laboratoryId, LocalDate date);
}
