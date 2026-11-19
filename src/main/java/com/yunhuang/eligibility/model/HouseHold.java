package com.yunhuang.eligibility.model;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name="houseHolds")
public class HouseHold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger Id;
    @ManyToOne
    @JoinColumn(name = "primaryApplicantId", nullable = false)
    private Applicant applicant;
    private String householdName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public void setId(BigInteger Id)
    {
        this.Id = Id;
    }

    public BigInteger getId()
    {
        return Id;
    }

    public void setApplicant(Applicant applicant)
    {
        this.applicant = applicant;
    }

    public Applicant getApplicant()
    {
        return applicant;

    }

    public void setHouseoldName(String householdName)
    {
        this.householdName = householdName;
    }

    public String getHouseholdName()
    {
        return householdName;
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
