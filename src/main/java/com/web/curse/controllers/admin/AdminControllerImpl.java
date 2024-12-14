package com.web.curse.controllers.admin;

import com.web.curse.dtos.account.*;
import com.web.curse.dtos.out.ClientOutputDto;
import com.web.curse.dtos.out.ClientWithDebtOutputDto;
import com.web.curse.services.ClientDomainService;
import org.example.controllers.AdminController;
import org.example.viewmodel.models.*;
import org.example.viewmodel.models.admin.LandModel;
import org.example.viewmodel.models.admin.MembershipFeeModel;
import org.example.viewmodel.models.admin.TargetFeeModel;
import org.example.viewmodel.models.admin.TariffModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {

    ClientDomainService clientDomainService;

    public AdminControllerImpl(ClientDomainService clientDomainService) {
        this.clientDomainService = clientDomainService;
    }

    @Override
    @GetMapping("/")
    public String adminPanel() {
        return "admin/hello";
    }

    @Override
    @GetMapping("/get_dept_leaderboard")
    public String getAccurateClients(Model model) {

        List<ClientWithDebtModel> clientWithDebtModels = new ArrayList<>();
        for (ClientWithDebtOutputDto c:clientDomainService.getDeptLeaderboard()) {
            clientWithDebtModels.add(new ClientWithDebtModel(c.id,c.login,c.name,c.middleName,c.lastName,c.tariffDept,c.targetDept,c.membershipDept,c.totalDept));
        }
        model.addAttribute("model",new ClientWithDebtListModel(clientWithDebtModels));
        return "admin/dept/list";
    }

    @GetMapping("/client/")
    public String list(Model model){
        List<ClientModel> list = new ArrayList<>();
        for (ClientOutputDto c:clientDomainService.getAll()) {
            list.add(new ClientModel(c.id,c.login,c.name,c.middleName,c.lastName));
        }
        model.addAttribute("model", new ClientModelList(list));
        return "admin/client/list";
    }

}
