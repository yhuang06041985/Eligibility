package com.yunhuang.eligibility.service;

import com.yunhuang.eligibility.repository.IncomeRepository;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository)
    {
        this.incomeRepository = incomeRepository;
    }
}
