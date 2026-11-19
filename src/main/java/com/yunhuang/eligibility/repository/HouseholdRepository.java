package com.yunhuang.eligibility.repository;

import com.yunhuang.eligibility.model.HouseHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface HouseholdRepository extends JpaRepository<HouseHold, BigInteger>
{
    @Query(value= """
    Select *
    from Households
    where (:householdName is null Or householdName like '%' + :householdName + '%'
""", nativeQuery = true)
    List<HouseHold> findHouseholdByName(String householdName);
}
