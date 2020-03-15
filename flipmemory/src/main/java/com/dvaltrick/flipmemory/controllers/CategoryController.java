package com.dvaltrick.flipmemory.controllers;

import java.util.List;

import com.dvaltrick.flipmemory.models.Category;
import com.dvaltrick.flipmemory.services.CategoryService;

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
 * CategoryController
 */
@CrossOrigin(origins = "*")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;

    @RequestMapping(value="/api/category",
            method={RequestMethod.POST},
            consumes=MediaType.APPLICATION_JSON_VALUE, 
	        produces=MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public ResponseEntity<?> post(@RequestBody Category toSave) {
        try{
            return new ResponseEntity<Category>(service.save(toSave), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value="/api/category",
            method={RequestMethod.GET},
            produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<?> getAll() {
        try{
            return new ResponseEntity<List<Category>>(service.findAll(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value="/api/category/{id}",
            method={RequestMethod.DELETE},
            produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try{
            service.delete(id);
            return new ResponseEntity<String>("OK!", HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}