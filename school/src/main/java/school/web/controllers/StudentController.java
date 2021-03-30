package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.anotation.PageTitle;
import school.model.binding.StudentBindingModel;
import school.model.service.StudentServiceModel;
import school.model.view.StudentViewModel;
import school.service.ClassroomService;
import school.service.StudentService;

import javax.validation.Valid;

import static school.constants.GlobalConstants.BINDING_MODEL;
import static school.constants.GlobalConstants.BINDING_RESULT;

@Controller
@RequestMapping("/students")
public class StudentController extends BaseController {

    private final StudentService studentService;
    private final ClassroomService classroomService;

    @Autowired
    public StudentController(ModelMapper modelMapper, StudentService studentService, ClassroomService classroomService) {
        super(modelMapper);
        this.studentService = studentService;
        this.classroomService = classroomService;
    }

    @GetMapping("/all/{groupId}")
    @PageTitle(value = "Ученици")
    public String studentsByGroupGet(@PathVariable Long groupId, Model model) {
        model.addAttribute("classroom", classroomService.getById(groupId));
        model.addAttribute("students", studentService.getStudentsByClassId(groupId));
        return "students-all";
    }

    @GetMapping("/add/{groupId}")
    @PageTitle(value = "Добави ученик")
    public String addStudentGet(@PathVariable Long groupId, Model model) {
        model.addAttribute("group", classroomService.getById(groupId));
        model.addAttribute("users", studentService.getAllFreeStudentUsers());
        if (model.getAttribute(BINDING_MODEL) == null) {
            model.addAttribute(BINDING_MODEL, new StudentBindingModel());
        }
        return "students-add";
    }

    @PostMapping("/add")
    public String addStudentPost(@Valid StudentBindingModel bindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BINDING_MODEL, bindingModel);
            redirectAttributes.addFlashAttribute(BINDING_RESULT, bindingResult);
            return redirect("/students/add/" + bindingModel.getClassroomId());
        }
        StudentServiceModel serviceModel = modelMapper.map(bindingModel, StudentServiceModel.class);
        studentService.addStudent(serviceModel);
        return redirect("/students/all/" + serviceModel.getClassroom().getId());
    }

    @GetMapping("/edit/{studentId}")
    @PageTitle(value = "Редактирай ученик")
    public String editStudent(@PathVariable Long studentId, Model model) {
        StudentServiceModel serviceModel = studentService.getStudentById(studentId);
        StudentViewModel bindingModel = modelMapper.map(serviceModel, StudentViewModel.class);
        if (model.getAttribute(BINDING_MODEL) == null) {
            model.addAttribute(BINDING_MODEL, bindingModel);
        }
        model.addAttribute("users", studentService.getAllFreeStudentUsers());
        return "students-edit";
    }

    @PutMapping("/edit")
    public String editStudentPut(@Valid StudentBindingModel bindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BINDING_MODEL, bindingModel);
            redirectAttributes.addFlashAttribute(BINDING_RESULT, bindingResult);
            return redirect("/students/edit/" + bindingModel.getId());
        }
        studentService.editStudent(modelMapper.map(bindingModel, StudentServiceModel.class));
        return redirect("/students/all/" + bindingModel.getClassroomId());
    }

    @DeleteMapping("/delete")
    public String deleteStudent(Long studentId, Long classroomId) {
        studentService.deleteStudent(studentId);
        return redirect("/students/all/" + classroomId);
    }

    @GetMapping("/home/{username}")
    public String home(@PathVariable String username,Model model){
        model.addAttribute("student",studentService.getStudentByUserUsername(username));
        return "home-student";
    }
}
