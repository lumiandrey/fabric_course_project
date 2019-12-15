package by.bsuir.ief.system.fabric.controller;

import by.bsuir.ief.system.fabric.controller.form.FormController;

public interface EditController <T> extends Controller {

    void setFormController(FormController<T> formController);
    void setEntity(T entity);
    boolean isValid();
    boolean isOk();
    T getEntity();
    void init();
}
