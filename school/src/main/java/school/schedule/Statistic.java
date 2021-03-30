package school.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import school.service.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class Statistic {

    private final TeacherService teacherService;
    private final UserService userService;
    private final MarkService markService;
    private final ClassroomService classroomService;

    private long teachersCount;
    private long usersCount;
    private long marksCount;
    private long classroomsCount;

    @Autowired
    public Statistic(TeacherService teacherService,
                     UserService userService,
                     MarkService markService,
                     ClassroomService classroomService) {
        this.teacherService = teacherService;
        this.userService = userService;
        this.markService = markService;
        this.classroomService = classroomService;
    }

    @Scheduled(fixedRate = 5000)
    public void setTeachersCount() {
        this.teachersCount = teacherService.getTeachersCount();
    }

    @Scheduled(fixedRate = 5000)
    private void setUsersCount() {
        this.usersCount = userService.getUserCount();
    }


    @Scheduled(fixedRate = 5000)
    private void setMarksCount() {
        this.marksCount = markService.getMarksCount();
    }


    @Scheduled(fixedRate = 5000)
    private void setClassroomsCount() {
        this.classroomsCount = classroomService.getClassroomsCount();
    }

    public Map<String,Long> getStatistics(){
        HashMap<String , Long> statistics = new HashMap<>();
        statistics.put("teachers",teachersCount);
        statistics.put("users",usersCount);
        statistics.put("marks",marksCount);
        statistics.put("classrooms",classroomsCount);
        return statistics;
    }
}
