package com.yunhuang.eligibility.controller;

import com.yunhuang.eligibility.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yunhuang.eligibility.model.Address;

@Controller
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;
    public AddressController(AddressService addressService)
    {
        this.addressService = addressService;
    }

    @GetMapping("/new")
    public String displayAddress(Model model)
    {
        model.addAttribute("address", new Address());
        return "address-create";
    }
}
