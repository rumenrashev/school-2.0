package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.constants.enumuration.SubjectEnum;
import school.model.entity.GroupEntity;
import school.model.entity.SubjectEntity;
import school.model.service.SubjectServiceModel;
import school.repository.SubjectRepository;
import school.service.SubjectService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl extends BaseService implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(ModelMapper modelMapper, SubjectRepository subjectRepository) {
        super(modelMapper);
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void addSubject(SubjectServiceModel serviceModel) {
        SubjectEntity subjectEntity = modelMapper.map(serviceModel, SubjectEntity.class);
        subjectEntity.setId(null);
        this.subjectRepository.save(subjectEntity);
    }

    @Override
    public List<SubjectServiceModel> getAllSubjectsByClassId(Long id) {
        return this.subjectRepository
                .findAll()
                .stream()
                .map(e-> modelMapper.map(e,SubjectServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public boolean subjectExists(SubjectEnum subject, Long groupId) {
        return subjectRepository.existsBySubjectAndGroupId(subject,groupId);
    }

    @Override
    public SubjectServiceModel getSubjectById(Long id) {
        return this.subjectRepository
                .findById(id)
                .map(e-> modelMapper.map(e,SubjectServiceModel.class))
                .orElseThrow();
    }
}
