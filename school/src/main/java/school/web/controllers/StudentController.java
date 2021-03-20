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
import school.service.GroupService;
import school.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

import static school.constants.GlobalConstants.BINDING_MODEL;

@Controller
@RequestMapping("/students")
public class StudentController extends BaseController {

    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(ModelMapper modelMapper,
                             StudentService studentService, GroupService groupService) {
        super(modelMapper);
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @PostMapping("/add")
    public String addStudentPost(StudentBindingModel bindingModel,RedirectAttributes redirectAttributes) {
        StudentServiceModel serviceModel = modelMapper.map(bindingModel, StudentServiceModel.class);
        studentService.addStudent(serviceModel);
        String successMessage = String.format("%s %s %s e добавен успешно",bindingModel.getFirstName(),bindingModel.getMiddleName(),bindingModel.getLastName());
        redirectAttributes.addFlashAttribute("successMessage",successMessage);
        return redirect("/students/add/" + serviceModel.getGroupId());
    }

    @GetMapping("/add/{id}")
    public String addStudentGet(@PathVariable Long id, Model model) {
        model.addAttribute("group",groupService.getGroupById(id));
        if (model.getAttribute(BINDING_MODEL) == null) {
            model.addAttribute(BINDING_MODEL, new StudentBindingModel());
        }
        model.addAttribute("students",studentService.getStudentsByClassId(id));
        return "students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id,Model model){
        StudentServiceModel serviceModel = studentService.getStudentById(id);
        StudentViewModel bindingModel = modelMapper.map(serviceModel, StudentViewModel.class);
        model.addAttribute("student",bindingModel);
        return "students-edit";
    }

    @PutMapping("/edit")
    public String editStudentPut(StudentViewModel studentBindingModel){
        studentService.editStudent(modelMapper.map(studentBindingModel,StudentServiceModel.class));
        return redirect("/students/add/" + studentBindingModel.getGroupId());
    }

    @DeleteMapping("/delete")
    public String deleteStudent(Long id,Long groupId){
        studentService.deleteStudent(id);
        return redirect("/students/add/" + groupId);
    }

    private List<StudentViewModel> allGet(Long id) {
        return studentService.getStudentsByClassId(id)
                .stream()
                .map(s-> modelMapper.map(s,StudentViewModel.class))
                .collect(Collectors.toList());
    }
}
