package com.example.demo.controller;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.azure.core.exception.ResourceNotFoundException;
import com.example.demo.model.NewUserForm;

@RestController
public class NewUserController {

    @GetMapping("/user")
    public String loadFormPage(Model model) {
        model.addAttribute("newUserForm", new NewUserForm());
        return "userHome";
    }

 
    @GetMapping("/reservations/{code}")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> oneReservation(@Pattern(regexp = "^RSV(-\\d{4,}){2}$") @PathVariable String code)
        throws ResourceNotFoundException { 
    	///reservations/RSV-2021-1001
      return new ResponseEntity<>(code, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/newuser", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String submitForm(@Valid NewUserForm newUserForm, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "userHome";
        }
        model.addAttribute("message", "Valid form");
        return "userHome";
    }
} 