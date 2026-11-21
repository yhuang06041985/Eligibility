package com.yunhuang.eligibility.service;

import com.yunhuang.eligibility.model.HouseHold;
import com.yunhuang.eligibility.model.Income;
import com.yunhuang.eligibility.model.Member;
import com.yunhuang.eligibility.repository.IncomeProjection;
import com.yunhuang.eligibility.repository.IncomeRepository;
import com.yunhuang.eligibility.repository.MemberRepository;
import com.yunhuang.eligibility.repository.SearchIncomeProjection;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final HouseHoldService houseHoldService;
    private final MemberSerice memberSerice;
    private final MemberRepository memberRepository;

    public IncomeService(IncomeRepository incomeRepository,
                         HouseHoldService houseHoldService,
                         MemberRepository memberRepository,
                         MemberSerice memberSerice)
    {
        this.incomeRepository = incomeRepository;
        this.houseHoldService = houseHoldService;
        this.memberSerice = memberSerice;
        this.memberRepository = memberRepository;
    }

    public Income saveIncome(BigInteger memberId, Income income)
    {
        Member member = memberRepository.findById(memberId).orElse(null);
        income.setMember(member);
        income.setCreatedDate(LocalDateTime.now());
        return incomeRepository.save(income);
    }

    public List<SearchIncomeProjection> getIncomeByHouseholdbyMember(String househleName,
                                                                     String firstName,
                                                                     String lastName)
    {
        return incomeRepository.findIncomeByHouseholdByApplicant(househleName, firstName, lastName);
    }

    public Income getIncomeById(BigInteger id)
    {
       // return incomeRepository.findIncomeById(id);
        return incomeRepository.findById(id).orElse(null);
    }

    public Income updateIncome(BigInteger id, Income income)
    {
        Income updateObj = getIncomeById(id);
        if (updateObj != null)
        {
            updateObj.setEmployerName(income.getEmployerName());
            updateObj.setGrossAmount(income.getGrossAmount());
            updateObj.setPayFrequency(income.getPayFrequency());
            updateObj.setMonthlyAmount(income.getMonthlyAmount());
            updateObj.setIncomeType(income.getIncomeType());
            updateObj.setStartDate(income.getStartDate());
            updateObj.setEndDate(income.getEndDate());
            return incomeRepository.save(updateObj);
        }
        return income;
    }

    public void deleteIncome(BigInteger id)
    {
        Income deleteObj = getIncomeById(id);
        if (deleteObj != null)
        {
            incomeRepository.delete(deleteObj);
        }
        return;
    }

}

