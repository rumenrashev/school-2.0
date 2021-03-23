package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.anotation.PageTitle;
import school.model.binding.StudentBindingModel;
import school.model.service.StudentServiceModel;
import school.model.view.StudentViewModel;
import school.service.GroupService;
import school.service.StudentService;
import school.service.UserService;

import static school.constants.GlobalConstants.BINDING_MODEL;

@Controller
@RequestMapping("/students")
public class StudentController extends BaseController {

    private final StudentService studentService;
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public StudentController(ModelMapper modelMapper,
                             StudentService studentService,
                             GroupService groupService, UserService userService) {
        super(modelMapper);
        this.studentService = studentService;
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping("/all/{groupId}")
    @PageTitle(value = "Ученици")
    public String studentsByGroupGet(@PathVariable Long groupId, Model model) {
        model.addAttribute("group",groupService.getGroupById(groupId));
        model.addAttribute("students",studentService.getStudentsByClassId(groupId));
        return "students-all";
    }

    @GetMapping("/add/{groupId}")
    @PageTitle(value = "Добави ученик")
    public String addStudentGet(@PathVariable Long groupId, Model model){
        model.addAttribute("group",groupService.getGroupById(groupId));
        model.addAttribute("users",userService.getAllStudents());
        if (model.getAttribute(BINDING_MODEL) == null) {
            model.addAttribute(BINDING_MODEL, new StudentBindingModel());
        }
        return "students-add";
    }

    @PostMapping("/add")
    public String addStudentPost(StudentBindingModel bindingModel,RedirectAttributes redirectAttributes) {
        StudentServiceModel serviceModel = modelMapper.map(bindingModel, StudentServiceModel.class);
        studentService.addStudent(serviceModel);
        String successMessage = String.format("%s %s %s e добавен успешно",bindingModel.getFirstName(),bindingModel.getMiddleName(),bindingModel.getLastName());
        redirectAttributes.addFlashAttribute("successMessage",successMessage);
        return redirect("/students/add/" + serviceModel.getGroupId());
    }

    @GetMapping("/edit/{studentId}")
    @PageTitle(value = "Редактирай ученик")
    public String editStudent(@PathVariable Long studentId,Model model){
        StudentServiceModel serviceModel = studentService.getStudentById(studentId);
        StudentViewModel bindingModel = modelMapper.map(serviceModel, StudentViewModel.class);
        model.addAttribute("student",bindingModel);
        model.addAttribute("users",userService.getAllStudents());
        return "students-edit";
    }

    @PutMapping("/edit")
    public String editStudentPut(StudentViewModel studentBindingModel){
        studentService.editStudent(modelMapper.map(studentBindingModel,StudentServiceModel.class));
        return redirect("/students/all/" + studentBindingModel.getGroupId());
    }

    @DeleteMapping("/delete")
    public String deleteStudent(Long studentId,Long groupId){
        studentService.deleteStudent(studentId);
        return redirect("/students/all/" + groupId);
    }
}
