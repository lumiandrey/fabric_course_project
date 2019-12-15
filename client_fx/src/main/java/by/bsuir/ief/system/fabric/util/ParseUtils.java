package by.bsuir.ief.system.fabric.util;

import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;

public class ParseUtils {

    public static double parseDouble(@NotNull TextField field){

        try {

            return Double.parseDouble(field.getText());
        } catch (Exception e){
            e.printStackTrace();
            return 0.0;
        }
    }
}
