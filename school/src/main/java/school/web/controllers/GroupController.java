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
    public GroupController(ModelMapper modelMapper,
                           GroupService groupService) {
        super(modelMapper);
        this.groupService = groupService;
    }

    @GetMapping("/all")
    @PageTitle(value = "Всички класове")
    public String all(Model model){
        model.addAttribute("groups",getGroups());
        return "groups-all";
    }

    @GetMapping("/add")
    @PageTitle(value = "Добави клас")
    public String add(Model model){
        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL,new GroupBindingModel());
            model.addAttribute(ERROR,null);
        }
        if (model.getAttribute("messageSuccess") == null){
            model.addAttribute("messageSuccess",null);
        }
        return "groups-add";
    }

    @PostMapping("/add")
    public String addGroupPost(GroupBindingModel groupBindingModel,
                               RedirectAttributes redirectAttributes){
        GroupServiceModel serviceModel = modelMapper.map(groupBindingModel, GroupServiceModel.class);
        boolean successful = groupService.createGroup(serviceModel);
        if (!successful){
            String error = String.format(
                    GROUP_CREATED,groupBindingModel.getNumber(),groupBindingModel.getLetter());
            redirectAttributes.addFlashAttribute(ERROR,error);
            redirectAttributes.addFlashAttribute(BINDING_MODEL,groupBindingModel);
            return redirect("/groups/add");
        }
        String messageSuccess = String.format(GROUP_EXISTS,
                groupBindingModel.getNumber(),groupBindingModel.getLetter());
        redirectAttributes.addFlashAttribute("messageSuccess",messageSuccess);
        return redirect("/groups/add");
    }

    @GetMapping("/details")
    @PageTitle(value = "Клас-дейтали")
    public String groupDetails(){
        return "groups-details";
    }

    @GetMapping("/details/{id}")
    @PageTitle(value = "Клас-дейтали")
    public String groupDetailsGet(@PathVariable Long id,Model model){
        GroupServiceModel group = groupService.getGroupById(id);
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
