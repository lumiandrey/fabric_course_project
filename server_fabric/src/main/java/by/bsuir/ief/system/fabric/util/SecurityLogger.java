package by.bsuir.ief.system.fabric.util;

import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SecurityLogger {

    public static final String X_AUTH = "X-Auth";

    private static volatile SecurityLogger ourInstance = null;

    public static SecurityLogger getInstance() {
        if(ourInstance == null)
            synchronized (SecurityLogger.class){
                if(ourInstance==null)
                    ourInstance = new SecurityLogger();
            }
        return ourInstance;
    }

    private SecurityLogger() {
    }

    private Map<String, UserEntity> mapTokenUser = new ConcurrentHashMap<String, UserEntity>();

    public synchronized boolean isAccess(final String token) throws AccessDeniedException {

        UserEntity user = mapTokenUser.get(token);

        if(user == null)
            throw new AccessDeniedException("Ошибка доступа для выполнения операции. ");

        return true;
    }

    public synchronized boolean removeTokenUser(final String token){

        if(token != null && mapTokenUser.get(token) != null){

            mapTokenUser.remove(token);
            return true;
        }
        return false;
    }

    public synchronized UserEntity getUser(final String token){
        return mapTokenUser.get(token);
    }

    private String getHach(final UserEntity user){
        return Crypto.sha256(user.getLogin() + user.getPassword() + System.currentTimeMillis());
    }

    public synchronized boolean isAutorizen(final UserEntity user){

        return mapTokenUser.get(getHach(user)) != null;
    }

    public synchronized void deleteUser(UserEntity entity){

        for(Map.Entry<String, UserEntity> entityEntry : mapTokenUser.entrySet()){

            UserEntity userEntity = entityEntry.getValue();
            if(userEntity != null && userEntity.getIdUser() == entity.getIdUser()){
                mapTokenUser.remove(entityEntry.getKey());
            }
        }
    }

    public synchronized  boolean isAutorizen(final String token){

        return mapTokenUser.get(token) != null;
    }

    public synchronized String addUserToken(final UserEntity user){

        String generatedToken = getHach(user);

        if(!isAutorizen(generatedToken)){

            mapTokenUser.putIfAbsent(generatedToken, user);
        }
        return generatedToken;
    }
}
