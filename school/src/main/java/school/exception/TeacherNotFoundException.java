package school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Not found teacher with this id!")
public class TeacherNotFoundException extends RuntimeException {
}
