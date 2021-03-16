package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import school.model.entity.GroupEntity;
import school.model.entity.StudentEntity;
import school.model.service.StudentServiceModel;
import school.repository.GroupRepository;
import school.repository.StudentRepository;
import school.service.StudentService;
import school.service.base.BaseService;

@Service
public class StudentServiceImpl extends BaseService implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentServiceImpl(ModelMapper modelMapper,
                              StudentRepository studentRepository,
                              GroupRepository groupRepository) {
        super(modelMapper);
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public void addStudent(StudentServiceModel serviceModel) {
        StudentEntity studentEntity = modelMapper.map(serviceModel, StudentEntity.class);
        studentEntity.setId(null);
        GroupEntity groupEntity = groupRepository.findById(serviceModel.getId()).orElseThrow();
        studentEntity.setGroup(groupEntity);
        studentRepository.saveAndFlush(studentEntity);
    }
}
