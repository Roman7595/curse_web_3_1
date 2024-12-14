package com.web.curse.controllers;

import com.web.curse.dtos.account.*;
import com.web.curse.dtos.out.*;
import com.web.curse.dtos.save.ClientSaveDto;
import com.web.curse.services.*;
import org.example.controllers.ClientsController;
import org.example.viewmodel.models.*;
import org.example.viewmodel.models.admin.*;
import org.example.viewmodel.forms.ClientSaveForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientControllerImpl implements ClientsController {
    ClientDomainService clientDomainService;
    LandDomainService landDomainService;

    @Autowired
    public ClientControllerImpl(ClientDomainService clientDomainService,LandDomainService landDomainService) {
        this.clientDomainService = clientDomainService;
        this.landDomainService = landDomainService;
    }



    @Override
    @PostMapping("/register")
    public String saveClient(ClientSaveForm form, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("form", form);
            return "client/register";
        }
        ClientOutputDto result = clientDomainService.register(new ClientSaveDto(form.login(),form.password(),form.name(),form.middleName(),form.lastName(),List.of(form.land())));
        return "redirect:/login";
    }

    @Override
    @GetMapping("/register")
    public String getClientForm(Model model) {
        List<LandPlainModel> result = new ArrayList<>();
        for (LandOutputDto l:landDomainService.getOnlyFree()) {
            result.add(new LandPlainModel(l.id,l.number));
        }

        model.addAttribute("form", new ClientSaveForm("","","","","",0L));
        model.addAttribute("model", new LandPlainModels(result));

        return "client/register";
    }

    @Override
    @GetMapping("/login")
    public String login() {
        return "client/login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/login";
    }

    @Override
    @GetMapping("/client")
    public String getAccount(Principal principal, Model model) {
        ClientOutputDto c = clientDomainService.findByLogin(principal.getName());
        FullClientOutputDto clientOutputDto = clientDomainService.createFullClient(c.id);
        ClientModel clientModel = new ClientModel(
                clientOutputDto.clientOutputDto().id,
                clientOutputDto.clientOutputDto().login,
                clientOutputDto.clientOutputDto().name,
                clientOutputDto.clientOutputDto().middleName,
                clientOutputDto.clientOutputDto().lastName
        );
        List<LandFullModel> lands = new ArrayList<>();
        for (FullLandOutputDto landOutputDto:clientOutputDto.lands()) {
            LandModel landModel = new LandModel(
                    landOutputDto.landOutputDto().id,
                    landOutputDto.landOutputDto().number,
                    landOutputDto.landOutputDto().sizeInArs,
                    landOutputDto.landOutputDto().electricMeter.getValue()
            );
            List<TariffPaymentFullModel> tariffPayments = new ArrayList<>();
            for (FullTariffPaymentOutputDto ftpd:landOutputDto.fullTariffPaymentOutputDtoList()) {
                TariffPaymentModel tariffPaymentModel = new TariffPaymentModel(
                        ftpd.tariffPaymentOutputDto().id(),
                        ftpd.tariffOutputDto().startLocalDate(),
                        ftpd.tariffPaymentOutputDto().endLocalDate(),
                        ftpd.tariffPaymentOutputDto().paymentLocalDate(),
                        ftpd.tariffPaymentOutputDto().waterUsage(),
                        ftpd.tariffPaymentOutputDto().singleElectricalUsage(),
                        ftpd.tariffPaymentOutputDto().doubleElectricalDayUsage(),
                        ftpd.tariffPaymentOutputDto().doubleElectricalNightUsage(),
                        ftpd.tariffPaymentOutputDto().waterSum(),
                        ftpd.tariffPaymentOutputDto().singleElectricalSum(),
                        ftpd.tariffPaymentOutputDto().doubleElectricalDaySum(),
                        ftpd.tariffPaymentOutputDto().doubleElectricalNightSum()
                );
                TariffModel tariffModel = new TariffModel(
                        ftpd.tariffOutputDto().id(),
                        ftpd.tariffOutputDto().waterTariff(),
                        ftpd.tariffOutputDto().singleElectricalTariff(),
                        ftpd.tariffOutputDto().doubleElectricalTariffDay(),
                        ftpd.tariffOutputDto().doubleElectricalTariffNight(),
                        ftpd.tariffOutputDto().startLocalDate()
                );
                tariffPayments.add(new TariffPaymentFullModel(
                        tariffPaymentModel, tariffModel
                ));
            }

            List<TargetFeePaymentFullModel> targetFeePayments = new ArrayList<>();
            for (FullTargetFeePaymentOutputDto ftpd:landOutputDto.fullTargetFeePaymentOutputDtoList()) {
                TargetFeePaymentModel targetFeePaymentModel = new TargetFeePaymentModel(
                        ftpd.targetFeePaymentOutputDto().id,
                        ftpd.targetFeePaymentOutputDto().feeSum,
                        ftpd.targetFeePaymentOutputDto().paymentLocalDate
                );
                TargetFeeModel targetFeeModel = new TargetFeeModel(
                        ftpd.targetFeeOutputDto().id,
                        ftpd.targetFeeOutputDto().targetName,
                        ftpd.targetFeeOutputDto().contributionAmount,
                        ftpd.targetFeeOutputDto().startLocalDate,
                        ftpd.targetFeeOutputDto().endLocalDate
                );
                targetFeePayments.add(new TargetFeePaymentFullModel(
                        targetFeePaymentModel, targetFeeModel
                ));


            }

            List<MembershipFeePaymentFullModel> membershipFeePayments = new ArrayList<>();
            for (FullMembershipFeePaymentOutputDto ftpd:landOutputDto.fullMembershipFeePaymentOutputList()) {
                MembershipFeePaymentModel membershipFeePaymentModel = new MembershipFeePaymentModel(
                        ftpd.membershipFeePaymentOutputDto().id,
                        ftpd.membershipFeePaymentOutputDto().feeSum,
                        ftpd.membershipFeePaymentOutputDto().paymentLocalDate
                );
                MembershipFeeModel membershipFeeModel = new MembershipFeeModel(
                        ftpd.membershipFeeOutputDto().id,
                        ftpd.membershipFeeOutputDto().contributionAmount,
                        ftpd.membershipFeeOutputDto().startLocalDate,
                        ftpd.membershipFeeOutputDto().endLocalDate
                );
                membershipFeePayments.add(new MembershipFeePaymentFullModel(
                        membershipFeePaymentModel, membershipFeeModel
                ));


            }

            lands.add(new LandFullModel(
                    landModel,
                    membershipFeePayments,
                    targetFeePayments,
                    tariffPayments
            ));
        }

        FullClientModel client = new FullClientModel(
                clientModel,
                lands
        );
        model.addAttribute("model", client);
        return "client/lk";
    }
}
