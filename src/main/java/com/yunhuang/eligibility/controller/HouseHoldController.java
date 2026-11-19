package com.yunhuang.eligibility.controller;


import com.yunhuang.eligibility.model.Applicant;
import com.yunhuang.eligibility.model.ApplicantSearchDto;
import com.yunhuang.eligibility.model.HouseHold;
import com.yunhuang.eligibility.service.ApplicantService;
import com.yunhuang.eligibility.service.HouseHoldService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/household")
public class HouseHoldController {

    private final ApplicantService applicantService;
    private final HouseHoldService houseHoldService;

    public HouseHoldController(ApplicantService applicantService, HouseHoldService houseHoldService)
    {
        this.applicantService = applicantService;
        this.houseHoldService = houseHoldService;
    }
    @GetMapping("/new")
    public String createHousehold(Model model)
    {
        model.addAttribute("household", new HouseHold());
        return "household-create";
    }

    @GetMapping("select-applicant")
    public String displaySearchApplicant(Model model)
    {
        model.addAttribute("applicant", new Applicant());
        return "applicant-search";
    }

    @GetMapping("/search-applicant")
    public String getEmployeeName(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            Model model) {

        // Convert empty strings to null
        if (firstName != null && firstName.isBlank()) {
            firstName = null;
        }
        List<ApplicantSearchDto> ApplicantSearchDtos =
                applicantService.getApplicantByNameByEmailByPhone(firstName, lastName, email, phone);
        model.addAttribute("applicantsearchDtos", ApplicantSearchDtos);
        // Preserve search criteria
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);

        model.addAttribute("email", email);
        model.addAttribute("phone", phone);

        return "applicant-search-househole";
    }

    @PostMapping("/save")
    public String saveHousehold(
                    @Valid @ModelAttribute("household") HouseHold houseHold,
            BindingResult result,
            @RequestParam("applicantId") BigInteger applicantId,
            @RequestParam("householdName") String householdName,
            Model model) {

        if (result.hasErrors()) {
            return "household-create";
        }

        houseHold.setHouseoldName(householdName);
        houseHoldService.saveHousehold(applicantId, houseHold);

        return "redirect:/household/list";
    }

    @GetMapping("/list")
    public String displayHouseholds(@RequestParam(required = false) String householdName,
                                    Model model)
    {
        if (householdName == null || householdName.isBlank())
            model.addAttribute("households", houseHoldService.getAllHousehold());
        else
            model.addAttribute("households", houseHoldService.getHouseholdByName(householdName));

        return "household-list";
    }

    @GetMapping("delete/{id}")
    public String removeHousehold(@PathVariable("id") BigInteger householdId, Model model)
    {
        houseHoldService.deteteHousehold(householdId);
        model.addAttribute("messages", "Record delete was sucessful");
        return "redirect:/household/list";
    }


}
