package by.bsuir.ief.system.fabric.util;

import by.bsuir.ief.system.fabric.controller.valid.DoubleValid;
import by.bsuir.ief.system.fabric.controller.valid.ValidField;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ValidUtil {


    public static String checkFieldStringNotEmpty(@NotNull String messageError, @NotNull TextField field) {
        String errorMessageResult = "";

        if (field.getText() == null || field.getText().length() < 2){
            errorMessageResult = messageError;
            field.setStyle(Style.getErrorStyle());
        } else {
            field.setStyle(null);
        }

        return errorMessageResult;
    }

    public static String checkFieldString(
            @NotNull String messageError,
            @NotNull TextField field,
            @NotNull ValidField<String> validation,
            @Nullable String errorValidationMessage) {
        String errorMessageResult = "";

        if (field.getText() == null){
            errorMessageResult = messageError;
            field.setStyle(Style.getErrorStyle());
        } else if (validation.isValid(field.getText())) {
            errorMessageResult = errorValidationMessage;
            field.setStyle(Style.getErrorStyle());
        } else {
            field.setStyle(null);
        }

        return errorMessageResult;
    }

    public static String checkDoubleValid(
            @NotNull TextField field,
            @NotNull String messageError,
            @Nullable String errorValidationMessage
            ) {
        String errorMessageResult = "";

        ValidField<String> doubleValid = new DoubleValid();

        if(field.getText() != null && field.getText().length() < 1) {
            errorMessageResult += messageError;
            field.setStyle(Style.getErrorStyle());
        } else if(!doubleValid.isValid(field.getText())) {
            errorMessageResult += errorValidationMessage;
            field.setStyle(Style.getErrorStyle());
        } else {
            field.setStyle(null);
        }

        return errorMessageResult;
    }
}
