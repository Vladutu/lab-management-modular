package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author vladu
 */
@Entity
@Table(name = "GRADE")
@Getter
@Setter
public class Grade {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "VALUE", nullable = false)
    private Integer value;

    @Basic
    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "PNC", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "LABORATORY_ID", referencedColumnName = "ID", nullable = false)
    private Laboratory laboratory;
}
