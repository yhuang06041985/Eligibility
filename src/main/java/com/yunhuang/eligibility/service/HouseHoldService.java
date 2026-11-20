package com.yunhuang.eligibility.service;

import com.yunhuang.eligibility.model.Applicant;
import com.yunhuang.eligibility.model.HouseHold;
import com.yunhuang.eligibility.repository.HouseholdRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class HouseHoldService {
    private final HouseholdRepository householdRepository;
    private final ApplicantService applicantService;

    public HouseHoldService(HouseholdRepository householdRepository,
                            ApplicantService applicantService)
    {
        this.householdRepository = householdRepository;
        this.applicantService = applicantService;
    }

    public List<HouseHold> getAllHousehold()
    {
        return householdRepository.findAll();
    }

    public List<HouseHold> getHouseholdByIdByName(BigInteger householdId, String householdName)
    {
        return householdRepository.findHouseholdByIdByName(householdId, householdName);
    }

    public HouseHold getHouseholdById(BigInteger householdId)
    {
        return householdRepository.findById(householdId).orElse(null);
    }

    public HouseHold saveHousehold(BigInteger applicantId, HouseHold houseHold)
    {
        Applicant applicant = applicantService.getApplicateById(applicantId);
        houseHold.setApplicant(applicant);
        houseHold.setCreatedDate(LocalDateTime.now());
        houseHold.setUpdatedDate(LocalDateTime.now());
        return householdRepository.save(houseHold);
    }

    public HouseHold updateHousehold(HouseHold houseHold)
    {
        HouseHold updateObj = getHouseholdById(houseHold.getId());
        updateObj.setApplicant(houseHold.getApplicant());
        updateObj.setUpdatedDate(LocalDateTime.now());
        return householdRepository.save(updateObj);
    }

    public void deteteHousehold(BigInteger householdid)
    {
        HouseHold deleteObj = getHouseholdById(householdid);
        if (deleteObj != null)
            householdRepository.delete(deleteObj);
        return;
    }

}
