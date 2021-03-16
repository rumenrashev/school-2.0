package school.web.controllers.admin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import school.model.binding.StudentBindingModel;
import school.model.service.StudentServiceModel;
import school.service.StudentService;
import school.web.controllers.base.BaseController;

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

    @PostMapping("/add-student")
    public String addStudent(StudentBindingModel bindingModel){
        StudentServiceModel serviceModel = modelMapper.map(bindingModel, StudentServiceModel.class);
        studentService.addStudent(serviceModel);
        return redirect("/admin/groups/details/" + serviceModel.getGroupId());
    }
}
