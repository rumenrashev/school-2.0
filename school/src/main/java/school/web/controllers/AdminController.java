package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.model.service.UserServiceModel;
import school.model.view.UserViewModel;
import school.service.AdminService;
import school.web.controllers.base.BaseController;

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
        return super.redirect("/admin/edit-user/" + userId);
    }

    @PutMapping("/remove-authority")
    public String removeAuthority(Long userId, String authority) {
        this.adminService.removeAuthority(userId, authority);
        return super.redirect("/admin/edit-user/" + userId);
    }

    @GetMapping("/edit-user/{id}")
    public String editUser(@PathVariable Long id,Model model){
        UserServiceModel userServiceModel = this.adminService.getUser(id);
        UserViewModel viewModel = this.modelMapper.map(userServiceModel, UserViewModel.class);
        model.addAttribute("user",viewModel);
        return "/admin/edit-user";
    }

}
