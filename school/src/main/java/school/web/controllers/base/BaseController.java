package school.web.controllers.base;

public abstract class BaseController {

    protected BaseController() {
    }

    protected String redirect(String url){
        return "redirect:" + url;
    }
}
