package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.model.binding.StudentBindingModel;
import school.model.service.StudentServiceModel;
import school.model.view.StudentViewModel;
import school.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

import static school.constants.GlobalConstants.BINDING_MODEL;

@Controller
@RequestMapping("/students")
public class StudentController extends BaseController {

    private final StudentService studentService;

    @Autowired
    public StudentController(ModelMapper modelMapper,
                             StudentService studentService) {
        super(modelMapper);
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public String addStudentPost(StudentBindingModel bindingModel,RedirectAttributes redirectAttributes) {
        StudentServiceModel serviceModel = modelMapper.map(bindingModel, StudentServiceModel.class);
        studentService.addStudent(serviceModel);
        redirectAttributes.addFlashAttribute("success",true);
        return redirect("/groups/details/" + serviceModel.getGroupId());
    }

    @GetMapping("/add/")
    public String addStudentGet(@PathVariable Long id, Model model) {
        model.addAttribute("groupId", id);
        if (model.getAttribute(BINDING_MODEL) == null) {
            model.addAttribute(BINDING_MODEL, new StudentBindingModel());
        }
        return "students-add";
    }

    @GetMapping("/all/{id}")
    @ResponseBody
    public List<StudentViewModel> allGet(@PathVariable Long id, Model model) {
        return studentService.getStudentsByClassId(id)
                .stream()
                .map(s-> modelMapper.map(s,StudentViewModel.class))
                .collect(Collectors.toList());
    }
}
