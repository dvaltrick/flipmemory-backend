package com.dvaltrick.flipmemory.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import com.dvaltrick.flipmemory.models.UserEntity;
import com.dvaltrick.flipmemory.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserEntityController
 */
@CrossOrigin(origins = "*")
@RestController
public class UserEntityController {
    @Autowired
    private UserService service;

    @RequestMapping(value="/api/userentity",
            method={RequestMethod.POST},
            consumes=MediaType.APPLICATION_JSON_VALUE, 
	        produces=MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public ResponseEntity<?> post(@RequestBody UserEntity toSave) {
        try{
            return new ResponseEntity<UserEntity>(service.save(toSave), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value="/api/userentity/{id}",
            method={RequestMethod.GET}, 
	        produces=MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public ResponseEntity<?> find(@PathParam("id") Long toFind) {
        try{
            return new ResponseEntity<UserEntity>(service.find(toFind), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
}