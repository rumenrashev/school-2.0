package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.model.entity.TeacherEntity;
import school.model.service.TeacherServiceModel;
import school.repository.TeacherRepository;
import school.service.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl extends BaseService implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(ModelMapper modelMapper,TeacherRepository teacherRepository) {
        super(modelMapper);
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void addTeacher(TeacherServiceModel serviceModel) {
        TeacherEntity entity = modelMapper.map(serviceModel, TeacherEntity.class);
        entity.setId(null);
        teacherRepository.save(entity);
    }

    @Override
    public List<TeacherServiceModel> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(e-> modelMapper.map(e,TeacherServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void editTeacher(TeacherServiceModel serviceModel) {
        TeacherEntity entity = modelMapper.map(serviceModel, TeacherEntity.class);
        entity.setId(null);
        String firstName = serviceModel.getFirstName();
        String middleName = serviceModel.getMiddleName();
        String lastName = serviceModel.getLastName();
        if (!firstName.isEmpty()){
            entity.setFirstName(firstName);
        }
        if (!middleName.isEmpty()){
            entity.setMiddleName(middleName);
        }
        if (!lastName.isEmpty()){
            entity.setLastName(lastName);
        }
        teacherRepository.save(entity);
    }

    @Override
    public TeacherServiceModel getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .map(e-> modelMapper.map(e,TeacherServiceModel.class))
                .orElseThrow();
    }
}
