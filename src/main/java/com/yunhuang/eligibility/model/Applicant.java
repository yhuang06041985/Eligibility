package com.yunhuang.eligibility.model;


import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="Applicants")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger Id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String ssnLast4;
    private String phone;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public BigInteger getId()
    {
        return Id;
    }

    public void getId(BigInteger Id)
    {
        this.Id = Id;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setSsnLast4(String ssnLast4)
    {
        this.ssnLast4 = ssnLast4;
    }

    public String getSsnLast4()
    {
        return ssnLast4;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setCreatedDate(LocalDateTime createdDate)
    {
        this.createdDate = createdDate;
    }

    public LocalDateTime getCreatedDate()
    {
        return createdDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate)
    {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getUpdatedDate()
    {
        return updatedDate;
    }





}




