package com.iquestint.controller;

import com.iquestint.error.BindingErrorInfo;
import com.iquestint.error.ErrorInfo;
import com.iquestint.exception.generic.ControllerEntityAlreadyExistsException;
import com.iquestint.exception.generic.ControllerEntityBindingException;
import com.iquestint.exception.generic.ControllerEntityNotFoundException;
import com.iquestint.populator.ErrorInfoPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class is a controller advice that handles all exception thrown from the REST controllers.
 *
 * @author Georgian Alexandru
 */
@ControllerAdvice(annotations = RestController.class)
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorInfoPopulator errorInfoPopulator;

    /**
     * This method handles all exception that extends ControllerEntityBindingException.
     *
     * @param exception thrown exception
     * @return a message that contains invalid fields, exception message and the http status code
     */
    @ExceptionHandler(ControllerEntityBindingException.class)
    public ResponseEntity<BindingErrorInfo> bindingException(ControllerEntityBindingException exception) {
        BindingErrorInfo bindingErrorInfo = errorInfoPopulator.populate(exception.getErrors(), exception.getMessage(),
            HttpStatus.BAD_REQUEST);

        return new ResponseEntity<BindingErrorInfo>(bindingErrorInfo, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles all exception that extends ControllerEntityNotFoundException.
     *
     * @param exception thrown exception
     * @return a message that contains exception message and the http status code
     */
    @ExceptionHandler(ControllerEntityNotFoundException.class)
    public ResponseEntity<ErrorInfo> entityNotFoundException(ControllerEntityNotFoundException exception) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles all exception that extends ControllerEntityAlreadyExistsException.
     *
     * @param exception thrown exception
     * @return a message that contains exception message and the http status code
     */
    @ExceptionHandler(ControllerEntityAlreadyExistsException.class)
    public ResponseEntity<ErrorInfo> entityAlreadyExistsException(ControllerEntityAlreadyExistsException exception) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
    }
}
