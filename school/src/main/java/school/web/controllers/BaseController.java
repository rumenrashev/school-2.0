package school.web.controllers;

import org.modelmapper.ModelMapper;

public abstract class BaseController {

    protected final ModelMapper modelMapper;

    protected BaseController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    protected String redirect(String url){
        return "redirect:" + url;
    }



}
