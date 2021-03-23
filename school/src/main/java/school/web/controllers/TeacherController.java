package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.model.binding.TeacherBindingModel;
import school.model.service.TeacherServiceModel;
import school.repository.SubjectRepository;
import school.service.TeacherService;
import school.service.UserService;

import static school.constants.GlobalConstants.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController extends BaseController {

    private final TeacherService teacherService;
    private final UserService userService;

    @Autowired
    public TeacherController(ModelMapper modelMapper,
                             TeacherService teacherService, UserService userService) {
        super(modelMapper);
        this.teacherService = teacherService;
        this.userService = userService;
    }

    @GetMapping
    public String teachers(Model model){
        model.addAttribute("teachers",teacherService.getAllTeachers());
        model.addAttribute("teacherUsers",userService.getAllTeachers());
        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL,new TeacherBindingModel());
        }
        return "teachers";
    }

    @PostMapping("/add")
    public String add(TeacherBindingModel bindingModel, RedirectAttributes redirectAttributes){
        TeacherServiceModel serviceModel = modelMapper.map(bindingModel, TeacherServiceModel.class);
        if (teacherService.existByUserId(bindingModel.getUserId())){
            redirectAttributes.addFlashAttribute(BINDING_MODEL,bindingModel);
            redirectAttributes.addFlashAttribute(ERROR, String.format("%s е учител вече.",userService.getUser(bindingModel.getUserId()).getUsername()));
            return redirect("/teachers");
        }
        teacherService.addTeacher(serviceModel);
        return redirect("/teachers");
    }

    @GetMapping("/edit/{id}")
    public String editGet(@PathVariable Long id, Model model){
        TeacherServiceModel serviceModel = teacherService.getTeacherById(id);
        TeacherBindingModel bindingModel = modelMapper.map(serviceModel, TeacherBindingModel.class);
        model.addAttribute("teacherUsers",userService.getAllTeachers());
        model.addAttribute(BINDING_MODEL,bindingModel);
        return "teachers-edit";
    }

    @PutMapping("/edit")
    public String editTeacherPut(TeacherBindingModel bindingModel,RedirectAttributes redirectAttributes){
        if (teacherService.existByUserId(bindingModel.getUserId())){
            redirectAttributes.addFlashAttribute(BINDING_MODEL,bindingModel);
            redirectAttributes.addFlashAttribute(ERROR, String.format("%s е учител вече.",userService.getUser(bindingModel.getUserId()).getUsername()));
            return redirect("/teachers");
        }
        teacherService.editTeacher(modelMapper.map(bindingModel, TeacherServiceModel.class));
        return redirect("/teachers");
    }

    @DeleteMapping("/delete")
    public String deleteTeacher(Long id){
        teacherService.deleteTeacher(id);
        return redirect("/teachers");
    }
}
