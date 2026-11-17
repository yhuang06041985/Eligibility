package com.yunhuang.eligibility.repository;

import com.yunhuang.eligibility.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, BigInteger> {

    List<Address> findByApplicant(BigInteger applicantId);
}
