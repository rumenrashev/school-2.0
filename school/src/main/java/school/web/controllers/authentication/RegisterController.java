package school.web.controllers.authentication;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.model.binding.UserRegisterBindingModel;
import school.model.service.UserServiceModel;
import school.service.UserService;
import school.web.annotation.PageTitle;
import school.web.controllers.base.BaseController;

import javax.validation.Valid;

import static school.constants.GlobalConstants.*;

@Controller
@RequestMapping(REGISTER_URL)
public class RegisterController extends BaseController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public RegisterController(ModelMapper modelMapper,
                              UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping()
    @PageTitle("Register")
    public String register(Model model){
        if (model.getAttribute("bindingModel") == null){
            model.addAttribute("bindingModel", new UserRegisterBindingModel());
        }
        return REGISTER_TEMPLATE;
    }

    @PostMapping
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("bindingModel",userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bindingModel",bindingResult);
            return super.redirect(REGISTER_URL);
        }
        this.userService.registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return super.redirect(LOGIN_URL);
    }

}
