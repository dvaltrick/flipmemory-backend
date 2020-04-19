package com.dvaltrick.flipmemory.controllers;

import java.util.List;

import com.dvaltrick.flipmemory.models.CardResponse;
import com.dvaltrick.flipmemory.models.StatsResponse;
import com.dvaltrick.flipmemory.services.CardResponseService;

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
 * CardResponseController
 */
@CrossOrigin(origins = "*")
@RestController
public class CardResponseController {
    @Autowired 
    CardResponseService service;
    
    @RequestMapping(value="/api/response",
            method={RequestMethod.POST},
            consumes=MediaType.APPLICATION_JSON_VALUE, 
	        produces=MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public ResponseEntity<?> post(@RequestBody CardResponse toSave) {
        try{
            return new ResponseEntity<CardResponse>(service.save(toSave), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value="/api/response/stats",
            method={RequestMethod.GET}, 
	        produces=MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public ResponseEntity<?> getStats() {
        try{
            return new ResponseEntity<List<StatsResponse>>(service.findStats(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}