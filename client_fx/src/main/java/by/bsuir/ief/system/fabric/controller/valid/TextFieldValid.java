package by.bsuir.ief.system.fabric.controller.valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFieldValid implements ValidField<String>{

    public static final String TEXT_PATTERN = "^[А-ЯёЁA-Z][а-яА-ЯёЁa-zA-Z]+$";
    Pattern pattern = Pattern.compile(TEXT_PATTERN);

    @Override
    public boolean isValid(String value) {

        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }
}
