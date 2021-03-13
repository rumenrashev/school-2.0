package school.web.rest;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import school.model.service.UserServiceModel;
import school.model.view.UserViewModel;
import school.service.AdminService;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:8000/admin/users")
public class AdminRestController {

    private final AdminService adminService;
    private final ModelMapper modelMapper;

    public AdminRestController(AdminService adminService, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/admins")
    public List<UserViewModel> admins(){
        return mapToUserViewModel(adminService.getAllAdmins());
    }

    @GetMapping("/teachers")
    public List<UserViewModel> teachers(){
        return mapToUserViewModel(adminService.getAllTeachers());
    }

    @GetMapping("/users")
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
