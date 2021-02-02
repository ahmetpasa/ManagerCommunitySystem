package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.dao.FamilyMember;

@Component
public class MemberValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return FamilyMember.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FamilyMember familyMember = (FamilyMember) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Name.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "Surname.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "Telephone.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "Address.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "Birthday.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "degree", "Degree.empty");		
	}

}
