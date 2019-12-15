package by.bsuir.ief.system.fabric.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtil {

    public static <TYPE> ObservableList<TYPE> parseListToObserv(ObservableList<TYPE> observableList, List<TYPE> entityList){
        if(observableList.size() > 0) {
            observableList.clear();
            observableList = null;
        }
        observableList = FXCollections.observableArrayList();
        if(entityList != null) {
            observableList.setAll(entityList);

        }
        return observableList;
    }

    public static <T>List<T> parseArray(T[] entities){

        return entities != null ? Arrays.asList(entities) : new ArrayList<>();

    }
}
