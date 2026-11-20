package com.yunhuang.eligibility.controller;

import com.yunhuang.eligibility.model.Income;
import com.yunhuang.eligibility.service.IncomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/income")
public class IncomeController {
   private final IncomeService incomeService;

   public IncomeController(IncomeService incomeService)
   {
       this.incomeService = incomeService;
   }

   @GetMapping("/new")
    public String createIncome(Model model)
   {
       model.addAttribute("income", new Income());
       return "income-create";
   }
}
