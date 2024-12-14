package com.web.curse.controllers.payment;

import com.web.curse.services.MembershipFeePaymentDomainService;
import org.example.controllers.BasePaymentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pay/membershipFee")
public class MembershipFeePaymentControllerImpl implements BasePaymentController {
    MembershipFeePaymentDomainService membershipFeePaymentDomainService;

    @Autowired
    public MembershipFeePaymentControllerImpl(MembershipFeePaymentDomainService membershipFeePaymentDomainService) {
        this.membershipFeePaymentDomainService = membershipFeePaymentDomainService;
    }
    @Override
    @GetMapping("/{id}")
    public String pay(@PathVariable long id, Model model) {
        membershipFeePaymentDomainService.pay(id);
        return "redirect:/client";
    }

}
