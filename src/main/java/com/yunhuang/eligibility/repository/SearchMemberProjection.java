package com.yunhuang.eligibility.repository;

import java.time.LocalDate;

public interface SearchMemberProjection {
    Long getId();
    String getHouseholdName();
    String getFullName();
    String getRelationship();
    Boolean getIsPrimaryApplicant();
    LocalDate getStartDate();
    LocalDate getEndDate();
}
