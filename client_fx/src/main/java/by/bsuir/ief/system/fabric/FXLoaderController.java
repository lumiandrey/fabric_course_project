package by.bsuir.ief.system.fabric;

import by.bsuir.ief.system.fabric.controller.Controller;
import by.bsuir.ief.system.fabric.controller.EditController;
import by.bsuir.ief.system.fabric.controller.form.FormController;
import by.bsuir.ief.system.fabric.controller.table.TableController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class FXLoaderController {

    private final static String PREF_PATH_BUSINESS = "fabric/";

    public static Controller loadController(final String nameViewFxml) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXLoaderController.class.getResource("/view/"+ nameViewFxml +".fxml"));

        loader.load();
        return loader.getController();
    }

    public static <T> EditController<T> loadEditController(final String nameViewEditFxml) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXLoaderController.class.getResource("/view/"+ nameViewEditFxml +".fxml"));

        loader.load();
        return loader.getController();
    }

    public static <T> FormController<T> loadFormController(final String nameViewFormFxml) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXLoaderController.class.getResource("/view/form/"+ nameViewFormFxml +".fxml"));

        loader.load();
        return loader.getController();
    }

    public static <T> FormController<T> loadBusinessFormController(final String nameViewFormFxml) throws IOException {
        return loadFormController(PREF_PATH_BUSINESS + nameViewFormFxml);
    }

    public static <T>TableController<T> loadTableController(final String nameTableFxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXLoaderController.class.getResource("/view/table/"+ nameTableFxml +".fxml"));

        loader.load();
        return loader.getController();
    }

    public static <T> TableController<T> loadBusinessTableController(final String nameTableFxml) throws IOException {
        return loadTableController(PREF_PATH_BUSINESS + nameTableFxml);
    }

    public static FXMLLoader load(final String nameViewFxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXLoaderController.class.getResource("/view/" + nameViewFxml +".fxml"));
        loader.load();

        return loader;
    }
}
