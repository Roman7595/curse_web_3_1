package com.web.curse.controllers.payment;

import com.web.curse.services.TariffPaymentDomainService;
import org.example.controllers.BasePaymentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay/tariff")
public class TariffPaymentControllerImpl implements BasePaymentController {
    TariffPaymentDomainService tariffPaymentDomainService;

    @Autowired
    public TariffPaymentControllerImpl(TariffPaymentDomainService tariffPaymentDomainService) {
        this.tariffPaymentDomainService = tariffPaymentDomainService;
    }


    @Override
    @GetMapping("/{id}")
    public String pay(long id, Model model) {
        tariffPaymentDomainService.pay(id);
        return "redirect:/client";
    }


}
