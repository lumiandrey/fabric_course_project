package by.bsuir.ief.system.fabric.controller.valid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleValid implements ValidField<String>{

    private final String regex = "-?[0-9]+(.)?[0-9]*";

    private final Pattern pattern = Pattern.compile(regex);

    @Override
    public boolean isValid(String value) {

        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }
}
