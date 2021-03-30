package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.model.binding.TeacherBindingModel;
import school.model.service.TeacherServiceModel;
import school.service.TeacherService;

import javax.validation.Valid;

import static school.constants.GlobalConstants.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController extends BaseController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(ModelMapper modelMapper, TeacherService teacherService) {
        super(modelMapper);
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    public String teachersAll(Model model){
        model.addAttribute("teachers",teacherService.getAllTeachers());
        return "teachers-all";
    }

    @GetMapping("/add")
    public String addGet(Model model){
        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL,new TeacherBindingModel());
        }
        model.addAttribute("teachersUsers",teacherService.getAllFreeTeachersUsers());
        return "teachers-add";
    }


    @PostMapping("/add")
    public String addPost(@Valid TeacherBindingModel bindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute(BINDING_MODEL,bindingModel);
            redirectAttributes.addFlashAttribute(BINDING_RESULT,bindingResult);
            return redirect("/teachers/add");
        }
        TeacherServiceModel serviceModel = modelMapper.map(bindingModel, TeacherServiceModel.class);
        teacherService.addTeacher(serviceModel);
        return redirect("/teachers/all");
    }

    @GetMapping("/edit/{id}")
    public String editGet(@PathVariable Long id, Model model){
        if (model.getAttribute(BINDING_MODEL) == null){
            TeacherServiceModel serviceModel = teacherService.getTeacherById(id);
            TeacherBindingModel bindingModel = modelMapper.map(serviceModel, TeacherBindingModel.class);
            model.addAttribute(BINDING_MODEL,bindingModel);
        }
        model.addAttribute("teacherUsers",teacherService.getAllFreeTeachersUsers());
        return "teachers-edit";
    }

    @PutMapping("/edit")
    public String editTeacherPut(@Valid TeacherBindingModel bindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute(BINDING_MODEL,bindingModel);
            redirectAttributes.addFlashAttribute(BINDING_RESULT,bindingResult);
            return redirect("/teachers/edit/" + bindingModel.getId());
        }
        teacherService.editTeacher(modelMapper.map(bindingModel, TeacherServiceModel.class));
        return redirect("/teachers/all");
    }

    @DeleteMapping("/delete")
    public String deleteTeacher(Long id){
        teacherService.deleteTeacher(id);
        return redirect("/teachers/all");
    }

    @GetMapping("/home/{username}")
    public String home(@PathVariable String username,Model model){
        model.addAttribute("teacher",teacherService.getTeacherByUsername(username));
        return "home-teacher";
    }
}
