package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.anotation.PageTitle;
import school.model.binding.TeacherBindingModel;
import school.model.service.TeacherServiceModel;
import school.model.service.UserServiceModel;
import school.service.RegisterService;
import school.service.TeacherService;

import javax.validation.Valid;

import static school.constants.GlobalConstants.*;

@Controller
@RequestMapping("/teachers")
@PreAuthorize("hasAuthority('ADMIN')")
public class TeacherController extends BaseController {

    private final TeacherService teacherService;
    private final RegisterService registerService;

    @Autowired
    public TeacherController(ModelMapper modelMapper,
                             TeacherService teacherService,
                             RegisterService registerService) {
        super(modelMapper);
        this.teacherService = teacherService;
        this.registerService = registerService;
    }

    @GetMapping("/all")
    @PageTitle(value = "Учители")
    public String teachersAll(Model model){
        model.addAttribute("teachers",teacherService.getAllTeachers());
        return "teachers-all";
    }

    @GetMapping("/add")
    @PageTitle(value = "Добави учител")
    public String addGet(Model model){
        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL,new TeacherBindingModel());
        }
        return "teachers-add";
    }


    @PostMapping("/add")
    public String addPost(@Valid TeacherBindingModel bindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BINDING_MODEL, bindingModel);
            redirectAttributes.addFlashAttribute(BINDING_RESULT, bindingResult);
            return redirect("/teachers/add");
        }
        if (registerService.isValid(bindingModel.getUserEmail())){
            UserServiceModel userServiceModel = registerService.registerTeacher(bindingModel.getUserEmail());
            TeacherServiceModel teacherServiceModel = modelMapper.map(bindingModel, TeacherServiceModel.class);
            teacherServiceModel.setUser(userServiceModel);
            teacherService.addTeacher(teacherServiceModel);
            return redirect("/teachers/all");
        }
        redirectAttributes.addFlashAttribute(BINDING_MODEL, bindingModel);
        redirectAttributes.addFlashAttribute(ERROR, true);
        return redirect("/teachers/add");
    }

    @GetMapping("/edit/{id}")
    @PageTitle(value = "Редактирей учител")
    public String editGet(@PathVariable Long id, Model model){
        if (model.getAttribute(BINDING_MODEL) == null){
            TeacherServiceModel serviceModel = teacherService.getTeacherById(id);
            TeacherBindingModel bindingModel = modelMapper.map(serviceModel, TeacherBindingModel.class);
            model.addAttribute(BINDING_MODEL,bindingModel);
        }
        return "teachers-edit";
    }

    @PutMapping("/edit")
    public String editTeacherPut(@Valid TeacherBindingModel bindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BINDING_MODEL, bindingModel);
            redirectAttributes.addFlashAttribute(BINDING_RESULT, bindingResult);
            return redirect("/teachers/edit/" + bindingModel.getId());
        }
        TeacherServiceModel teacherServiceModel = modelMapper.map(bindingModel, TeacherServiceModel.class);
        if (teacherService.emailIsSame(teacherServiceModel) || registerService.isValid(bindingModel.getUserEmail())) {
            UserServiceModel userServiceModel = registerService.registerTeacher(bindingModel.getUserEmail());
            teacherServiceModel.setUser(userServiceModel);
            teacherService.editTeacher(teacherServiceModel);
            return redirect("/teachers/all");
        }
        redirectAttributes.addFlashAttribute(BINDING_MODEL, bindingModel);
        redirectAttributes.addFlashAttribute(ERROR, true);
        return redirect("/teachers/edit/" + bindingModel.getId());
    }

    @DeleteMapping("/delete")
    public String deleteTeacher(Long id){
        teacherService.deleteTeacher(id);
        return redirect("/teachers/all");
    }

    @GetMapping("/home/{username}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String home(@PathVariable String username,Model model){
        model.addAttribute("teacher",teacherService.getTeacherByUsername(username));
        return "home-teacher";
    }
}
