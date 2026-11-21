package com.yunhuang.eligibility.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="incomerecords")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger Id;
    @ManyToOne
    @JoinColumn(name = "householdMemberId", nullable = false)
    private Member member;
    private String incomeType;
    private String employerName;
    private BigDecimal grossAmount;
    private BigDecimal monthlyAmount;
    private String payFrequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    private LocalDateTime createdDate;

    public void setId(BigInteger Id)
    {
        this.Id = Id;
    }

    public BigInteger getId()
    {
        return Id;
    }

    public void setMember(Member member)
    {
        this.member = member;
    }

    public Member getMember()
    {
        return member;
    }

    public void setIncomeType(String incomeType)
    {
        this.incomeType = incomeType;
    }

    public String getIncomeType()
    {
        return incomeType;
    }

    public void setEmployerName(String employerName)
    {
        this.employerName = employerName;
    }

    public String getEmployerName()
    {
        return employerName;
    }

    public void setGrossAmount(BigDecimal grossAmount)
    {
        this.grossAmount = grossAmount;
    }

    public BigDecimal getGrossAmount()
    {
        return grossAmount;
    }

    public void setMonthlyAmount(BigDecimal monthlyAmount)
    {
        this.monthlyAmount = monthlyAmount;
    }

    public BigDecimal getMonthlyAmount()
    {
        return monthlyAmount;
    }

    public void setPayFrequency(String payFrequency)
    {
        this.payFrequency = payFrequency;
    }

    public String getPayFrequency()
    {
        return  payFrequency;
    }

    public void setIsActive(boolean isActive)
    {
        this.isActive = isActive;
    }

    public boolean getIsActive()
    {
        return isActive;
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

    public void setCreatedDate(LocalDateTime createdDate)
    {
        this.createdDate = createdDate;
    }

    public LocalDateTime getCreatedDate()
    {
        return createdDate;
    }
}












