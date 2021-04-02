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
import school.model.binding.StudentBindingModel;
import school.model.service.StudentServiceModel;
import school.model.service.UserServiceModel;
import school.service.ClassroomService;
import school.service.RegisterService;
import school.service.StudentService;

import javax.validation.Valid;

import static school.constants.GlobalConstants.*;

@Controller
@RequestMapping("/students")
@PreAuthorize("hasAuthority('ADMIN')")
public class StudentController extends BaseController {

    private final StudentService studentService;
    private final ClassroomService classroomService;
    private final RegisterService registerService;

    @Autowired
    public StudentController(ModelMapper modelMapper,
                             StudentService studentService,
                             ClassroomService classroomService,
                             RegisterService registerService) {
        super(modelMapper);
        this.studentService = studentService;
        this.classroomService = classroomService;
        this.registerService = registerService;
    }

    @GetMapping("/all/{classroomId}")
    @PageTitle(value = "Ученици")
    public String studentsByGroupGet(@PathVariable Long classroomId, Model model) {
        model.addAttribute("classroom", classroomService.getById(classroomId));
        model.addAttribute("students", studentService.getStudentsByClassId(classroomId));
        return "students-all";
    }

    @GetMapping("/add/{classroomId}")
    @PageTitle(value = "Добави ученик")
    public String addStudentGet(@PathVariable Long classroomId, Model model) {
        model.addAttribute("group", classroomService.getById(classroomId));
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
        StudentServiceModel studentServiceModel = modelMapper.map(bindingModel, StudentServiceModel.class);
        if (registerService.isValid(bindingModel.getUserEmail())){
            UserServiceModel userServiceModel = registerService.registerStudent(bindingModel.getUserEmail());
            studentServiceModel.setUser(userServiceModel);
            studentService.addStudent(studentServiceModel);
            return redirect("/students/all/" + studentServiceModel.getClassroom().getId());
        }
        redirectAttributes.addFlashAttribute(BINDING_MODEL, bindingModel);
        redirectAttributes.addFlashAttribute(ERROR, true);
        return redirect("/students/add/" + bindingModel.getClassroomId());

    }

    @GetMapping("/edit/{studentId}")
    @PageTitle(value = "Редактирай ученик")
    public String editStudent(@PathVariable Long studentId, Model model) {
        StudentServiceModel serviceModel = studentService.getStudentById(studentId);
        StudentBindingModel bindingModel = modelMapper.map(serviceModel, StudentBindingModel.class);
        bindingModel.setUserEmail(serviceModel.getUser().getEmail());
        if (model.getAttribute(BINDING_MODEL) == null) {
            model.addAttribute(BINDING_MODEL, bindingModel);
        }
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
        StudentServiceModel studentServiceModel = modelMapper.map(bindingModel, StudentServiceModel.class);
        if (studentService.emailIsTheSame(studentServiceModel) || registerService.isValid(bindingModel.getUserEmail())) {
            studentServiceModel.setUser(registerService.registerStudent(bindingModel.getUserEmail()));
            studentService.editStudent(studentServiceModel);
            return redirect("/students/all/" + bindingModel.getClassroomId());
        }
        redirectAttributes.addFlashAttribute(BINDING_MODEL, bindingModel);
        redirectAttributes.addFlashAttribute(ERROR, true);
        return redirect("/students/edit/" + bindingModel.getId());
    }

    @DeleteMapping("/delete")
    public String deleteStudent(Long studentId, Long classroomId) {
        studentService.deleteStudent(studentId);
        return redirect("/students/all/" + classroomId);
    }

    @GetMapping("/home/{username}")
    @PreAuthorize("hasAuthority('STUDENT')")
    @PageTitle(value = "Ученик-начало")
    public String home(@PathVariable String username, Model model) {
        StudentServiceModel student = studentService.getStudentByUserUsername(username);
        model.addAttribute("student", student);
        return "home-student";
    }
}
