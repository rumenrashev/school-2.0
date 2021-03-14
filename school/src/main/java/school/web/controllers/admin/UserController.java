package school.web.controllers.admin;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.model.service.UserServiceModel;
import school.model.view.UserViewModel;
import school.service.AdminService;
import school.web.controllers.base.BaseController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class UserController extends BaseController {

    private final AdminService adminService;
    public UserController(AdminService adminService, ModelMapper modelMapper) {
        super(modelMapper);
        this.adminService = adminService;
    }

    @GetMapping("/show-users")
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
        UserViewModel viewModel = modelMapper.map(userServiceModel, UserViewModel.class);
        model.addAttribute("user",viewModel);
        return "/admin/edit-user";
    }

    @GetMapping("/admins")
    @ResponseBody
    public List<UserViewModel> admins(){
        return mapToUserViewModel(adminService.getAllAdmins());
    }


    @GetMapping("/teachers")
    @ResponseBody
    public List<UserViewModel> teachers(){
        return mapToUserViewModel(adminService.getAllTeachers());
    }

    @GetMapping("/users")
    @ResponseBody
    public List<UserViewModel> users(){
        return mapToUserViewModel(adminService.getAllUsers());
    }

    private List<UserViewModel> mapToUserViewModel(List<UserServiceModel> serviceModels){
        return serviceModels
                .stream()
                .map(s->this.modelMapper.map(s,UserViewModel.class))
                .collect(Collectors.toList());
    }
}
