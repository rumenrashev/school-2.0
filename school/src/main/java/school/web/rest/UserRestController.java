package school.web.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import school.model.service.UserServiceModel;
import school.model.view.UserViewModel;
import school.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('ADMIN')")
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
        return this.userService.getAllUsers()
                .stream()
                .map(serviceModel -> modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/admins")
    public List<UserViewModel> getAdministrators() {
        return this.userService.getAllAdmins()
                .stream()
                .map(serviceModel -> modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/teachers")
    public List<UserViewModel> getTeachers() {
        return this.userService.getAllTeachers()
                .stream()
                .map(serviceModel -> modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
    }


    @GetMapping("/students")
    public List<UserViewModel> getStudents() {
        return this.userService.getAllStudents()
                .stream()
                .map(serviceModel -> modelMapper.map(serviceModel, UserViewModel.class))
                .collect(Collectors.toList());
    }
}
