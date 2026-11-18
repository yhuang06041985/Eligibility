package com.yunhuang.eligibility.repository;

import com.yunhuang.eligibility.model.Address;
import com.yunhuang.eligibility.model.AddressApplicantDto;
import com.yunhuang.eligibility.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, BigInteger> {

    List<Address> findByApplicant(Applicant applicant);

    @Query(value= """
         Select a.Id as Id,
                p.firstName + ' ' + p.lastName as fullName,
                t.typeName as typeName,
                a.streetAddress as streetAddress,
                a.city as city,
                a.state as state,
                a.zipCode as zipCode
         From Addresses a
         Inner Join Applicants p
         on a.applicantId = p.id
         inner join AddressTypes t
         on a.addressTypeId = t.id
         Where (:firstName is null or p.firstName like '%' + :firstName + '%')
             AND (:lastName is null or p.lastName like '%' + :lastName + '%')
             And (:email is null or p.email like '%' + :email + '%')
             And (:phone is null or p.phone like '%' + :phone + '%')
        
""", nativeQuery = true)
    List<AddressApplicantDto> findAddressAndApplicant(String firstName, String lastName,
                                                      String email, String phone);
}
