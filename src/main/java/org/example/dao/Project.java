package org.example.dao;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "projects")
public class Project implements Serializable {
    public Project() {
    }

    public Project(String name, Date cteationDate) {
        this.name = name;
        this.cteationDate = cteationDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;
    private Date cteationDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCteationDate() {
        return cteationDate;
    }

    public void setCteationDate(Date cteationDate) {
        this.cteationDate = cteationDate;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cteationDate=" + cteationDate +
                '}';
    }
}
