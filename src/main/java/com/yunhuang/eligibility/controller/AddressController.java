package com.yunhuang.eligibility.controller;

import com.yunhuang.eligibility.model.Applicant;
import com.yunhuang.eligibility.model.ApplicantSearchDto;
import com.yunhuang.eligibility.service.AddressService;
import com.yunhuang.eligibility.service.AddressTypeService;
import com.yunhuang.eligibility.service.ApplicantService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.yunhuang.eligibility.model.Address;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    private final AddressTypeService addressTypeService;
    private final ApplicantService applicantService;
    public AddressController(AddressService addressService,
                             AddressTypeService addressTypeService,
                            ApplicantService applicantService )
    {
        this.addressService = addressService;
        this.addressTypeService = addressTypeService;
        this.applicantService = applicantService;
    }
    @GetMapping("/new")
    public String displayAddress(Model model)
    {
        model.addAttribute("address", new Address());
        model.addAttribute("addressTypes", addressTypeService.getAddressType());
        return "address-create";
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

    @PostMapping("/save")
    public String saveAddress(@Valid @ModelAttribute("address") Address address,
                             @RequestParam("applicantId") BigInteger applicantId,
                             BindingResult results,
                              Model model)
    {
        if (results.hasErrors())
        {
            model.addAttribute("address", new Address());
            return "address-create";
        }
        Applicant applicant = applicantService.getApplicateById(applicantId);
        address.setApplicant(applicant);
        Address addressObn = addressService.saveAddress(address);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String displayAddressAndApplicant(@RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
    Model model) {
        model.addAttribute("addresslist",
                addressService.getAddressApplicant(firstName, lastName, email, phone));

        return "address-list";
    }

    @GetMapping("/edit/{id}")
    public String editAddress(@PathVariable("id") BigInteger addressId,
                              Model model)
    {
        model.addAttribute("address", addressService.getAddressById(addressId));
        return "address-update";
    }

    @PostMapping("/update")
    public String updateAddress(@Valid @ModelAttribute("address") Address address,
                    BindingResult results, Model model) {
        if (results.hasErrors()) {
            model.addAttribute("address", address);
            return "address-update";
        }

        System.out.println(address.getId());
        return "redirect:/";
    }

}
