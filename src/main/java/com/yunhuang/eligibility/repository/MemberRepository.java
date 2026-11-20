package com.yunhuang.eligibility.repository;

import com.yunhuang.eligibility.model.Member;
import com.yunhuang.eligibility.model.SearchMemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, BigInteger> {

    @Query(value= """
     Select  hm.id as id,
             h.householdName as householdName,
            CONCAT(ap.firstName, ' ',ap.lastName) as appFullName,
            hm.relationship as relationship,
            hm.isPrimaryApplicant as isPrimaryApplicant,
            hm.startDate as startDate
            
     from HouseholdMembers hm
     Inner Join Households h
     On hm.householdId = h.id
     Inner join Applicants ap
     On hm.applicantId = ap.id
     Where (:householdname is null Or h.householdName like CONCAT('%', :householdName, '%') )
       and (:firstName is null or ap.firstName like CONCAT('%', :firstName, '%') )
       and (:lastName is null OR ap.lastName like CONCAT('%', :lastName, '%') )
""", nativeQuery = true)
    List<SearchMemberDto> findMemberByhouseholdNameByApplicantName(String householdName,
                                                                   String firstName, String lastName);
}
