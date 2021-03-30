package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.exception.ClassroomNotFountException;
import school.model.entity.ClassroomEntity;
import school.model.service.ClassroomServiceModel;
import school.repository.ClassroomRepository;
import school.service.ClassroomService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassroomServiceImpl extends BaseService implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomServiceImpl(ModelMapper modelMapper, ClassroomRepository classroomRepository) {
        super(modelMapper);
        this.classroomRepository = classroomRepository;
    }

    @Override
    public boolean createClassroom(ClassroomServiceModel model) {
        if (classroomRepository.existsByNumberAndLetter(model.getNumber(),model.getLetter())){
            return false;
        }
        ClassroomEntity entity = modelMapper.map(model, ClassroomEntity.class);
        this.classroomRepository.saveAndFlush(entity);
        return true;
    }

    @Override
    public List<ClassroomServiceModel> getAll() {
        return classroomRepository
                .getAllOrderByNumberAndLetter()
                .stream()
                .map(e->modelMapper.map(e, ClassroomServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClassroomServiceModel getById(Long id) {
        return this.classroomRepository.findById(id)
                .map(e->modelMapper.map(e, ClassroomServiceModel.class))
                .orElseThrow(ClassroomNotFountException::new);
    }

    @Override
    public long getClassroomsCount() {
        return classroomRepository.count();
    }
}
