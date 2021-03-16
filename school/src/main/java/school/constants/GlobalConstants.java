package school.constants;

public class GlobalConstants {

    //Roots
    public static final String ALL = "/**";
    public static final String AUTHENTICATION_ROOT = "/authentication";
    public static final String ADMIN_ROOT = "/admin/users";
    public static final String ADMIN_ROOT_ALL = ADMIN_ROOT + ALL;


    //URLs
    public static final String LOGIN_URL = AUTHENTICATION_ROOT + "/login";
    public static final String LOGIN_ERROR_URL = AUTHENTICATION_ROOT + "/login-error";
    public static final String REGISTER_URL = AUTHENTICATION_ROOT + "/register";
    public static final String LOGOUT_URL = "/logout";
    public static final String INDEX_URL = "/";


    //Templates
    public static final String LOGIN_TEMPLATE = LOGIN_URL;
    public static final String REGISTER_TEMPLATE = REGISTER_URL;

    //Validation attributes
    public static final String BINDING_MODEL = "bindingModel";
    public static final String BINDING_RESULT = "org.springframework.validation.BindingResult." + BINDING_MODEL;
    public static final String ERROR = "error";

    //Page title
    public static final String REGISTER_TITLE = "Register";
    public static final String LOGIN_TITLE = "Login";

}
