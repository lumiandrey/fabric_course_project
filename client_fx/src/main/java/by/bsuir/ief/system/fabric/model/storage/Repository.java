package by.bsuir.ief.system.fabric.model.storage;

import by.bsuir.ief.system.fabric.controller.table.TableController;
import by.bsuir.ief.system.fabric.model.entity.fabric.*;
import by.bsuir.ief.system.fabric.model.entity.user.UserEntity;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractCommandShowError;
import by.bsuir.ief.system.fabric.model.storage.command.AbstractObserver;
import by.bsuir.ief.system.fabric.model.storage.command.ICommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.component_part.ReadComponentWithProducerByProductCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.component_part.ReadComponentWithProducerCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.consumer.ConsumerListCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_cost.OutGoingConstListByProductCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_cost.OutGoingConstListCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_dynamic.OutGoingDynamicListByProductCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.out_going_dynamic.OutGoingDynamicListCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.producer.ProducerListCommand;
import by.bsuir.ief.system.fabric.model.storage.command.fabric.production.ProductionListCommand;
import by.bsuir.ief.system.fabric.model.storage.command.user.GetUserListCommand;
import by.bsuir.ief.system.fabric.util.ListUtil;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import org.jetbrains.annotations.NotNull;

public class Repository {

    public static <T>Observable<T> getResult(@NotNull final ICommand<T> command) {

        return command.execute();
    }

    //------------- USER -------------//
    public static void getsUser(TableController<UserEntity> tableController) {

        Repository.getResult(new GetUserListCommand())
                .subscribe(getEntity(tableController));
    }

    //--------------Fabric ---------------//
    public static void getsProducer(TableController<ProducerEntity> tableController) {

        Repository.getResult(new ProducerListCommand())
                .subscribe(getEntity(tableController));
    }

    public static void getsConsumer(TableController<ConsumerEntity> tableController) {

        Repository.getResult(new ConsumerListCommand())
                .subscribe(getEntity(tableController));
    }

    public static void getsOutGoingDynamic(TableController<OutGoingDynamicEntity> tableController) {

        Repository.getResult(new OutGoingDynamicListCommand())
                .subscribe(getEntity(tableController));
    }

    public static void getsOutGoingDynamic(TableController<OutGoingDynamicEntity> tableController, int idProduct) {

        Repository.getResult(new OutGoingDynamicListByProductCommand(idProduct))
                .subscribe(getEntity(tableController));
    }

    public static void getsOutGoingCost(TableController<OutGoingConstEntity> tableController) {

        Repository.getResult(new OutGoingConstListCommand())
                .subscribe(getEntity(tableController));
    }

    public static void getsOutGoingCost(TableController<OutGoingConstEntity> tableController, int idProduct) {

        Repository.getResult(new OutGoingConstListByProductCommand(idProduct))
                .subscribe(getEntity(tableController));
    }

    public static void getsComponentPart(TableController<ComponentPartWithProducerEntity> tableController) {

        Repository.getResult(new ReadComponentWithProducerCommand())
                .subscribe(getEntity(tableController));
    }

    public static void getsComponentPart(TableController<ComponentPartWithProducerEntity> tableController, int idProduct) {

        Repository.getResult(new ReadComponentWithProducerByProductCommand(idProduct))
                .subscribe(getEntity(tableController));
    }

    public static void getsProduction(TableController<ProductionEntity> tableController) {

        Repository.getResult(new ProductionListCommand())
                .subscribe(getEntity(tableController));
    }

    private static <T> AbstractObserver<T[]> getEntity(TableController<T> tableController){

        return new AbstractCommandShowError<T[]>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);

                tableController.displayIndicatorProgress();
            }

            @Override
            public void onNext(T[] list) {

                tableController.setList(ListUtil.parseArray(list));
            }

            @Override
            public void onComplete() {

                super.onComplete();
                tableController.closeIndicatorProgress();
            }
        };
    }

}
