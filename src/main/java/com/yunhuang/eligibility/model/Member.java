package com.yunhuang.eligibility.model;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="householdmembers")
public class HouseholdMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger Id;
    @ManyToOne
    @JoinColumn(name = "applicantId", nullable = false)
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name="householdId", nullable = false)
    private HouseHold houseHold;
    private String relationship;
    private boolean isPrimaryApplicant;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private void setId(BigInteger Id)
    {
        this.Id = Id;
    }

    public BigInteger getId() {
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

    public void setHouseHold(HouseHold houseHold)
    {
        this.houseHold = houseHold;
    }

    public HouseHold getHouseHold()
    {
        return houseHold;
    }

    public void setRelationship(String relationship)
    {
        this.relationship = relationship;
    }

    public String getRelationship()
    {
        return relationship;
    }

    public void setisPrimaryApplican(boolean isPrimaryApplicant)
    {
        this.isPrimaryApplicant = isPrimaryApplicant;
    }

    public boolean getisPrimaryApplican()
    {
        return isPrimaryApplicant;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setCreatedDate(LocalDateTime createdDate)
    {
        this.createdDate = createdDate;
    }

    public LocalDateTime getCreatedDate()
    {
        return createdDate;
    }
}
