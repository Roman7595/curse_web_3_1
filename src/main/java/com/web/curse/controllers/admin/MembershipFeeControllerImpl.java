package com.web.curse.controllers.admin;

import com.web.curse.dtos.out.MembershipFeeOutputDto;
import com.web.curse.dtos.save.MembershipFeeSaveDto;
import com.web.curse.services.MembershipFeeDomainService;
import org.example.controllers.BaseAdminController;
import org.example.viewmodel.models.admin.MembershipFeeModel;
import org.example.viewmodel.models.admin.MembershipFeeListModel;
import org.example.viewmodel.forms.MembershipFeeSaveForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/membership")
public class MembershipFeeControllerImpl implements BaseAdminController<MembershipFeeSaveForm> {
    MembershipFeeDomainService membershipFeeDomainService;

    @Autowired
    public MembershipFeeControllerImpl(MembershipFeeDomainService membershipFeeDomainService) {
        this.membershipFeeDomainService = membershipFeeDomainService;
    }

    @Override
    @GetMapping("/")
    public String list(Model model){
        List<MembershipFeeModel> list = new ArrayList<>();
        for (MembershipFeeOutputDto m:membershipFeeDomainService.findAll()) {
            list.add(new MembershipFeeModel(m.id,m.contributionAmount,m.startLocalDate,m.endLocalDate));
        }
        model.addAttribute("model", new MembershipFeeListModel(list));
        return "/admin/membership/list";
    }

    @Override
    @GetMapping("/create")
    public String saveForm(Model model){
        model.addAttribute("form", new MembershipFeeModel(0,0,null,null));
        return "/admin/membership/create";
    }

    @Override
    @PostMapping("/create")
    public String save(MembershipFeeSaveForm membershipFee, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("form", membershipFee);
            return "/admin/membership/create";
        }
        membershipFeeDomainService.save(new MembershipFeeSaveDto(membershipFee.contributionAmount()));
        return "redirect:/admin/membership/";
    }

}
