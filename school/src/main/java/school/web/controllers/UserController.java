package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.anotation.PageTitle;
import school.model.service.UserServiceModel;
import school.model.view.UserViewModel;
import school.service.UserService;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        super(modelMapper);
        this.userService = userService;
    }

    @GetMapping("/all")
    @PageTitle(value = "Потребители")
    public String users() {
        return "users-all";
    }

    @DeleteMapping("/delete")
    public String delete(Long userId) {
        this.userService.deleteUser(userId);
        return redirect("/users/all");
    }

    @PutMapping("/add-authority")
    public String addAuthority(Long userId, String authority) {
        userService.addAuthority(userId, authority);
        return redirect("/users/edit-user/" + userId);
    }

    @PutMapping("/remove-authority")
    public String removeAuthority(Long userId, String authority) {
        userService.removeAuthority(userId, authority);
        return redirect("/users/edit-user/" + userId);
    }

    @GetMapping("/edit-user/{id}")
    @PageTitle(value = "Промени потребител")
    public String editUser(@PathVariable Long id,Model model){
        UserServiceModel userServiceModel = userService.getUser(id);
        UserViewModel viewModel = modelMapper.map(userServiceModel, UserViewModel.class);
        model.addAttribute("user",viewModel);
        return "users-edit";
    }
}
