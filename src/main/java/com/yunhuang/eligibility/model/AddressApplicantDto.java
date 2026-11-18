package com.yunhuang.eligibility.model;

public class AddressApplicantDto {
    private long Id;
    private String fullName;
    private String addressType;
    private String streetName;
    private String city;
    private String state;
    private String zipCode;

    public AddressApplicantDto(long Id, String fullName, String addressType,
          String streetName, String city, String state, String zipCode)
    {
        this.Id = Id;
        this.fullName = fullName;
        this.addressType = addressType;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public long getId()
    {
        return Id;
    }

    public String getFullName()
    {
        return fullName;
    }

    public String getAddressType()
    {
        return addressType;
    }

    public String getStreetName(){
        return streetName;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }

    public String getZipCode()
    {
        return zipCode;
    }


}
