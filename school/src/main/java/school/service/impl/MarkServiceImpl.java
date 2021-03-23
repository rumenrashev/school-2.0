package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.model.entity.MarkEntity;
import school.model.service.MarkServiceModel;
import school.repository.MarkRepository;
import school.service.MarkService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarkServiceImpl extends BaseService implements MarkService {

    private final MarkRepository markRepository;

    @Autowired
    public MarkServiceImpl(ModelMapper modelMapper, MarkRepository markRepository) {
        super(modelMapper);
        this.markRepository = markRepository;
    }

    @Override
    public void addMark(MarkServiceModel serviceModel) {
        MarkEntity entity = modelMapper.map(serviceModel, MarkEntity.class);
        entity.setId(null);
        markRepository.save(entity);
    }

    @Override
    public List<MarkServiceModel> getMarksByStudentAndSubject(Long studentId, Long subjectId) {
        return markRepository.findAllByStudent_IdAndSubject_Id(studentId, subjectId)
                .stream()
                .map(e -> modelMapper.map(e, MarkServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMark(Long markId) {
        markRepository.deleteById(markId);
    }

    @Override
    public void editMark(MarkServiceModel serviceModel) {
        MarkEntity entity = modelMapper.map(serviceModel, MarkEntity.class);
        this.markRepository.save(entity);
    }

    @Override
    public Double getStudentAverageMark(List<MarkServiceModel> marks) {
        return marks
                .stream()
                .map(MarkServiceModel::getValue)
                .mapToInt(v -> v)
                .average()
                .orElse(0.00);
    }

}
