package school.service.impl;

import org.modelmapper.ModelMapper;

public abstract class BaseService {

    protected ModelMapper modelMapper;

    protected BaseService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
