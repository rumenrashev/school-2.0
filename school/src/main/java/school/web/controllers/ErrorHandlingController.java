package school.web.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlingController implements ErrorController {



    public static final int SAMPLE = HttpStatus.FORBIDDEN.value();

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {

        Integer statusCode = getStatusCode(request);

        final int value = HttpStatus.FORBIDDEN.value();

        String message = "";


        if (statusCode != null && statusCode == HttpStatus.FORBIDDEN.value()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("custom", "Достъп отказан");
            modelAndView.setViewName("error");
            return modelAndView;
        }

        return new ModelAndView("error");

    }

    private Integer getStatusCode(HttpServletRequest httpServletRequest){
        Object status =
                httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return status instanceof Integer ? (Integer)status : null;
    }
}
