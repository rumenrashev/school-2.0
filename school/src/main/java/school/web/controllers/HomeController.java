package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import school.service.SubjectService;
import school.service.TeacherService;

import java.security.Principal;

@Controller
public class HomeController extends BaseController {

    private final TeacherService teacherService;

    @Autowired
    public HomeController(ModelMapper modelMapper,
                          TeacherService teacherService) {
        super(modelMapper);
        this.teacherService = teacherService;
    }

    @GetMapping
    public String index(Principal principal, Model model) {
        if (principal == null) {
            return "index";
        }
        String username = principal.getName();
        model.addAttribute("teacher", teacherService.getTeacherByUsername(username));
        return "home";
    }
}
