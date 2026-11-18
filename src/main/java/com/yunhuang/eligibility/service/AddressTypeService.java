package com.yunhuang.eligibility.service;

import com.yunhuang.eligibility.model.AddressType;
import com.yunhuang.eligibility.repository.AddressTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressTypeService {

    private final AddressTypeRepository addressTypeRepository;

    public AddressTypeService(AddressTypeRepository addressTypeRepository) {
        this.addressTypeRepository = addressTypeRepository;
    }

    public List<AddressType> getAddressType() {
        return addressTypeRepository.findAll();
    }
}