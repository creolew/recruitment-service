package vn.unigap.api.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericResponse {
    private int errorCode;
    private int statusCode;
    private String message;
    private Object object;

    public GenericResponse(int statusCode, Object object) {
        this.statusCode = statusCode;
        this.object = object;
    }

    public GenericResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
