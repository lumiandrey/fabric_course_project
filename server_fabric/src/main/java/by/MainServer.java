package by;

import by.bsuir.ief.system.fabric.FactoryJsonCommand;
import by.bsuir.ief.system.fabric.command.fabric.booking.*;
import by.bsuir.ief.system.fabric.command.fabric.component_parts.*;
import by.bsuir.ief.system.fabric.command.fabric.consumer.*;
import by.bsuir.ief.system.fabric.command.fabric.outgoing_const.*;
import by.bsuir.ief.system.fabric.command.fabric.outgoing_dynamic.*;
import by.bsuir.ief.system.fabric.command.fabric.producer.*;
import by.bsuir.ief.system.fabric.command.fabric.production.*;
import by.bsuir.ief.system.fabric.command.user.*;
import by.bsuir.ief.system.fabric.controller.ConnectionSettingController;
import by.bsuir.ief.system.fabric.controller.DialogManager;
import by.bsuir.ief.system.fabric.model.RoleApp;
import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntityWithConsumer;
import by.bsuir.ief.system.fabric.model.entity.user.RoleApplicationEntity;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.repository.mysql.ConnectionPool;
import by.bsuir.ief.system.fabric.util.Crypto;
import by.bsuir.ief.system.fabric.util.RepositoryManager;
import by.bsuir.ief.system.fabric.util.SecurityLogger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class MainServer extends Application {


    private static Thread threadServer = null;

    private static ThreadGroup threadGroup = null;

    private Stage primaryStage;


    /**
     * Host for SUB
     */
    private static String hostSQL = "jdbc:mysql://localhost:3306/";
    /**
     * The name for SUB
     */
    private static String nameDataBase = "fabric";
    /**
     * Login to log in to the DBMS server_support
     */
    private static String loginUserDataBase = "root";
    /**
     * Password to log in to the DBMS server_support
     */
    private static String passwordUserDataBase = "root";

    @Override
    public void init() throws Exception {
        super.init();

        try {

            ConnectionPool.initPool(
                    hostSQL,
                    nameDataBase,
                    loginUserDataBase,
                    passwordUserDataBase,
                    "com.mysql.cj.jdbc.Driver",
                    30);

            SecurityLogger.getInstance().removeTokenUser(null);
        } catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }

    public static void main(String[]args) {

        launch(args);
    }
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {


        try {
            List<RoleApplicationEntity> roleApplicationEntities = RepositoryManager.getInstance().getUserRoleApplicationRepository().read();

            if(roleApplicationEntities.size() < 1) {

                for (RoleApp role: RoleApp.values()) {
                    RoleApplicationEntity roleApplicationEntity = new RoleApplicationEntity();

                    roleApplicationEntity.setLevel(role.getLevel());
                    roleApplicationEntity.setName(role.name());

                    RepositoryManager.getInstance().getUserRoleApplicationRepository().create(roleApplicationEntity);
                }
            }

            List<UserEntity> userEntities = RepositoryManager.getInstance().getUserRepository().read();

            if(userEntities.size() < 1){
                roleApplicationEntities = RepositoryManager.getInstance().getUserRoleApplicationRepository().read();

                RoleApplicationEntity roleApplicationEntity = null;

                for(RoleApplicationEntity entity: roleApplicationEntities) {

                    if(entity.getName().equals(RoleApp.ADMIN.name())){
                        roleApplicationEntity = entity;
                        break;
                    }
                }

                UserEntity entity = new UserEntity();

                entity.setLogin("root");
                entity.setPassword(Crypto.sha256("root"));
                entity.setStatus(1);

                entity.setRoleApplication(roleApplicationEntity);

                RepositoryManager.getInstance().getUserRepository().create(entity);
            }

            initUserMethod();

            //-------------- fabric methods -----------------//
            initProducerCommands();

            initComponentPartCommands();

            initBookingCommands();

            initProductionCommands();

            initConsumerCommands();

            initOutGoingConstCommands();

            initOutGoingDynamicCommands();

            //List<ComponentPartWithProducerEntity> entities = RepositoryManager.getInstance().getComponentPartRepository().readComponentPartWithProducerListByProduction(1);

            List<BookingEntityWithConsumer> entities1 = RepositoryManager.getInstance().getBookingRepository().readBookingEntityWithConsumerList();
            //List<OutGoingDynamicEntity> entities2 =  RepositoryManager.getInstance().getOutGoingDynamicRepository().readOutGoingByProduction(1);

            this.primaryStage = primaryStage;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ConnectionSetting.fxml"));
            BorderPane rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            ConnectionSettingController controller = loader.getController();
            controller.setPrimaryStage(this.primaryStage);
            primaryStage.show();

        } catch (Exception e){

            DialogManager.showErrorDialog("Ошибка при запуске приложения",
                    "При запуске приложения произошла ошибка. " + e.getMessage());
        }
    }

    public static Thread getThreadServer() {
        return threadServer;
    }

    public static void setThreadServer(Thread threadServer) {
        MainServer.threadServer = threadServer;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     * <p>
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     */
    @Override
    public void stop() throws Exception {

        try {
            if (threadGroup != null)
                threadGroup.destroy();

        } catch (IllegalThreadStateException e) {

        }finally {
            super.stop();
        }
        System.exit(0);
    }

    public static ThreadGroup getThreadGroup() {
        return threadGroup;
    }

    public static void setThreadGroup(ThreadGroup threadGroup) {
        MainServer.threadGroup = threadGroup;
    }

    private void initUserMethod() {

        FactoryJsonCommand.addCommand("adduser", new UserCreateCommand());

        FactoryJsonCommand.addCommand("updateuser", new UserUpdateCommand());

        FactoryJsonCommand.addCommand("readusers", new UserReadListCommand());

        FactoryJsonCommand.addCommand("readbyiduser", new UserReadByIdCommand());

        FactoryJsonCommand.addCommand("deleteuser", new UserDeleteByIdCommand());

        FactoryJsonCommand.addCommand("registration", new RegistrationCommand());

        FactoryJsonCommand.addCommand("checklogin", new UserCheckLoginCommand());

        FactoryJsonCommand.addCommand("getroleapplicationregistration", new GetRoleApplicationRegistrationCommand());

        FactoryJsonCommand.addCommand("getlistroleapplication", new RoleApplicationListReadCommand());

        FactoryJsonCommand.addCommand("getUser", new GetUserCommand());
    }

    //----------------Fabric method ----------------//
    private void initProducerCommands() {
        FactoryJsonCommand.addCommand("add.producer", new ProducerCreateCommand());

        FactoryJsonCommand.addCommand("update.producer", new ProducerUpdateCommand());

        FactoryJsonCommand.addCommand("list.producer", new ProducerReadListCommand());

        FactoryJsonCommand.addCommand("delete.producer.id", new ProducerDeleteByIdCommand());

        FactoryJsonCommand.addCommand("read.by.id.user", new ProducerReadByIdCommand());

    }

    private void initComponentPartCommands() {
        FactoryJsonCommand.addCommand("add.component.part", new ComponentPartCreateCommand());

        FactoryJsonCommand.addCommand("update.component.part", new ComponentPartUpdateCommand());

        FactoryJsonCommand.addCommand("list.component.part", new ComponentPartReadListCommand());

        FactoryJsonCommand.addCommand("list.component.part.producer", new ReadComponentWithProducerCommand());

        FactoryJsonCommand.addCommand("list.component.part.production", new ReadComponentWithProducerByProductCommand());

        FactoryJsonCommand.addCommand("delete.component.part.id", new ComponentPartDeleteByIdCommand());

        FactoryJsonCommand.addCommand("read.component.part", new ComponentPartReadByIdCommand());
    }

    private void initBookingCommands() {
        FactoryJsonCommand.addCommand("add.booking", new BookingCreateCommand());

        FactoryJsonCommand.addCommand("update.booking", new BookingUpdateCommand());

        FactoryJsonCommand.addCommand("list.booking", new BookingReadListCommand());

        FactoryJsonCommand.addCommand("delete.booking", new BookingDeleteByIdCommand());

        FactoryJsonCommand.addCommand("read.booking", new BookingReadByIdCommand());
    }

    private void initProductionCommands() {
        FactoryJsonCommand.addCommand("add.production", new ProductionCreateCommand());

        FactoryJsonCommand.addCommand("update.production", new ProductionUpdateCommand());

        FactoryJsonCommand.addCommand("list.production", new ProductionReadListCommand());

        FactoryJsonCommand.addCommand("delete.production.id", new ProductionDeleteByIdCommand());

        FactoryJsonCommand.addCommand("read.production", new ProductionReadByIdCommand());

        FactoryJsonCommand.addCommand("action.cost.outgoing.production", new CalculationOutGoingProduction());
    }

    private void initConsumerCommands() {
        FactoryJsonCommand.addCommand("add.consumer", new ConsumerCreateCommand());

        FactoryJsonCommand.addCommand("update.consumer", new ConsumerUpdateCommand());

        FactoryJsonCommand.addCommand("list.consumer", new ConsumerReadListCommand());

        FactoryJsonCommand.addCommand("delete.consumer.id", new ConsumerDeleteByIdCommand());

        FactoryJsonCommand.addCommand("read.consumer", new ConsumerReadByIdCommand());
    }

    private void initOutGoingConstCommands() {
        FactoryJsonCommand.addCommand("add.out.going.const", new OutGoingConstCreateCommand());

        FactoryJsonCommand.addCommand("update.out.going.const", new OutGoingConstUpdateCommand());

        FactoryJsonCommand.addCommand("list.out.going.const", new OutGoingConstReadListCommand());

        FactoryJsonCommand.addCommand("delete.out.going.const.id", new OutGoingConstDeleteByIdCommand());

        FactoryJsonCommand.addCommand("list.out.going.const.product.id", new ReadOutGoingConstByProductCommand());

        FactoryJsonCommand.addCommand("read.out.going.const", new OutGoingConstReadByIdCommand());
    }

    private void initOutGoingDynamicCommands() {
        FactoryJsonCommand.addCommand("add.out.going.dynamic", new OutGoingDynamicCreateCommand());

        FactoryJsonCommand.addCommand("update.out.going.dynamic", new OutGoingDynamicUpdateCommand());

        FactoryJsonCommand.addCommand("list.out.going.dynamic", new OutGoingDynamicReadListCommand());

        FactoryJsonCommand.addCommand("delete.out.going.dynamic.id", new OutGoingDynamicDeleteByIdCommand());

        FactoryJsonCommand.addCommand("list.out.going.dynamic.product.id", new ReadOutGoingDynamictByProductCommand());

        FactoryJsonCommand.addCommand("read.out.going.dynamic", new OutGoingDynamicReadByIdCommand());
    }
}
