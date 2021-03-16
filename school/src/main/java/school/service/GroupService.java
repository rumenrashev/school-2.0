package school.service;

import school.model.service.GroupServiceModel;

import java.util.List;

public interface GroupService {

    boolean createGroup(GroupServiceModel model);

    List<GroupServiceModel> getAllGroups();

    GroupServiceModel getGroupById(Long id);

}
