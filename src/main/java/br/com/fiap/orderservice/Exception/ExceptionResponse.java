package br.com.fiap.orderservice.Exception;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String details;
    public ExceptionResponse(LocalDateTime timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
