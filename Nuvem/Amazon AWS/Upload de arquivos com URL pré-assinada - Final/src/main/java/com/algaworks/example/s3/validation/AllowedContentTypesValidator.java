package com.algaworks.example.s3.validation;

import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class AllowedContentTypesValidator
		implements ConstraintValidator<AllowedContentTypes, String> {

	private String[] allowedTypes = {};
	
	public final void initialize(final AllowedContentTypes annotation) {
		allowedTypes = annotation.value();
	}
	
	public final boolean isValid(final String type,
								 final ConstraintValidatorContext context) {

		if (type == null || type.isBlank()){
			return true;
		}
		
		if (allowedTypes.length == 0) {
			return true;
		}
		
		for (String allowedType : this.allowedTypes) {
			if (type.equals(allowedType)) {
				return true;
			}
		}

		var contextImpl = (ConstraintValidatorContextImpl) context;
		contextImpl.addMessageParameter("types", String.join(", ", this.allowedTypes));

		return false;
	}
}

