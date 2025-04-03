package in.stackroute.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter @Setter @AllArgsConstructor
public class ResponseDto {
    private HttpStatus status;
    private Object message;
}
