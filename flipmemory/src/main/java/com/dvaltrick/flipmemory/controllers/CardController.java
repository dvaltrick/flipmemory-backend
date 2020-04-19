package com.dvaltrick.flipmemory.controllers;

import java.util.List;

import com.dvaltrick.flipmemory.models.Card;
import com.dvaltrick.flipmemory.services.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CardController
 */
@CrossOrigin(origins = "*")
@RestController
public class CardController {
    @Autowired
    private CardService service;


    @RequestMapping(value="/api/card",
            method={RequestMethod.POST},
            consumes=MediaType.APPLICATION_JSON_VALUE, 
	        produces=MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public ResponseEntity<?> post(@RequestBody Card toSave) {
        try{
            return new ResponseEntity<Card>(service.save(toSave), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value="/api/card",
            method={RequestMethod.GET},
            produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<?> getAll() {
        try{
            return new ResponseEntity<List<Card>>(service.findAll(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value="/api/card/random/{category}",
            method={RequestMethod.GET},
            produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<?> getRandom(@PathVariable("category") Long category) {
        try{
            return new ResponseEntity<Card>(service.findRandom(category), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    
}