package com.example.Employee.Exception;

import com.example.Employee.Entity.Employee;
import com.example.Employee.Response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Response<Employee>>handledEmployeeNotFound
            (EmployeeNotFoundException ex){
        Response<Employee> response=new Response<>("Employee not Found",null,false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidEmpolyeeDataException.class)
    public ResponseEntity<Response<Employee>>handledInvalidEmployeeData(InvalidEmpolyeeDataException ex){
        Response<Employee> response=new Response<>
                ("Invalid Student Data",null,false);

    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Employee>>handledGenricException(Exception ex){
        Response<Employee> response= new Response<>("Something went wrong ",null,false);
        return new ResponseEntity<>(response ,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
