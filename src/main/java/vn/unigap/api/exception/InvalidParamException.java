package vn.unigap.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class InvalidParamException extends RuntimeException{
    private String resourceName;
    private String param;
    private int paramValue;

    public InvalidParamException(String resourceName, String param, int paramValue) {
        super(createErrorMessage(resourceName, param, paramValue));
        this.resourceName = resourceName;
        this.param = param;
        this.paramValue = paramValue;
    }

    private static String createErrorMessage(String resourceName, String param, int paramValue) {
        if(param.equalsIgnoreCase("page")){
            return String.format(" %s = %s is invalid: greater than %s", resourceName, paramValue, 0);
        }else if(param.equalsIgnoreCase("pageSize")){
            return String.format("%s = %s is invalid: not greater than %s", resourceName, paramValue, 500);
        }
        return String.format("Invalid parameter");
    }


}
