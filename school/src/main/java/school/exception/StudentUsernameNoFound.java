package school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Student with this username was not found!")
public class StudentUsernameNoFound extends RuntimeException {
}
