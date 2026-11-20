package com.yunhuang.eligibility.model;

import java.math.BigInteger;
import java.time.LocalDate;

public class SearchMemberDto {

    private Long id;
    private String householdName;
    private String fullName;
    private String relationship;
    private Boolean isPrimaryApplicant;
    private LocalDate startDate;

    public SearchMemberDto(Long id, String householdName
    , String fullName, String relationship, Boolean isPrimaryApplicant, LocalDate startDate)
    {
        this.id = id;
        this.householdName = householdName;
        this.fullName = fullName;
        this.relationship = relationship;
        this.isPrimaryApplicant = isPrimaryApplicant;
        this.startDate = startDate;
    }

    public Long getId()
    {
        return id;
    }

    public String getHouseholdName()
    {
        return householdName;
    }

    public String getFullName()
    {
        return fullName;
    }

    public String getRelationship()
    {
        return relationship;
    }

    public Boolean getIsPrimaryApplicant()
    {
        return isPrimaryApplicant;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }
}


