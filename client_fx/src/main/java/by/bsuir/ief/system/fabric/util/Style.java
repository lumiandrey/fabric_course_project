package by.bsuir.ief.system.fabric.util;

import javafx.scene.paint.Color;

public class Style {

    public static Color getColorBackground(){
        Color color = Color.valueOf("FF6B01");
        return Color.color(color.getRed(), color.getGreen(), color.getBlue(), 0.2);
    }

    /*public static Background getBackground(){
        return new Background(new BackgroundFill(Style.getColorBackground(), CornerRadii.EMPTY, Insets.EMPTY));
    }*/

    public static String getErrorStyle(){
        return "" +
                "    -fx-focus-color: #d35244;" +
                "    -fx-faint-focus-color: #d3524422;" +
                "    -fx-highlight-fill: -fx-accent;" +
                "    -fx-highlight-text-fill: white;" +
                "    -fx-background-color:" +
                "        -fx-focus-color," +
                "        -fx-control-inner-background," +
                "        -fx-faint-focus-color," +
                "        linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background);" +
                "    -fx-background-insets: -0.2, 1, -1.4, 3;" +
                "    -fx-background-radius: 3, 2, 4, 0;" +
                "    -fx-prompt-text-fill: transparent;" +
                "";
    }
}
