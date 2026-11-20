package com.yunhuang.eligibility.model;

import java.time.LocalDate;

public class SearchMemberDto {

    private String householdName;
    private String fullName;
    private String relationship;
    private boolean isPrimaryApplicant;
    private LocalDate startDate;

    public void setHouseholdName(String householdName)
    {
        this.householdName = householdName;
    }

    public String getHouseholdName()
    {
        return householdName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setRelationship(String relationship)
    {
        this.relationship = relationship;
    }

    public String getRelationship()
    {
        return relationship;
    }

    public void setIsPrimaryApplicant(boolean isPrimaryApplicant)
    {
        this.isPrimaryApplicant = isPrimaryApplicant;
    }

    public boolean getIsPrimaryApplicant()
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
}


