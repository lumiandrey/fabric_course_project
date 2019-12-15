package by.bsuir.ief.system.fabric.controller.table;

import by.bsuir.ief.system.fabric.controller.Controller;

import java.util.List;

public interface TableController<T> extends Controller {

    T getSelect();
    List<T> getSelected();
    void setList(List<T> list);
    void setMultiSelect(boolean multiSelect);
    void setCallbackSelect(CallbackSelected<T> callbackSelect);
    interface CallbackSelected <T>{
        void onSelectItem(T entity);
    }
}
