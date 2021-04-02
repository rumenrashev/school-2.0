package school.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.model.binding.UserLoginBindingModel;
import school.anotation.PageTitle;

import static school.constants.GlobalConstants.*;


@Controller
@PreAuthorize("isAnonymous()")
public class LoginController extends BaseController {

    public LoginController(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @GetMapping(LOGIN_URL)
    @PageTitle(value = "Вход")
    public String login(Model model) {
        if (model.getAttribute(ERROR) == null){
            model.addAttribute(ERROR,false);
        }
        return LOGIN_TEMPLATE;
    }


    @GetMapping(LOGIN_ERROR_URL)
    public String loginError(UserLoginBindingModel bindingModel,
                             RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(ERROR, true);
        redirectAttributes.addFlashAttribute(BINDING_MODEL,bindingModel);
        return super.redirect(LOGIN_URL);
    }
}

