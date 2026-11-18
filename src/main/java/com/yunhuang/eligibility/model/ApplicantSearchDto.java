package com.yunhuang.eligibility.model;

import java.math.BigInteger;

public class ApplicantSearchDto {
    private long  applicantId;
    private String fullName;

    public ApplicantSearchDto(long applicantId, String fullName)
    {
        this.applicantId = applicantId;
        this.fullName = fullName;
    }

    public long getApplicantId()
    {
        return applicantId;
    }

    public String getFullName()
    {
        return fullName;
    }
}

