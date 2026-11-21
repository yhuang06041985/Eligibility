package com.yunhuang.eligibility.repository;

import java.math.BigDecimal;

public interface SearchIncomeProjection {
    Long getId();
    String getMemberName();
    String getHouseholdName();
    BigDecimal getMonthlyAmount();
    Boolean getIsActive();
}
