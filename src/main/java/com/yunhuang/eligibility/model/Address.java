package com.yunhuang.eligibility.model;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="Addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger Id;
    @ManyToOne
    @JoinColumn(name = "applicantId", nullable = false)
    private Applicant applicant;
    private String addressType;
    private String streetAddress;
    private String apartment;
    private String city;
    private String state;
    private String zipCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCurrent;
    private LocalDateTime createDate;

    public void setId(BigInteger Ud)
    {
        this.Id = Id;
    }

    public BigInteger getId()
    {
        return Id;
    }

    public void setApplicant(Applicant applicant)
    {
        this.applicant = applicant;
    }

    public Applicant getApplicant()
    {
        return applicant;
    }

    public void setAddressType(String addressType)
    {
        this.addressType = addressType;
    }

    public String getAddressType()
    {
        return addressType;
    }

    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress()
    {
        return streetAddress;
    }

    public void setApartment(String apartment)
    {
        this.apartment = apartment;
    }

    public String getApartment()
    {
        return apartment;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    public void getZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setIsCurrent(boolean isCurrent)
    {
        this.isCurrent = isCurrent;
    }

    public boolean getIsCurrent()
    {
        return isCurrent;
    }

    public void setCreateDate(LocalDateTime createDate)
    {
        this.createDate = createDate;
    }

    public LocalDateTime getCreateDate()
    {
        return createDate;
    }
}


