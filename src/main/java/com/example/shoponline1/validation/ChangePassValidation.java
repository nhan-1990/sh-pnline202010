package com.example.shoponline1.validation;

import com.example.shoponline1.dao.IUserDao;
import com.example.shoponline1.dto.ChangePassForm;
import com.example.shoponline1.dto.EditProfile;
import com.example.shoponline1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ChangePassValidation implements Validator {

    @Autowired
    private IUserDao userDao;

    @Override
    public boolean supports(Class<?> aClass) {

        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ChangePassForm changePassForm = (ChangePassForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPass", "NotEmpty", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comfirmPass", "NotEmpty", "NotEmpty");        
        
    }

}
