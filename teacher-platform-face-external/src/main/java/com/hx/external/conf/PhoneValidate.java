package com.hx.external.conf;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidate implements ConstraintValidator<PhoneNum, String> {


    final String phonePattern = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57]|19[0-9]|16[0-9])[0-9]{8}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        Pattern p = Pattern.compile(phonePattern);
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
