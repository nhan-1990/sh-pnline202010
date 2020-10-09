package com.example.shoponline1.validation;

import com.example.shoponline1.dao.IUserDao;
import com.example.shoponline1.dto.EditProfile;
import com.example.shoponline1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EditProfileValidation implements Validator {

    @Autowired
    private IUserDao userDao;

    @Override
    public boolean supports(Class<?> aClass) {

        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EditProfile user = (EditProfile) o;        
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty", "NotEmpty");
        
        /*if (!user.getPassword().equals(user.getCfpassword())) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password incorrect", "password incorrect");
        } else {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cfpassword", "NotEmpty", "NotEmpty");
        }*/
        
    }

}
