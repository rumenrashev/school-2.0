package school.web.controllers.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.web.annotation.PageTitle;
import school.web.controllers.base.BaseController;

import static school.constants.GlobalConstants.LOGIN_TEMPLATE;
import static school.constants.GlobalConstants.LOGIN_URL;


@Controller
public class LoginController extends BaseController {

    @GetMapping(LOGIN_URL)
    @PageTitle("Login")
    public String login() {
        return LOGIN_TEMPLATE;
    }


    @GetMapping("/login-error")
    public String loginError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("loginError", true);
        return super.redirect(LOGIN_URL);
    }
}

