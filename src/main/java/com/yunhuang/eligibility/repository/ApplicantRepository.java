package com.yunhuang.eligibility.repository;

import com.yunhuang.eligibility.model.Applicant;
import com.yunhuang.eligibility.model.ApplicantSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, BigInteger> {

@Query(value= """
    Select a.Id as Id,
           a.firstName + ' ' + a.lastName as fullName
    from applicants a
    WHERE (:firstName is null or a.firstName like '%' + :firstName + '%')
      AND (:lastName is null or a.lastName like '%' + :lastName + '%')
      And (:email is null or a.email like '%' + :email + '%')
      And (:phone is null or a.phone like '%' + :phone + '%')
    
""", nativeQuery = true)
List<ApplicantSearchDto> findApplicantsByNameByEmailByPhone(
            String firstName, String lastName, String email, String phone);

}
