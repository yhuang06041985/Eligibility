package com.yunhuang.eligibility.service;

import com.yunhuang.eligibility.model.Address;
import com.yunhuang.eligibility.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    private AddressService(AddressRepository addressRepository)
    {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddressByApplicant(BigInteger applicantId)
    {
        return addressRepository.findByApplicant(applicantId);
    }

    public List<Address> getAllAddress()
    {
        return addressRepository.findAll();
    }

    public Address getAddressById(BigInteger addressId)
    {
        return addressRepository.findById(addressId).orElse(null);
    }

    public Address saveAddress(Address address)
    {
        address.setCreateDate(LocalDateTime.now());
        return addressRepository.save(address);
    }

    public Address updateAddress(BigInteger id, Address address)
    {
        Address updateObj = getAddressById(id);
        updateObj.setAddressType(address.getAddressType());
        updateObj.setApartment(address.getApartment());
        updateObj.setApplicant(address.getApplicant());
        updateObj.setCity(address.getCity());
        updateObj.setEndDate(address.getEndDate());
        updateObj.setIsCurrent(address.getIsCurrent());
        updateObj.setStartDate(address.getStartDate());
        updateObj.setStreetAddress(address.getStreetAddress());
        updateObj.setState(address.getState());
        return addressRepository.save(address);
    }
}
