package com.web.curse.controllers.payment;

import com.web.curse.services.TargetFeePaymentDomainService;
import org.example.controllers.BasePaymentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay/targetFee")
public class TargetFeePaymentControllerImpl implements BasePaymentController {
    TargetFeePaymentDomainService targetFeePaymentDomainService;

    @Autowired
    public TargetFeePaymentControllerImpl(TargetFeePaymentDomainService targetFeePaymentDomainService) {
        this.targetFeePaymentDomainService = targetFeePaymentDomainService;
    }


    @Override
    @GetMapping("/{id}")
    public String pay(long id, Model model) {
        targetFeePaymentDomainService.pay(id);
        return "redirect:/client";
    }

}
