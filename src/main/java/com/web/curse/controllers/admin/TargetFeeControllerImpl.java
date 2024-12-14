package com.web.curse.controllers.admin;

import com.web.curse.dtos.out.TargetFeeOutputDto;
import com.web.curse.dtos.save.TargetFeeSaveDto;
import com.web.curse.entities.TargetFee;
import com.web.curse.services.TargetFeeDomainService;
import org.example.controllers.BaseAdminController;
import org.example.viewmodel.models.admin.TargetFeeListModel;
import org.example.viewmodel.models.admin.TargetFeeModel;
import org.example.viewmodel.forms.TargetFeeSaveForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/target")
public class TargetFeeControllerImpl implements BaseAdminController<TargetFeeSaveForm> {
    TargetFeeDomainService targetFeeDomainService;

    @Autowired
    public TargetFeeControllerImpl(TargetFeeDomainService targetFeeDomainService) {
        this.targetFeeDomainService = targetFeeDomainService;
    }

    @Override
    @GetMapping("/")
    public String list(Model model){
        List<TargetFeeModel> list = new ArrayList<>();
        for (TargetFeeOutputDto t:targetFeeDomainService.findAll()) {
            list.add(new TargetFeeModel(t.id,t.targetName,t.contributionAmount,t.startLocalDate,t.endLocalDate));
        }
        model.addAttribute("model", new TargetFeeListModel(list));
        return "admin/target/list";
    }

    @Override
    @GetMapping("/create")
    public String saveForm(Model model){
        model.addAttribute("form", new TargetFee("",0, new Date()));
        return "admin/target/create";
    }

    @Override
    @PostMapping("/create")
    public String save(TargetFeeSaveForm target, BindingResult bindingResult, Model model) {
        System.out.println(target.endLocalDate()+"___________________________");
        if(bindingResult.hasErrors()){
            model.addAttribute("form", target);
            return "admin/target/create";
        }
        targetFeeDomainService.save(new TargetFeeSaveDto(target.targetName(),target.contributionAmount(),target.endLocalDate()));
        return "redirect:/admin/target/";
    }
}
