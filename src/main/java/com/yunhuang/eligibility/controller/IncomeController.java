package com.yunhuang.eligibility.controller;

import com.yunhuang.eligibility.model.HouseHold;
import com.yunhuang.eligibility.model.Income;
import com.yunhuang.eligibility.model.Member;
import com.yunhuang.eligibility.repository.SearchIncomeProjection;
import com.yunhuang.eligibility.repository.SearchMemberByHouseholdProj;
import com.yunhuang.eligibility.repository.SearchMemberProjection;
import com.yunhuang.eligibility.service.HouseHoldService;
import com.yunhuang.eligibility.service.IncomeService;
import com.yunhuang.eligibility.service.MemberSerice;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/income")
public class IncomeController {
   private final IncomeService incomeService;
   private final HouseHoldService houseHoldService;
   private final MemberSerice memberSerice;

   public IncomeController(IncomeService incomeService,
                           HouseHoldService houseHoldService,
                           MemberSerice memberSerice)
   {
       this.incomeService = incomeService;
       this.houseHoldService = houseHoldService;
       this.memberSerice = memberSerice;
   }

   @GetMapping("/new")
    public String createIncome(Model model)
   {
       model.addAttribute("income", new Income());
       return "income-create";
   }

    @GetMapping("select-member")
    public String displaySearchHousehold(Model model)
    {
        model.addAttribute("household", new HouseHold());
        return "member-search";
    }

    @GetMapping("/search-member-name")
    public String getMember(
            @RequestParam(required = false) String householdName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            Model model) {

        // Convert empty strings to null
        if (householdName != null && householdName.isBlank()) {
            householdName = null;
        }
        List<SearchMemberProjection> members =
                memberSerice.getMemberByHouseholdByApplicant(householdName, firstName, lastName);
        model.addAttribute("members", members);
        // Preserve search criteria
        model.addAttribute("householdName", householdName);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);

        return "member-search";
    }


    @PostMapping("/save")
    public String saveIncome(@Valid @ModelAttribute("income") Income income,
                             BindingResult results,
                             @RequestParam("memberId") BigInteger memberId,
                             Model model)
    {
        if (results.hasErrors())
        {
            model.addAttribute("income", income);
            return "income-create";
        }
        Income incomeObj = incomeService.saveIncome(memberId, income);
        return "redirect:/income/list";
    }

    @GetMapping("/list")
    public String displayIncomeList(
            @RequestParam(required = false) String householdName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            Model model
    )
    {
        List<SearchIncomeProjection> incomes =
                incomeService.getIncomeByHouseholdbyMember(householdName, firstName, lastName);
        model.addAttribute("incomes", incomes);
        return "income-list";
    }

    @GetMapping("/edit/{id}")
    public String editIncome(@PathVariable("id")BigInteger id, Model model)
    {
        Income income = incomeService.getIncomeById(id);
        model.addAttribute("income", income);
        Member member = income.getMember();
        String householdName = member.getHouseHold().getHouseholdName();
        model.addAttribute("member", member);
        model.addAttribute("householdName", householdName);
        return "income-update";
    }

    @PostMapping("/update/{id}")
    public String updateIncomeRecord(@Valid @ModelAttribute("Income") Income income,
                               @PathVariable("id") BigInteger id,
                            BindingResult results, Model model)
    {
        if (results.hasErrors())
        {
            model.addAttribute("income", income);
            return "income-update";
        }
        incomeService.updateIncome(id, income);
        return "redirect:/income/list";
    }

    @GetMapping("/detail/{id}")
    public String displayIncomeDetail(@PathVariable("id")BigInteger id, Model model)
    {
        Income income = incomeService.getIncomeById(id);
        model.addAttribute("income", income);
        Member member = income.getMember();
        String householdName = member.getHouseHold().getHouseholdName();
        model.addAttribute("member", member);
        model.addAttribute("householdName", householdName);
        return "income-detail";
    }

    @GetMapping("/delete/{id}")
    public String removeIncome(@PathVariable("id") BigInteger id)
    {
        incomeService.deleteIncome(id);
        return "redirect:/income/list";
    }

}
