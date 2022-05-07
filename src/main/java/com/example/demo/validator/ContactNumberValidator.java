package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {

	@Override
	public void initialize(ContactNumberConstraint contactNumber) {
	}

	@Override
	public boolean isValid(String contactField, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return contactField != null && contactField.matches("[0-9]+") && (contactField.length() > 8)
				&& (contactField.length() < 14);
	}

}