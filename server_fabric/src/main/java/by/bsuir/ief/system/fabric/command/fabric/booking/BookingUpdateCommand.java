package by.bsuir.ief.system.fabric.command.fabric.booking;

import by.bsuir.ief.system.fabric.command.BaseUpdateCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntityWithConsumer;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProductionEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasBookingEntity;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IProductionHasBookingRepository;
import by.bsuir.ief.system.fabric.util.RepositoryManager;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BookingUpdateCommand extends BaseUpdateCommand<BookingEntityWithConsumer> {

    @Override
    public Class<BookingEntityWithConsumer> getType() {
        return BookingEntityWithConsumer.class;
    }

    @Override
    protected BookingEntityWithConsumer onUpdate(BookingEntityWithConsumer entity) throws Exception {

        BookingEntity updateEntity = RepositoryManager.getInstance().getBookingRepository().update(
                new BookingEntity(
                        entity.getId(),
                        entity.getName(),
                        entity.getCoefMultiply(),
                        entity.getIdConsumer()
                )
        );

        if (updateEntity != null) {

            //------------- ComponentPartHasProductionEntity update logic---------------//
            List<? extends ProductionEntity> componentPartEntityList = entity.getProductionEntities();

            if (componentPartEntityList != null && !componentPartEntityList.isEmpty()) {

                IProductionHasBookingRepository componentPartHasProductionRepository = RepositoryManager.getInstance().getProductionHasBookingRepository();
                List<ProductionHasBookingEntity> currentDataBaseIdPartHasProduction
                        = componentPartHasProductionRepository.getListByBooking(updateEntity.getId());

                Set<Integer> createSetComponentPartHasProductionEntity = new LinkedHashSet<>();
                Set<ProductionHasBookingEntity> deleteSetComponentPartHasProductionEntity = new LinkedHashSet<>();

                //---------  Find removed component entity
                for (ProductionHasBookingEntity currentDbEntity : currentDataBaseIdPartHasProduction) {
                    int idCurrentDbEntity = currentDbEntity.getIdProduction();

                    if (!isContainsComponentPartHasProductEntity(componentPartEntityList, idCurrentDbEntity)) {
                        deleteSetComponentPartHasProductionEntity.add(currentDbEntity);
                    }
                }
                //------- find create entity
                for (ProductionEntity componentPartEntity : componentPartEntityList) {
                    int id = componentPartEntity.getId();
                    if (!isContainsComponentPartEntity(currentDataBaseIdPartHasProduction, id)) {
                        createSetComponentPartHasProductionEntity.add(id);
                    }
                }

                //--------- create process (not optimized) for optimization need adding logic for update or delete entity
                //--------- delete entity -----------
                for (ProductionHasBookingEntity deleteEntity : deleteSetComponentPartHasProductionEntity) {
                    componentPartHasProductionRepository.delete(deleteEntity.getId());
                }

                for (Integer createId : createSetComponentPartHasProductionEntity) {
                    componentPartHasProductionRepository.create(new ProductionHasBookingEntity(-1, updateEntity.getId(), createId));
                }

            }

        } else {
            throw new Exception("Не удалось создать!");
        }

        return entity;
    }

    private boolean isContainsComponentPartHasProductEntity(@NotNull List<? extends ProductionEntity> componentPartEntityList, int idCurrentDbEntity) {

        for (ProductionEntity componentPartEntity : componentPartEntityList) {

            if (componentPartEntity.getId() == idCurrentDbEntity)
                return true;
        }

        return false;
    }

    private boolean isContainsComponentPartEntity(@NotNull List<ProductionHasBookingEntity> componentPartEntityList, int IdComponent) {

        for (ProductionHasBookingEntity componentPartEntity : componentPartEntityList) {

            if (componentPartEntity.getIdProduction() == IdComponent)
                return true;
        }

        return false;
    }
}
