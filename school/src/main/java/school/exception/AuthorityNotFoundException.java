package school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "AuthorityEntity with this authority not found!")
public class AuthorityNotFoundException extends RuntimeException {
}
