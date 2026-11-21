package com.yunhuang.eligibility.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IncomeProjection {
    Long getId();
    String getHouseholdName();
    String getMemberName();
    String getEmployerName();
    BigDecimal getGrossAmount();
    String PayFrequency();
    BigDecimal getMonthlyAmount();
    Boolean getIsActive();
    LocalDate getStartDate();
    LocalDate getEndDate();
    LocalDateTime getCreatedDate();
}
