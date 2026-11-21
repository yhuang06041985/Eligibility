package com.yunhuang.eligibility.repository;

import com.yunhuang.eligibility.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, BigInteger> {

    @Query(value = """
    SELECT  ic.id AS id,
            h.householdName AS householdName,
            CONCAT(ap.firstName, ' ', ap.lastName) AS memberName,
            ic.monthlyAmount AS monthlyAmount,
            ic.isActive AS isActive
    FROM IncomeRecords ic
    INNER JOIN HouseholdMembers hm
        ON ic.householdMemberId = hm.id
    INNER JOIN Households h
        ON hm.householdId = h.id
    INNER JOIN Applicants ap
        ON hm.applicantId = ap.id
    WHERE (:householdName IS NULL OR h.householdName LIKE CONCAT('%', :householdName, '%'))
      AND (:firstName IS NULL OR ap.firstName LIKE CONCAT('%', :firstName, '%'))
      AND (:lastName IS NULL OR ap.lastName LIKE CONCAT('%', :lastName, '%'))
    """, nativeQuery = true)
    List<SearchIncomeProjection> findIncomeByHouseholdByApplicant(
            @Param("householdName") String householdName,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName
    );

    @Query(value = """
                SELECT  ic.id AS id,
                h.householdName AS householdName,
                CONCAT(ap.firstName, ' ', ap.lastName) AS memberName,
                ic.employerName as employerName,
                ic.grossAmount as grossAmount,
                ic.payFrequency as payFrequency,
                ic.monthlyAmount AS monthlyAmount,
                ic.isActive AS isActive,
                ic.startDate as startDate,
                ic.endDate as endDate,
                ic.createdDate as createdDate
        FROM IncomeRecords ic
        INNER JOIN HouseholdMembers hm
            ON ic.householdMemberId = hm.id
        INNER JOIN Households h
            ON hm.householdId = h.id
        INNER JOIN Applicants ap
            ON hm.applicantId = ap.id
        Where ic.id= :id
  """, nativeQuery = true)
    IncomeProjection findIncomeById(@Param("id") BigInteger id);

}
