package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.model.binding.TeacherBindingModel;
import school.model.service.StudentServiceModel;
import school.model.service.TeacherServiceModel;
import school.model.view.StudentViewModel;
import school.service.TeacherService;

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

    @GetMapping
    public String teachers(Model model){
        model.addAttribute("teachers",teacherService.getAllTeachers());
        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL,new TeacherBindingModel());
        }
        return "teachers";
    }

    @PostMapping("/add")
    public String add(TeacherBindingModel bindingModel){
        TeacherServiceModel serviceModel = modelMapper.map(bindingModel, TeacherServiceModel.class);
        teacherService.addTeacher(serviceModel);
        return redirect("/teachers");
    }

    @GetMapping("/edit/{id}")
    public String editGet(@PathVariable Long id, Model model){
        TeacherServiceModel serviceModel = teacherService.getTeacherById(id);
        TeacherBindingModel bindingModel = modelMapper.map(serviceModel, TeacherBindingModel.class);
        model.addAttribute(BINDING_MODEL,bindingModel);
        return "teachers-edit";
    }

    @PutMapping("/edit")
    public String editTeacherPut(TeacherBindingModel teacherBindingModel){
        teacherService.editTeacher(modelMapper.map(teacherBindingModel, TeacherServiceModel.class));
        return redirect("/teachers");
    }

}
