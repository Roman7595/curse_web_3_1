package com.web.curse.controllers.admin;

import com.web.curse.dtos.out.LandOutputDto;
import com.web.curse.dtos.save.LandSaveDto;
import com.web.curse.entities.enums.Meter;
import com.web.curse.services.LandDomainService;
import org.example.controllers.BaseAdminController;
import org.example.viewmodel.models.admin.LandListModel;
import org.example.viewmodel.models.admin.LandModel;
import org.example.viewmodel.forms.LandSaveForm;
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
@RequestMapping("/admin/land")
public class LandControllerImpl implements BaseAdminController<LandSaveForm> {
    LandDomainService landDomainService;

    @Autowired
    public LandControllerImpl(LandDomainService landDomainService) {
        this.landDomainService = landDomainService;
    }

    @Override
    @GetMapping("/")
    public String list(Model model){
        List<LandModel> list = new ArrayList<>();
        for (LandOutputDto l:landDomainService.getAll()) {
            list.add(new LandModel(l.id,l.number,l.sizeInArs,l.electricMeter.getValue()));
        }
        model.addAttribute("model", new LandListModel(list));
        return "admin/land/list";
    }

    @Override
    @GetMapping("/create")
    public String saveForm(Model model){
        model.addAttribute("form", new LandSaveForm("",1,0));
        return "admin/land/create";
    }

    @Override
    @PostMapping("/create")
    public String save(LandSaveForm landSaveForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("form", landSaveForm);
            return "admin/land/create";
        }
        landDomainService.save(new LandSaveDto(landSaveForm.number(),landSaveForm.sizeInArs(), Meter.values()[landSaveForm.electricMeter()]));
        return "redirect:/admin/land/";
    }
}
