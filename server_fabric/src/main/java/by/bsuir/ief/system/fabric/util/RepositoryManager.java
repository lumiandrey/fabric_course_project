package by.bsuir.ief.system.fabric.util;

import by.bsuir.ief.system.fabric.model.entity.fabric.*;
import by.bsuir.ief.system.fabric.model.entity.fabric.has.ComponentPartHasProductionEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasBookingEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasOutGoingConstEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasOutGoingDynamicEntity;
import by.bsuir.ief.system.fabric.model.entity.user.RoleApplicationEntity;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.repository.BaseRepository;
import by.bsuir.ief.system.fabric.model.repository.fabric.*;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IComponentPartHasProductionRepository;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IProductionHasBookingRepository;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IProductionHasOutGoingConstRepository;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IProductionHasOutGoingDynamicRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric.*;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric.has.ComponentPartHasProductionRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric.has.ProductionHasBookingRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric.has.ProductionHasOutGoingConstRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.fabric.has.ProductionHasOutGoingDynamicRepository;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.user.RoleApplicationRepositoryImpl;
import by.bsuir.ief.system.fabric.model.repository.mysql.mysqlrepository.user.UserRepositoryImpl;
import by.bsuir.ief.system.fabric.model.repository.user.RoleApplicationRepository;
import by.bsuir.ief.system.fabric.model.repository.user.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class RepositoryManager {

    private final static RepositoryManager ourInstance = new RepositoryManager();
    //----------------- user features repository ------------------------//
    private final UserRepository userRepository;
    private final RoleApplicationRepository userRoleApplicationRepository;

    //--------------------- fabric feature ------------------------//
    private final IProducerRepository producerRepository;
    private final IComponentPartRepository componentPartRepository;
    private final IProduсtionRepository produсtionRepository;
    private final IOutGoingConstRepository outGoingConstRepository;
    private final IOutGoingDynamicRepository outGoingDynamicRepository;
    private final IConsumerRepository consumerRepository;
    private final IBookingRepository bookingRepository;

    // --------------- has repository ----------------//
    private final IComponentPartHasProductionRepository componentPartHasProductionRepository;
    private final IProductionHasBookingRepository productionHasBookingRepository;
    private final IProductionHasOutGoingConstRepository productionHasOutGoingConstRepository;
    private final IProductionHasOutGoingDynamicRepository productionHasOutGoingDynamicRepository;


    private final Map<Class, BaseRepository> map = new HashMap<>();


    public static RepositoryManager getInstance() {
        return ourInstance;
    }

    private RepositoryManager() {

        //----------------- user features repository ------------------------//
        userRepository = new UserRepositoryImpl();
        userRoleApplicationRepository = new RoleApplicationRepositoryImpl();

        map.put(UserEntity.class, userRepository);
        map.put(RoleApplicationEntity.class, userRoleApplicationRepository);

        //--------------------- fabric feature ------------------------//
        producerRepository = new ProducerRepository();
        componentPartRepository = new ComponentPartRepository();
        produсtionRepository = new ProductionRepository();
        outGoingConstRepository = new OutGoingConstRepository();
        outGoingDynamicRepository = new OutGoingDynamicRepository();
        consumerRepository = new ConsumerRepository();
        bookingRepository = new BookingRepository();

        map.put(ProducerEntity.class, producerRepository);
        map.put(ComponentPartEntity.class, componentPartRepository);
        map.put(ProductionEntity.class, produсtionRepository);
        map.put(OutGoingConstEntity.class, outGoingConstRepository);
        map.put(OutGoingDynamicEntity.class, outGoingDynamicRepository);
        map.put(ConsumerEntity.class, consumerRepository);
        map.put(BookingEntity.class, bookingRepository);

        // --------------- has repository ----------------//
        componentPartHasProductionRepository = new ComponentPartHasProductionRepository();
        productionHasBookingRepository = new ProductionHasBookingRepository();
        productionHasOutGoingConstRepository = new ProductionHasOutGoingConstRepository();
        productionHasOutGoingDynamicRepository = new ProductionHasOutGoingDynamicRepository();

        map.put(ComponentPartHasProductionEntity.class, componentPartHasProductionRepository);
        map.put(ProductionHasBookingEntity.class, productionHasBookingRepository);
        map.put(ProductionHasOutGoingConstEntity.class, productionHasOutGoingConstRepository);
        map.put(ProductionHasOutGoingDynamicEntity.class, productionHasOutGoingDynamicRepository);

    }

    public <T> BaseRepository<T> getRepository(Class<T> clazz){

        return map.get(clazz);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RoleApplicationRepository getUserRoleApplicationRepository() {
        return userRoleApplicationRepository;
    }

    //-----------------Fabric features -----------------------//

    //----------------- HAS REP -------------------------//
    public IComponentPartHasProductionRepository getComponentPartHasProductionRepository() {
        return componentPartHasProductionRepository;
    }

    public IProductionHasBookingRepository getProductionHasBookingRepository() {
        return productionHasBookingRepository;
    }

    public IProductionHasOutGoingConstRepository getProductionHasOutGoingConstRepository() {
        return productionHasOutGoingConstRepository;
    }

    public IProductionHasOutGoingDynamicRepository getProductionHasOutGoingDynamicRepository() {
        return productionHasOutGoingDynamicRepository;
    }

    //--------------other features repository -----------------------//
    public IProducerRepository getProducerRepository() {
        return producerRepository;
    }

    public IComponentPartRepository getComponentPartRepository() {
        return componentPartRepository;
    }

    public IProduсtionRepository getProduсtionRepository() {
        return produсtionRepository;
    }


    public IOutGoingConstRepository getOutGoingConstRepository() {
        return outGoingConstRepository;
    }

    public IOutGoingDynamicRepository getOutGoingDynamicRepository() {
        return outGoingDynamicRepository;
    }

    public IConsumerRepository getConsumerRepository() {
        return consumerRepository;
    }

    public IBookingRepository getBookingRepository() {
        return bookingRepository;
    }

    public void release(){


    }
}
