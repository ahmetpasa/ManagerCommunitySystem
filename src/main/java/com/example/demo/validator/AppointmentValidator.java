package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.dao.Appointment;

@Component
public class AppointmentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Appointment.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Appointment appointment = (Appointment) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateof_app", "Date.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "Time.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description_app", "Description.empty");
	}

}
