package com.yunhuang.eligibility.service;

import com.yunhuang.eligibility.model.Address;
import com.yunhuang.eligibility.model.AddressApplicantDto;
import com.yunhuang.eligibility.model.Applicant;
import com.yunhuang.eligibility.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ApplicantService applicantService;

    private AddressService(AddressRepository addressRepository, ApplicantService applicantService)
    {
        this.addressRepository = addressRepository;
        this.applicantService = applicantService;
    }

    public List<Address> getAddressByApplicant(Applicant applicant)
    {
        return addressRepository.findByApplicant(applicant);
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
        address.setCreatedDate(LocalDateTime.now());
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
        updateObj.setAddressType(address.getAddressType());
        return addressRepository.save(address);
    }

    public List<AddressApplicantDto> getAddressApplicant(String firstName,
         String lastName, String email, String phone)
    {
        return addressRepository.findAddressAndApplicant(firstName, lastName, email, phone);
    }
}