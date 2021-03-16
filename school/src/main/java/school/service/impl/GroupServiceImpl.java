package school.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.model.entity.GroupEntity;
import school.model.service.GroupServiceModel;
import school.repository.GroupRepository;
import school.service.GroupService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl extends BaseService implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(ModelMapper modelMapper,GroupRepository groupRepository) {
        super(modelMapper);
        this.groupRepository = groupRepository;
    }

    @Override
    public boolean createGroup(GroupServiceModel model) {
        if (groupRepository.existsByNumberAndLetter(model.getNumber(),model.getLetter())){
            return false;
        }
        GroupEntity entity = modelMapper.map(model, GroupEntity.class);
        this.groupRepository.saveAndFlush(entity);
        return true;
    }

    @Override
    public List<GroupServiceModel> getAllGroups() {
        return groupRepository.getAllOrderByNumberAndLetter()
                .stream()
                .map(e->modelMapper.map(e,GroupServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public GroupServiceModel getGroupById(Long id) {
        return this.groupRepository
                .findById(id)
                .map(e->modelMapper.map(e,GroupServiceModel.class))
                .orElseThrow();
    }
}
