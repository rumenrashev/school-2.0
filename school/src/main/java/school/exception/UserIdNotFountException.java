package school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "UserEntity with this id does not exists")
public class UserIdNotFountException extends RuntimeException {
}
