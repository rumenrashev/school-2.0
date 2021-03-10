package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import school.model.view.UserViewModel;
import school.service.AdminService;
import school.web.controllers.base.BaseController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private final AdminService adminService;
    private final ModelMapper modelMapper;

    public AdminController(AdminService adminService, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserViewModel> users = this.adminService.getAllUsers()
                .stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "/admin/users";
    }

    @GetMapping("/admins")
    public String admins(Model model) {
        List<UserViewModel> users = this.adminService.getAllAdmins()
                .stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "/admin/users";
    }

    @DeleteMapping("/delete")
    public String delete(Long id) {
        this.adminService.deleteUser(id);
        return super.redirect("/admin/users");
    }

    @PutMapping("/add-authority")
    public String addAuthority(Long userId, String authority) {
        this.adminService.addAuthority(userId, authority);
        return super.redirect("/admin/users");
    }

    @PutMapping("/remove-authority")
    public String removeAuthority(Long userId, String authority) {
        this.adminService.removeAuthority(userId, authority);
        return super.redirect("/admin/users");
    }

}
