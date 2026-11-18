package com.yunhuang.eligibility.service;

import com.yunhuang.eligibility.model.Applicant;
import com.yunhuang.eligibility.model.ApplicantSearchDto;
import com.yunhuang.eligibility.repository.ApplicantRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicantService {
    private final ApplicantRepository applicantRepository;

    public ApplicantService(ApplicantRepository applicantRepository)
    {
        this.applicantRepository = applicantRepository;
    }

    public Applicant saveApplicant(Applicant applicant)
    {
        LocalDateTime currentDate = LocalDateTime.now();
        applicant.setCreatedDate(currentDate);
        applicant.setUpdatedDate(currentDate);
        return applicantRepository.save(applicant);
    }

    public Applicant updatedApplicant(BigInteger id, Applicant applicant)
    {
        Applicant updateObj = applicantRepository.findById(id).orElse(null);
        updateObj.setFirstName(applicant.getFirstName());
        updateObj.setLastName(applicant.getLastName());
        updateObj.setMiddleName(applicant.getMiddleName());
        updateObj.setSsnLast4(applicant.getSsnLast4());
        updateObj.setDateOfBirth(applicant.getDateOfBirth());
        updateObj.setEmail(applicant.getEmail());
        updateObj.setPhone(applicant.getPhone());
        updateObj.setUpdatedDate(LocalDateTime.now());
        return applicantRepository.save(updateObj);

    }

    public Applicant getApplicateById(BigInteger id)
    {
        return applicantRepository.findById(id).orElse(null);
    }

    public List<Applicant> getAllApplicants()
    {
        return applicantRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
    }

    public List<ApplicantSearchDto> getApplicantByNameByEmailByPhone(
            String firstName, String lastName, String email, String phone
    )
    {
        return applicantRepository.findApplicantsByNameByEmailByPhone(firstName, lastName, email, phone);
    }

}
