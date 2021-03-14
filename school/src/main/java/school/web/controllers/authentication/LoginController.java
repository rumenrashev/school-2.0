package school.web.controllers.authentication;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.model.binding.UserLoginBindingModel;
import school.anotation.PageTitle;
import school.web.controllers.base.BaseController;

import static school.constants.GlobalConstants.*;


@Controller
public class LoginController extends BaseController {

    public LoginController(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @GetMapping(LOGIN_URL)
    @PageTitle(LOGIN_TITLE)
    public String login() {
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

