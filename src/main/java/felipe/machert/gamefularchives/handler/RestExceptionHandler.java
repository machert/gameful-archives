package felipe.machert.gamefularchives.handler;

import felipe.machert.gamefularchives.exception.BadRequestException;
import felipe.machert.gamefularchives.exception.BadRequestExceptionDetails;
import felipe.machert.gamefularchives.exception.InternalServerErrorException;
import felipe.machert.gamefularchives.exception.InternalServerErrorExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre){
        return new ResponseEntity<BadRequestExceptionDetails>(
                BadRequestExceptionDetails.builder().
                        timestamp(LocalDateTime.now()).
                        status(HttpStatus.BAD_REQUEST.value()).
                        title("Bad request exception, check the documentation").
                        details(bre.getMessage()).
                        developerMessage(bre.getClass().getName()).
                        build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<InternalServerErrorExceptionDetails> handleGenericException(InternalServerErrorException isee) {
        return new ResponseEntity<InternalServerErrorExceptionDetails>(
                InternalServerErrorExceptionDetails.builder().
                        timestamp(LocalDateTime.now()).
                        status(HttpStatus.INTERNAL_SERVER_ERROR.value()).
                        title("Internal server error exception, check the documentation").
                        details(isee.getMessage()).
                        developerMessage(isee.getClass().getName()).
                        build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
