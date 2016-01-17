package com.iquestint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * This is an model entity that maps the DOCUMENT table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "DOCUMENT")
@Getter
@Setter
public class Document {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    @Column(name = "NAME", nullable = false)
    @Size(min = 3, max = 100)
    private String name;

    @Basic
    @Column(name = "TYPE", nullable = false)
    @Size(min = 3, max = 100)
    private String type;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "CONTENT", nullable = false)
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "LABORATORY_ID", referencedColumnName = "ID", nullable = false)
    private Laboratory laboratory;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Document)) {
            return false;
        }

        Document document = (Document) o;

        if (id != document.id) {
            return false;
        }
        if (name != null ? !name.equals(document.name) : document.name != null) {
            return false;
        }
        return !(type != null ? !type.equals(document.type) : document.type != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
