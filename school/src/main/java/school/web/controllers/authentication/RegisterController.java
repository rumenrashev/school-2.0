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
import school.service.RegisterService;
import school.anotation.PageTitle;
import school.web.controllers.base.BaseController;

import javax.validation.Valid;

import static school.constants.GlobalConstants.*;

@Controller
@RequestMapping(REGISTER_URL)
public class RegisterController extends BaseController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(ModelMapper modelMapper,
                              RegisterService registerService) {
        super(modelMapper);
        this.registerService = registerService;
    }

    @GetMapping()
    @PageTitle(REGISTER_TITLE)
    public String register(Model model){
        if (model.getAttribute(BINDING_MODEL) == null){
            model.addAttribute(BINDING_MODEL, new UserRegisterBindingModel());
        }
        return REGISTER_TEMPLATE;
    }

    @PostMapping
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute(BINDING_MODEL,userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(BINDING_RESULT,bindingResult);
            return redirect(REGISTER_URL);
        }
        registerService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return redirect(LOGIN_URL);
    }

}
