package com.yunhuang.eligibility.repository;

import com.yunhuang.eligibility.model.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressTypeRepository extends JpaRepository<AddressType, Integer> {

}
