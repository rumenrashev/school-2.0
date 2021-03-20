package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.model.binding.SubjectBindingModel;
import school.model.service.GroupServiceModel;
import school.model.service.SubjectServiceModel;
import school.model.view.SubjectViewModel;
import school.service.GroupService;
import school.service.SubjectService;

import java.util.List;
import java.util.stream.Collectors;

import static school.constants.GlobalConstants.BINDING_MODEL;
import static school.constants.GlobalConstants.ERROR;

@Controller
@RequestMapping("/subjects")
public class SubjectController extends BaseController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(ModelMapper modelMapper, SubjectService subjectService) {
        super(modelMapper);
        this.subjectService = subjectService;
    }

    @GetMapping("/{groupId}")
    public String subjects(@PathVariable Long groupId, Model model) {
        model.addAttribute("groupId",groupId);
        model.addAttribute("subjects",getSubjects(groupId));
        if (model.getAttribute(BINDING_MODEL) == null) {
            model.addAttribute(BINDING_MODEL, new SubjectBindingModel());
        }
        return "subjects";
    }

    @PostMapping("/add")
    public String add(SubjectBindingModel subjectBindingModel, RedirectAttributes redirectAttributes) {
        if (subjectService.subjectExists(subjectBindingModel.getSubject(),subjectBindingModel.getGroupId())){
            redirectAttributes.addFlashAttribute(ERROR,"Предмета същестува");
            return redirect("/subjects/" + subjectBindingModel.getGroupId());
        }
        SubjectServiceModel serviceModel = modelMapper.map(subjectBindingModel, SubjectServiceModel.class);
        subjectService.addSubject(serviceModel);
        return redirect("/subjects/" + subjectBindingModel.getGroupId());
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
