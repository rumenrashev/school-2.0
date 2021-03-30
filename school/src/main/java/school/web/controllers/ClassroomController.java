package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.anotation.PageTitle;
import school.model.binding.ClassroomBindingModel;
import school.model.binding.StudentBindingModel;
import school.model.service.ClassroomServiceModel;
import school.model.view.ClassroomViewModel;
import school.service.ClassroomService;

import java.util.List;
import java.util.stream.Collectors;

import static school.constants.GlobalConstants.*;

@Controller
@RequestMapping("/classrooms")
public class ClassroomController extends BaseController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ModelMapper modelMapper, ClassroomService classroomService) {
        super(modelMapper);
        this.classroomService = classroomService;
    }

    @GetMapping("/all")
    @PageTitle(value = "Всички класове")
    public String all(Model model){
        model.addAttribute("groups",getClassrooms());
        return "classroom-all";
    }

    @GetMapping("/add")
    @PageTitle(value = "Добави клас")
    public String add(Model model){
        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL,new ClassroomBindingModel());
            model.addAttribute(ERROR,null);
        }
        if (model.getAttribute("messageSuccess") == null){
            model.addAttribute("messageSuccess",null);
        }
        return "classroom-add";
    }

    @PostMapping("/add")
    public String addClassroomPost(ClassroomBindingModel classroomBindingModel,
                               RedirectAttributes redirectAttributes){
        ClassroomServiceModel serviceModel = modelMapper.map(classroomBindingModel, ClassroomServiceModel.class);
        boolean successful = classroomService.createClassroom(serviceModel);
        if (!successful){
            String error = String.format(
                    GROUP_CREATED, classroomBindingModel.getNumber(), classroomBindingModel.getLetter());
            redirectAttributes.addFlashAttribute(ERROR,error);
            redirectAttributes.addFlashAttribute(BINDING_MODEL, classroomBindingModel);
            return redirect("/classrooms/add");
        }
        String messageSuccess = String.format(GROUP_EXISTS,
                classroomBindingModel.getNumber(), classroomBindingModel.getLetter());
        redirectAttributes.addFlashAttribute("messageSuccess",messageSuccess);
        return redirect("/classrooms/add");
    }

    @GetMapping("/details")
    @PageTitle(value = "Клас-дейтали")
    public String classroomDetails(){
        return "classroom-details";
    }

    @GetMapping("/details/{id}")
    @PageTitle(value = "Клас-дейтали")
    public String classroomsDetailsGet(@PathVariable Long id,Model model){
        ClassroomServiceModel group = classroomService.getById(id);
        model.addAttribute(BINDING_MODEL,new StudentBindingModel());
        model.addAttribute("group",group);
        return "classroom-details";
    }

    @PostMapping("/details")
    public String classroomDetailsPost(Long groupId,RedirectAttributes redirectAttributes){
        ClassroomServiceModel group = classroomService.getById(groupId);
        StudentBindingModel bindingModel = new StudentBindingModel();
        redirectAttributes.addFlashAttribute("group",group);
        redirectAttributes.addFlashAttribute(BINDING_MODEL,bindingModel);
        return redirect("/classrooms/details");
    }

    private List<ClassroomViewModel> getClassrooms(){
        return classroomService.getAll()
                .stream()
                .map(s -> modelMapper.map(s, ClassroomViewModel.class))
                .collect(Collectors.toList());
    }
}
