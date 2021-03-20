package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.model.service.UserServiceModel;
import school.model.view.UserViewModel;
import school.service.AdminService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/users")
public class UsersController extends BaseController {

    private final AdminService adminService;


    public UsersController(AdminService adminService, ModelMapper modelMapper) {
        super(modelMapper);
        this.adminService = adminService;
    }

    @GetMapping("/show-users")
    public String users(Model model) {
        return "/admin/users/users";
    }

    @DeleteMapping("/delete")
    public String delete(Long userId) {
        this.adminService.deleteUser(userId);
        return redirect("/admin/users/show-users");
    }

    @PutMapping("/add-authority")
    public String addAuthority(Long userId, String authority) {
        adminService.addAuthority(userId, authority);
        return redirect("/admin/users/edit-user/" + userId);
    }

    @PutMapping("/remove-authority")
    public String removeAuthority(Long userId, String authority) {
        adminService.removeAuthority(userId, authority);
        return redirect("/admin/users/edit-user/" + userId);
    }

    @GetMapping("/edit-user/{id}")
    public String editUser(@PathVariable Long id,Model model){
        UserServiceModel userServiceModel = adminService.getUser(id);
        UserViewModel viewModel = modelMapper.map(userServiceModel, UserViewModel.class);
        model.addAttribute("user",viewModel);
        return "/admin/users/edit-user";
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
