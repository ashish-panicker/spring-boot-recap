package in.stackroute.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ResponseDto {
    private HttpStatus status;
    private Object message;
}
