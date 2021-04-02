package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.anotation.PageTitle;
import school.model.service.UserAuthenticationServiceModel;
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
        UserAuthenticationServiceModel userServiceModel = userService.getUserWithAuthorities(id);
        model.addAttribute("user",modelMapper.map(userServiceModel,UserViewModel.class));
        return "users-edit";
    }

    @GetMapping("/forgotten-password")
    @PreAuthorize("isAnonymous()")
    public String forgottenPasswordGet(){
        return "forgotten-password";
    }

    @PostMapping("/forgotten-password")
    @PreAuthorize("isAnonymous()")
    public String forgottenPasswordPost(String email, RedirectAttributes redirectAttributes){
        if (userService.resendPassword(email)){
            redirectAttributes.addFlashAttribute("success",email);
        }else {
            redirectAttributes.addFlashAttribute("error",email);
        }
        return redirect("/users/forgotten-password");
    }
}
