package com.iquestint.utils;

import com.iquestint.exception.ServiceInvalidSemesterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * @author vladu
 */
@Component
public class WeekCalculator {

    @Autowired
    private Environment environment;

    private static final int FIRST_SEMESTER = 1;
    private static final int SECOND_SEMESTER = 2;
    private static final int NO_DAYS_IN_WEEK = 7;

    public int getWeek(LocalDate date) throws ServiceInvalidSemesterException {
        int firstSemesterYear = Integer.parseInt(environment.getRequiredProperty("first.year"));
        Month firstSemesterMonth = Month.valueOf(environment.getRequiredProperty("first.month"));
        int firstSemesterDay = Integer.parseInt(environment.getRequiredProperty("first.day"));
        int secondSemesterYear = Integer.parseInt(environment.getRequiredProperty("second.year"));
        Month secondSemesterMonth = Month.valueOf(environment.getRequiredProperty("second.month"));
        int secondSemesterDay = Integer.parseInt(environment.getRequiredProperty("second.day"));

        LocalDate firstSemesterStart = LocalDate.of(firstSemesterYear, firstSemesterMonth, firstSemesterDay);
        LocalDate secondSemesterStart = LocalDate.of(secondSemesterYear, secondSemesterMonth, secondSemesterDay);

        int semester = getSemester(date, firstSemesterStart, secondSemesterStart);

        if (semester == FIRST_SEMESTER) {
            return calculateWeek(date, firstSemesterStart);
        }
        else {
            return calculateWeek(date, secondSemesterStart);
        }

    }

    private int calculateWeek(LocalDate date, LocalDate semesterStart) {
        int week = 1;

        if (!(semesterStart.isEqual(date) || semesterStart.getDayOfWeek().equals(DayOfWeek.MONDAY))) {
            while (!(semesterStart.isEqual(date) || semesterStart.getDayOfWeek().equals(DayOfWeek.MONDAY))) {
                semesterStart = semesterStart.plusDays(1);
            }

            week++;
        }

        while (semesterStart.isBefore(date) && ChronoUnit.DAYS.between(semesterStart, date) >= NO_DAYS_IN_WEEK) {
            semesterStart = semesterStart.plusDays(NO_DAYS_IN_WEEK);
            week++;
        }

        return week;
    }

    private int getSemester(LocalDate date, LocalDate firstSemesterStart, LocalDate secondSemesterStart)
        throws ServiceInvalidSemesterException {
        if (date.isEqual(firstSemesterStart) ||
            (date.isAfter(firstSemesterStart) && date.isBefore(secondSemesterStart))) {
            return FIRST_SEMESTER;
        }
        else if (date.isEqual(firstSemesterStart) || date.isAfter(secondSemesterStart)) {
            return SECOND_SEMESTER;
        }

        throw new ServiceInvalidSemesterException();
    }
}
