package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.model.binding.SubjectBindingModel;
import school.model.service.GroupServiceModel;
import school.model.service.StudentServiceModel;
import school.model.service.SubjectServiceModel;
import school.model.service.TeacherServiceModel;
import school.model.view.SubjectViewModel;
import school.service.GroupService;
import school.service.StudentService;
import school.service.SubjectService;
import school.service.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

import static school.constants.GlobalConstants.BINDING_MODEL;
import static school.constants.GlobalConstants.ERROR;

@Controller
@RequestMapping("/subjects")
public class SubjectController extends BaseController {

    private final SubjectService subjectService;
    private final TeacherService teacherService;
    private final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    public SubjectController(ModelMapper modelMapper,
                             SubjectService subjectService,
                             TeacherService teacherService, GroupService groupService,
                             StudentService studentService) {
        super(modelMapper);
        this.subjectService = subjectService;
        this.teacherService = teacherService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @GetMapping("/all//{groupId}")
    public String subjects(@PathVariable Long groupId, Model model) {
        GroupServiceModel group = groupService.getGroupById(groupId);
        model.addAttribute("group",group);
        model.addAttribute("subjects",getSubjects(groupId));
        return "subjects-all";
    }

    @GetMapping("/add/{groupId}")
    public String addSubjectGet(@PathVariable Long groupId,Model model){
        GroupServiceModel group = groupService.getGroupById(groupId);
        List<TeacherServiceModel> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers",teachers);
        model.addAttribute("group",group);
        if (model.getAttribute(BINDING_MODEL) == null) {
            model.addAttribute(BINDING_MODEL, new SubjectBindingModel());
        }
        return "subjects-add";
    }

    @PostMapping("/add")
    public String add(SubjectBindingModel subjectBindingModel, RedirectAttributes redirectAttributes) {
        if (subjectService.subjectExists(subjectBindingModel.getSubject(),subjectBindingModel.getGroupId())){
            redirectAttributes.addFlashAttribute(ERROR,"Предмета същестува");
            return redirect("/subjects/add/" + subjectBindingModel.getGroupId());
        }
        SubjectServiceModel serviceModel = modelMapper.map(subjectBindingModel, SubjectServiceModel.class);
        subjectService.addSubject(serviceModel);
        return redirect("/subjects/all/" + subjectBindingModel.getGroupId());
    }

    @GetMapping("/edit/{id}")
    public String editSubjectGet(@PathVariable Long id,Model model){
        SubjectServiceModel serviceModel = subjectService.getSubjectById(id);
        SubjectBindingModel bindingModel = modelMapper.map(serviceModel, SubjectBindingModel.class);
        List<TeacherServiceModel> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers",teachers);
        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL,bindingModel);
        }
        return "subjects-edit";
    }

    @GetMapping("/details/{subjectId}")
    public String details(@PathVariable Long subjectId,Model model){
        Long groupId = subjectService.getGroupIdBySubjectId(subjectId);
        SubjectServiceModel subject = subjectService.getSubjectById(subjectId);
        List<StudentServiceModel> students = studentService.getStudentsByClassId(groupId);
        model.addAttribute("subject",subject);
        model.addAttribute("students",students);
        return "subjects-details";
    }

    @PutMapping("/edit")
    public String editSubjectPut(SubjectBindingModel bindingModel){
        SubjectServiceModel serviceModel = modelMapper.map(bindingModel, SubjectServiceModel.class);
        subjectService.editSubject(serviceModel);
        return redirect("/subjects/" + bindingModel.getId());
    }

    @DeleteMapping("/delete")
    public String delete(Long id,Long groupId){
        subjectService.deleteSubject(id);
        return redirect("/subjects/" + groupId);
    }


    private List<SubjectViewModel> getSubjects(Long groupId){
        return this.subjectService.getAllSubjectsByClassId(groupId)
                .stream()
                .map(s-> modelMapper.map(s,SubjectViewModel.class))
                .collect(Collectors.toList());
    }
}
