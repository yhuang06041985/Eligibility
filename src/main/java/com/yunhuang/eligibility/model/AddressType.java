package com.yunhuang.eligibility.model;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name="addresstypes")
public class AddressType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String typeName;

    public Integer getId()
    {
        return Id;
    }

    public void setId(Integer Id)
    {
        this.Id = Id;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }

}
