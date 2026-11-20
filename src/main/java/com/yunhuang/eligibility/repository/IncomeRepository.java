package com.yunhuang.eligibility.repository;

import com.yunhuang.eligibility.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface IncomeRepository extends JpaRepository<Income, BigInteger> {
}
