package vn.unigap.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailAlreadyExistException extends RuntimeException{
    private String email;
    private HttpStatus status;

    public EmailAlreadyExistException(HttpStatus status, String email) {
        super(String.format("Email: %s is already exists", email));
        this.status = status;
        this.email = email;
    }



}
