package school.web.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.model.view.UserViewModel;
import school.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users")
    public List<UserViewModel> getUsers() {
        List<UserViewModel> users = this.userService.getAllUsers()
                .stream()
                .map(serviceModel -> modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
        return this.userService.getAllUsers()
                .stream()
                .map(serviceModel -> modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/admins")
    public List<UserViewModel> getAdministrators() {
        List<UserViewModel> administrators = this.userService.getAllAdmins()
                .stream()
                .map(serviceModel -> modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
        return this.userService.getAllAdmins()
                .stream()
                .map(serviceModel -> modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/teachers")
    public List<UserViewModel> getTeachers() {
        List<UserViewModel> teachers = this.userService.getAllTeachers()
                .stream()
                .map(serviceModel -> modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
        return this.userService.getAllTeachers()
                .stream()
                .map(serviceModel -> modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
    }
}
