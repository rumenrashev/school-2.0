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
import school.model.binding.GroupBindingModel;
import school.model.binding.StudentBindingModel;
import school.model.service.GroupServiceModel;
import school.model.view.GroupViewModel;
import school.service.GroupService;

import java.util.List;
import java.util.stream.Collectors;

import static school.constants.GlobalConstants.*;

@Controller
@RequestMapping("/groups")
public class GroupController extends BaseController {

    private final GroupService groupService;

    @Autowired
    protected GroupController(ModelMapper modelMapper, GroupService groupService) {
        super(modelMapper);
        this.groupService = groupService;
    }

    @GetMapping("/all")
    public String all(Model model){
        model.addAttribute("groups",getGroups());
        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL,new GroupBindingModel());
        }
        return "groups-all";
    }

    @GetMapping("/add")
    public String addGroupGet(Model model){
        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL,new GroupBindingModel());
        }
        return "groups-add";
    }

    @PostMapping("/add")
    public String addGroupPost(GroupBindingModel groupBindingModel, RedirectAttributes redirectAttributes){
        GroupServiceModel serviceModel = modelMapper.map(groupBindingModel, GroupServiceModel.class);
        boolean successful = groupService.createGroup(serviceModel);
        if (!successful){
            redirectAttributes.addFlashAttribute(ERROR,true);
            redirectAttributes.addFlashAttribute(BINDING_MODEL,groupBindingModel);
            return redirect("/groups/add");
        }
        return redirect("/groups/all");
    }

    @GetMapping("/details")
    public String groupDetails(Model model){
        return "groups-details";
    }

    @GetMapping("/details/{id}")
    public String groupDetailsGet(@PathVariable Long id,Model model){
        GroupServiceModel group = groupService.getGroupById(id);
        StudentBindingModel bindingModel = new StudentBindingModel();
        model.addAttribute(BINDING_MODEL,new StudentBindingModel());
        model.addAttribute("group",group);
        return "groups-details";
    }

    @PostMapping("/details")
    public String groupDetailsPost(Long groupId,RedirectAttributes redirectAttributes){
        GroupServiceModel group = groupService.getGroupById(groupId);
        StudentBindingModel bindingModel = new StudentBindingModel();
        redirectAttributes.addFlashAttribute("group",group);
        redirectAttributes.addFlashAttribute(BINDING_MODEL,bindingModel);
        return redirect("/groups/details");
    }

    private List<GroupViewModel> getGroups(){
        return groupService.getAllGroups()
                .stream()
                .map(s -> modelMapper.map(s, GroupViewModel.class))
                .collect(Collectors.toList());
    }
}
