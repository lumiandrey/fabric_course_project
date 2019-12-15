package by.bsuir.ief.system.fabric.controller;

import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;

public interface AcceptNewClient {
    void onActionClient(String string);

    void onAcceptClient(UserEntity entity);

    void onRemoveClient(UserEntity entity);
}
