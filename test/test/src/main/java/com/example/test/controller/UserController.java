/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.controller;

import com.example.test.dto.ExceptionDto;
import com.example.test.model.User;
import com.example.test.service.UserFetchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author twinkal
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserFetchService userFetchService;
    
    @Autowired
    private ExceptionDto exceptionDto;
    
    @GetMapping("/test")
    public String Hello() {
        return "Hello from UserController";
    }

    @GetMapping("/insert")
    public ResponseEntity<ExceptionDto> insertUserDetail() {
        
        	try {
			userFetchService.storeUserData();
			exceptionDto.setStatus(true);
			exceptionDto.setMessage("users Added");

			return new ResponseEntity<>(exceptionDto, new HttpHeaders(), HttpStatus.OK);

		} catch (RuntimeException e) {
			exceptionDto.setStatus(false);
			exceptionDto.setMessage("Something went wrong");
			return new ResponseEntity<>(exceptionDto, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
    }
    
    @GetMapping("/getby/id/{id}")
    public ResponseEntity<ExceptionDto> fetchUserDetailById(@PathVariable Long id) {
        
       	try {
		User list = userFetchService.getById(id);
		exceptionDto.setStatus(true);
		exceptionDto.setMessage("Id Found");
                exceptionDto.setData(list);
	        return new ResponseEntity<>(exceptionDto, new HttpHeaders(), HttpStatus.OK);

	} catch (RuntimeException e) {
		exceptionDto.setStatus(false);
		exceptionDto.setMessage("Something went wrong");
		return new ResponseEntity<>(exceptionDto, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
    }
    
    @GetMapping("/getby/userid/{userId}")
    public ResponseEntity<ExceptionDto> fetchUserDetailByUserId(@PathVariable Integer userId) {
       	try {
		List<User> list = userFetchService.getByUserId(userId);
		exceptionDto.setStatus(true);
		exceptionDto.setMessage("User Id Found");
                exceptionDto.setData(list);
	        return new ResponseEntity<>(exceptionDto, new HttpHeaders(), HttpStatus.OK);

	} catch (RuntimeException e) {
		exceptionDto.setStatus(false);
		exceptionDto.setMessage("Something went wrong");
		return new ResponseEntity<>(exceptionDto, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}        
    }

    @GetMapping("/getby/title/{title}")
    public ResponseEntity<ExceptionDto> fetchUserDetailByTitle(@PathVariable String title) {
       	try {
		List<User> list = userFetchService.getByTitle(title);
		exceptionDto.setStatus(true);
		exceptionDto.setMessage("User Title Found");
                exceptionDto.setData(list);
	        return new ResponseEntity<>(exceptionDto, new HttpHeaders(), HttpStatus.OK);

	} catch (RuntimeException e) {
		exceptionDto.setStatus(false);
		exceptionDto.setMessage("Something went wrong");
		return new ResponseEntity<>(exceptionDto, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}         
        
    }
    
}
