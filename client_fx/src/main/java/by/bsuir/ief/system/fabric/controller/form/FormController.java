package by.bsuir.ief.system.fabric.controller.form;


import by.bsuir.ief.system.fabric.controller.Controller;

public interface FormController<T> extends Controller {

    void setEntity(T entity);
    void initEntity();
    boolean isValid();
    T getEntity();
}
