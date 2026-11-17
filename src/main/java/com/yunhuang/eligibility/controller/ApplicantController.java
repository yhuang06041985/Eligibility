package com.yunhuang.eligibility.controller;

import com.yunhuang.eligibility.model.Applicant;
import com.yunhuang.eligibility.service.ApplicantService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Controller
@RequestMapping("applicant")
public class ApplicantController {

    private final ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService)
    {
        this.applicantService = applicantService;
    }

    @GetMapping("/new")
    public String create(Model model)
    {
        model.addAttribute("applicant", new Applicant());
        return "applicant-create";
    }

    @PostMapping("/save")
    public String saveApplicant(@Valid @ModelAttribute("applicant") Applicant applicant,
                                BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("Applicant", applicant);
            return "applicant-create";
        }

        Applicant neweApplicant = applicantService.saveApplicant(applicant);
        return "redirect:/applicant/list";
    }

    @GetMapping("/edit/{id}")
    public String editApplicante(@PathVariable("id")BigInteger id,
                Model model)
    {
        model.addAttribute("applicant", applicantService.getApplicateById(id));
        return "applicate-update";
    }

    @PostMapping("/update/{id}")
    public String updateApplicant(@Valid @ModelAttribute("appliant") Applicant applicant,
                                  @RequestParam("applicantId") BigInteger applicantId,
                                  @PathVariable("id") BigInteger id,
                                  BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("appliant", applicant);
            return "applicant-update";
        }

        Applicant updateObj = applicantService.updatedApplicant(id, applicant);
        Applicant Obj = applicantService.updatedApplicant(applicantId, applicant);
        return "redirect:/applicant/list";
    }

    @GetMapping("/list")
    public String displayList(Model model)
    {
        model.addAttribute("applicants", applicantService.getAllApplicants());
        return "applicant-list";
    }

    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable("id") BigInteger id, Model model)
    {
        model.addAttribute("applicant", applicantService.getApplicateById(id));
        return "applicant-detail";
    }
}
