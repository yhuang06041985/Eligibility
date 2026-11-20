package com.yunhuang.eligibility.service;

import com.yunhuang.eligibility.model.Applicant;
import com.yunhuang.eligibility.model.HouseHold;
import com.yunhuang.eligibility.model.Member;
import com.yunhuang.eligibility.model.SearchMemberDto;
import com.yunhuang.eligibility.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberSerice {
    private final MemberRepository memberRepository;
    private final HouseHoldService houseHoldService;
    private final ApplicantService applicantService;

    public MemberSerice(MemberRepository memberRepository,
                        HouseHoldService houseHoldService,
                        ApplicantService applicantService)
    {
        this.memberRepository = memberRepository;
        this.houseHoldService = houseHoldService;
        this.applicantService = applicantService;
    }

    public Member saveMember(BigInteger householdId, BigInteger applicantId, Member member)
    {
        HouseHold houseHold = houseHoldService.getHouseholdById(householdId);
        Applicant applicant = applicantService.getApplicateById(applicantId);
        member.setApplicant(applicant);
        member.setHouseHold(houseHold);
        member.setCreatedDate(LocalDateTime.now());
        member.setUpdatedDate(LocalDateTime.now());
        return memberRepository.save(member);
    }

    public List<SearchMemberDto> searchMember(String householdName, String firstName, String lastName)
    {
        return memberRepository.findMemberByhouseholdNameByApplicantName(householdName, firstName, lastName);
    }
}
