package com.yunhuang.eligibility.controller;

import com.yunhuang.eligibility.model.Applicant;
import com.yunhuang.eligibility.model.ApplicantSearchDto;
import com.yunhuang.eligibility.model.HouseHold;
import com.yunhuang.eligibility.model.Member;
import com.yunhuang.eligibility.service.ApplicantService;
import com.yunhuang.eligibility.service.HouseHoldService;
import com.yunhuang.eligibility.service.MemberSerice;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("member")
public class MemberController {
    private final MemberSerice memberSerice;
    private final ApplicantService applicantService;
    private final HouseHoldService houseHoldService;

    MemberController(MemberSerice memberSerice,
                     ApplicantService applicantService,
                     HouseHoldService houseHoldService)
    {
        this.memberSerice = memberSerice;
        this.applicantService = applicantService;
        this.houseHoldService = houseHoldService;
    }

    @GetMapping("/new")
    public String create(Model model)
    {
        model.addAttribute("member", new Member());
        return "member-create";
    }

    @GetMapping("select-applicant")
    public String displaySearchApplicant(Model model)
    {
        model.addAttribute("applicant", new Applicant());
        return "applicant-search";
    }

    @GetMapping("/search-applicant-name")
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

        return "applicant-search";
    }

    @GetMapping("select-household")
    public String displaySearchHousehold(Model model)
    {
        model.addAttribute("household", new HouseHold());
        return "household-search";
    }

    @GetMapping("/search-household-name")
    public String getHouseholeName(
            @RequestParam(required = false)BigInteger householdId,
            @RequestParam(required = false) String householdName,
            Model model) {

        // Convert empty strings to null
        if (householdName != null && householdName.isBlank()) {
            householdName = null;
        }
        List<HouseHold> households =
                houseHoldService.getHouseholdByIdByName(householdId, householdName);
        model.addAttribute("households", households);
        // Preserve search criteria
        model.addAttribute("householdId", householdId);
        model.addAttribute("fullName", householdName);


        return "household-search";
    }


    @PostMapping("/save")
    public String saveHouseholdMember(@Valid @ModelAttribute("member") Member member,
                                      @RequestParam("applicantId") BigInteger applicantId,
                                      @RequestParam("householdId") BigInteger householdId,
                                      BindingResult results, Model model)
    {
        if (results.hasErrors())
        {
            model.addAttribute("member", new Member());
        }
        Member memberObj = memberSerice.saveMember(householdId, applicantId, member);
        return "redirect:/member/list";
    }

    @GetMapping("/list")
    public String displayMemberList(@RequestParam(required = false) String householdName,
                                    @RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String lastName,
                                    Model model)   {

        model.addAttribute("members",
                memberSerice.searchMember(householdName, firstName, lastName));
        return "member-list";
    }

    @GetMapping("/detail/{id}")
    public String displayMemeberDetail(@PathVariable("id") BigInteger id,
                                       Model model)
    {
        model.addAttribute("member", memberSerice.getMemberById(id));
        return "member-detail";
    }
}
