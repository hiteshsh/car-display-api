package apis.cardetails;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorResponse {
    private String timestamp;
    private String status;
    private String error;
    private String exception;
    private String message;
    private String trace;
    private String path;
}
