package com.yunhuang.eligibility.repository;

import com.yunhuang.eligibility.model.Member;
import com.yunhuang.eligibility.model.SearchMemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, BigInteger> {

    @Query(value= """
     Select  hm.id as id,
             h.householdName as householdName,
            CONCAT(ap.firstName, ' ', ap.lastName) as fullName,
            hm.relationship as relationship,
            hm.isPrimaryApplicant as isPrimaryApplicant,
            hm.startDate as startDate,
            hm.endDate as endDate
            
     from HouseholdMembers hm
     Inner Join Households h
     On hm.householdId = h.id
     Inner join Applicants ap
     On hm.applicantId = ap.id
     Where (:householdName is null Or h.householdName like CONCAT('%', :householdName, '%') )
       and (:firstName is null or ap.firstName like CONCAT('%', :firstName, '%') )
       and (:lastName is null OR ap.lastName like CONCAT('%', :lastName, '%') )
""", nativeQuery = true)
    List<SearchMemberProjection> findMemberByhouseholdNameByApplicantName(
            @Param("householdName") String householdName,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName);

    @Query(value= """
     Select  hm.id as id,
             h.householdName as householdName,
            CONCAT(ap.firstName, ' ', ap.lastName) as fullName,
            hm.relationship as relationship,
            hm.isPrimaryApplicant as isPrimaryApplicant,
            hm.startDate as startDate,
            hm.endDate as endDate        
     from HouseholdMembers hm
     Inner Join Households h
     On hm.householdId = h.id
     Inner join Applicants ap
     On hm.applicantId = ap.id
     Where hm.id=:id
""", nativeQuery = true)
    SearchMemberProjection findMemberById(
            @Param("id") BigInteger id);

    @Query(value= """
      Select  hm.id as id,
             h.householdName as householdName,
            CONCAT(ap.firstName, ' ', ap.lastName) as fullName,
            hm.relationship as relationship,
            hm.isPrimaryApplicant as isPrimaryApplicant,
            hm.startDate as startDate,
            hm.endDate as endDate  
      from HouseholdMembers hm
      INNER JOIN Households h
      on hm.householdId = h.id
      Inner Join Applicants ap
      On hm.applicantId = ap.id
      Where (:householdName is null OR h.householdName like CONCAT('%', :householdName, '%') )
        AND (:firstName is null OR ap.firstName like CONCAT('%', :firstName, '%') )
        And (:lastName is null OR ap.lastName like CONCAT('%', :lastName, '%') )
        """, nativeQuery = true)
        List<SearchMemberProjection> findMemberByHouseholdByApplicant(
          @Param("householdName") String householdName,
          @Param("firstName") String firstName,
          @Param("lastName") String lastName
        );

}

