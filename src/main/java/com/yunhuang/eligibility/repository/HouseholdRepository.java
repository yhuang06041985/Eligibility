package com.yunhuang.eligibility.repository;

import com.yunhuang.eligibility.model.HouseHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface HouseholdRepository extends JpaRepository<HouseHold, BigInteger> {

    @Query(value = """
        SELECT *
        FROM Households
        WHERE (:householdName IS NULL
               OR householdName LIKE '%' + :householdName + '%')
          AND (:householdId IS NULL
               OR id = :householdId)
        """, nativeQuery = true)
    List<HouseHold> findHouseholdByIdByName(
            @Param("householdId") BigInteger householdId,
            @Param("householdName") String householdName);
}