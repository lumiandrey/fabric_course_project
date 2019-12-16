package by.bsuir.ief.system.fabric.command.fabric.booking;


import by.bsuir.ief.system.fabric.command.BaseCreateCommand;
import by.bsuir.ief.system.fabric.model.entity.fabric.BookingEntityWithConsumer;
import by.bsuir.ief.system.fabric.model.entity.fabric.ProductionEntity;
import by.bsuir.ief.system.fabric.model.entity.fabric.has.ProductionHasBookingEntity;
import by.bsuir.ief.system.fabric.model.repository.fabric.has.IProductionHasBookingRepository;
import by.bsuir.ief.system.fabric.util.RepositoryManager;

public class BookingCreateCommand extends BaseCreateCommand<BookingEntityWithConsumer> {

    @Override
    public Class<BookingEntityWithConsumer> getType() {
        return BookingEntityWithConsumer.class;
    }

    @Override
    protected void onCreate(BookingEntityWithConsumer entity) throws Exception {
        long idProduction = RepositoryManager.getInstance().getBookingRepository().create(entity);

        if (idProduction > 0) {

            if (entity.getProductionEntities() != null) {
                IProductionHasBookingRepository componentPartHasProductionRepository = RepositoryManager.getInstance().getProductionHasBookingRepository();

                for (ProductionEntity componentPartEntity : entity.getProductionEntities()) {
                    componentPartHasProductionRepository.create(new ProductionHasBookingEntity(-1, (int) idProduction, componentPartEntity.getId()));
                }
            }

        } else {
            throw new Exception("Не удалось создать!");
        }
    }
}
