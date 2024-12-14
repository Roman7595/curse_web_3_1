package com.web.curse.controllers.admin;

import com.web.curse.dtos.out.TariffOutputDto;
import com.web.curse.dtos.save.TariffSaveDto;
import com.web.curse.services.TariffDomainService;
import org.example.controllers.BaseAdminController;
import org.example.viewmodel.models.admin.TariffListModel;
import org.example.viewmodel.models.admin.TariffModel;
import org.example.viewmodel.forms.TariffSaveForm;
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
@RequestMapping("/admin/tariff")
public class TariffControllerImpl implements BaseAdminController<TariffSaveForm> {
    TariffDomainService tariffDomainService;

    @Autowired
    public TariffControllerImpl(TariffDomainService tariffDomainService) {
        this.tariffDomainService = tariffDomainService;
    }

    @Override
    @GetMapping("/")
    public String list(Model model){
        List<TariffModel> list = new ArrayList<>();
        for (TariffOutputDto t:tariffDomainService.findAll()) {
            System.out.println(t.id()+" "+t.doubleElectricalTariffDay());
            list.add(new TariffModel(t.id(), t.waterTariff(), t.singleElectricalTariff(), t.doubleElectricalTariffDay(),t.doubleElectricalTariffNight(),t.startLocalDate()));
        }
        model.addAttribute("model", new TariffListModel(list));
        return "admin/tariff/list";
    }

    @Override
    @GetMapping("/create")
    public String saveForm(Model model){
        model.addAttribute("form", new TariffSaveForm(0,0,0,0));
        return "admin/tariff/create";
    }

    @Override
    @PostMapping("/create")
    public String save(TariffSaveForm tariff, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("form", tariff);
            return "admin/tariff/create";
        }
        tariffDomainService.save(new TariffSaveDto(tariff.waterTariff(),tariff.singleElectricalTariff(),tariff.doubleElectricalTariffDay(),tariff.doubleElectricalTariffNight()));
        return "redirect:/admin/tariff/";
    }
}
