package in.vikram.expensetrackerapi.exception;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import in.vikram.expensetrackerapi.entity.ErrorObject;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorObject> handleExpenseNotFoundException(
			ResourceNotFoundException ex , WebRequest request){
		
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimestamp(new Date());
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorObject> handleMethodArgumentTypeMismatchException(Exception ex , WebRequest request){
		
		ErrorObject errorObject = new ErrorObject();
		
		errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimestamp(new Date());
		
		
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorObject> handleGeneralException(Exception ex , WebRequest request){
		
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimestamp(new Date());
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	// Handle for not valid 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		
		
		bodyMap.put("statusCode", HttpStatus.BAD_REQUEST.value());
		bodyMap.put("timestamp", new Date() );
	    List<String>msg	= ex.getBindingResult()
			  .getFieldErrors().stream().map(x->x.getDefaultMessage()).toList();
	    
		bodyMap.put("message", msg);
		return new ResponseEntity<Object>(bodyMap,HttpStatus.BAD_REQUEST);
	}
	
	
	// Item already exist exception
	
	@ExceptionHandler(ItemAlreadyExistException.class)
	public ResponseEntity<ErrorObject> handleItemAlreadyExistException(ItemAlreadyExistException exception , WebRequest request){
	    ErrorObject errorObject = new ErrorObject();
	    
	    errorObject.setMessage(exception.getMessage());
	    errorObject.setStatusCode(HttpStatus.CONFLICT.value());
	    errorObject.setTimestamp(new Date());
		
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.CONFLICT);
	}
	
	
	
	
	
	
	
}
