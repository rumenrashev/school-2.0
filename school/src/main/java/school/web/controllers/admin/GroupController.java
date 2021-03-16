package school.web.controllers.admin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.model.binding.GroupBindingModel;
import school.model.binding.StudentBindingModel;
import school.model.service.GroupServiceModel;
import school.model.view.GroupViewModel;
import school.service.GroupService;
import school.service.StudentService;
import school.web.controllers.base.BaseController;

import java.util.List;
import java.util.stream.Collectors;

import static school.constants.GlobalConstants.*;

@Controller
@RequestMapping("/admin/groups")
public class GroupController extends BaseController {

    private final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    protected GroupController(ModelMapper modelMapper, GroupService groupService, StudentService studentService) {
        super(modelMapper);
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @GetMapping("/add-group")
    public String addGroupGet(Model model){
        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL,new GroupBindingModel());
        }
        model.addAttribute("groups",getGroups());
        return "/admin/groups/groups";
    }

    @PostMapping("/add-group")
    public String addGroupPost(GroupBindingModel groupBindingModel, RedirectAttributes redirectAttributes){
        GroupServiceModel serviceModel = modelMapper.map(groupBindingModel, GroupServiceModel.class);
        boolean successful = groupService.createGroup(serviceModel);
        if (!successful){
            redirectAttributes.addFlashAttribute(ERROR,true);
        }
        return redirect("/admin/groups/add-group");
    }

    @GetMapping("/details/{id}")
    public String groupDetailsGet(@PathVariable Long id,Model model){
        GroupServiceModel group = groupService.getGroupById(id);
        model.addAttribute("group",group);

        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL,new StudentBindingModel());
        }
        return "/admin/groups/group-details";
    }

    private List<GroupViewModel> getGroups(){
        return groupService.getAllGroups()
                .stream()
                .map(s -> modelMapper.map(s, GroupViewModel.class))
                .collect(Collectors.toList());
    }
}
