package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "name")
    @NotEmpty(message = "*Please enter the students name!")
    private String name;

    @Column(name = "birthdate")
    @NotEmpty(message = "*Please enter the students birthdate!")
    private String birthDate;

    @Column(name = "address")
    @NotEmpty(message = "*Please enter the students address!")
    private String address;

    @Column(name = "phone", unique = true)
    @NotEmpty(message = "*Please enter the students phone number!")
    private String phone;

    @Column(name = "program")
    @NotEmpty(message = "*Please enter the students program!")
    private String program;

    public String toString() {
        return
                "ID: " + id + "\n" +
                        "Name: " + name + "\n" +
                        "Birthdate: " + birthDate + "\n" +
                        "Address: " + address + "\n" +
                        "Phone: " + phone + "\n" +
                        "Program: " + program + "\n"
                ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
