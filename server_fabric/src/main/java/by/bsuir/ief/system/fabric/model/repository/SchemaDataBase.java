package by.bsuir.ief.system.fabric.model.repository;

public class SchemaDataBase {

    //-----------------user table -----------------------//

    public static class RoleApplication {
        public static final String TABLE = "role_application";

        public static final String ID = "id_role_application";
        public static final String NAME = "name";
        public static final String LEVEL = "level";
    }

    public static class User {
        public static final String TABLE = "user";

        public static final String ID = "id_user";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String STATUS = "status";
        public static final String ID_ROLE_APPLICATION = "id_role_application";
    }

    //-------------------Fabric table ---------------------//

    public static class Producer {
        public static final String TABLE = "producer";

        public static final String ID = "id_producer";
        public static final String NAME = "name";
    }

    public static class ComponentPart {
        public static final String TABLE = "component_parts";

        public static final String ID = "id_component_parts";
        public static final String NAME = "name";
        public static final String COST = "cost";//double
        public static final String ID_PRODUCER = "producer_id_producer";

    }

    public static class ComponentPartHasProduction {
        public static final String TABLE = "component_parts_has_production";

        public static final String ID = "id_component_parts_has_production";
        public static final String ID_PRODUCTION = "production_id_production";
        public static final String ID_COMPONENT = "component_parts_id_component_parts";
    }

    public static class Production {

        public static final String TABLE = "production";

        public static final String ID = "id_production";
        public static final String NAME = "name";
        public static final String DESCRIBE = "description";

    }

    public static class OutGoingConst {
        public static final String TABLE = "outgoing_const";

        public static final String ID = "id_outgoing_const";
        public static final String NAME = "outgoing_name";
        public static final String COST = "cost";//double
    }

    public static class ProductionHasOutGoingConst {
        public static final String TABLE = "production_has_outgoing_const";

        public static final String ID = "id_production_has_outgoing_const";
        public static final String ID_OUTGOING_CONST = "outgoing_const_id_outgoing_const";
        public static final String ID_PRODUCTION = "production_id_production";
    }

    public static class OutGoingDynamic {
        public static final String TABLE = "outgoing_dynamic";

        public static final String ID = "id_outgoing_dynamic";
        public static final String NAME = "outgoing_name";
        public static final String COST = "cost";//double
    }

    public static class ProductionHasOutGoingDynamic {
        public static final String TABLE = "production_has_outgoing_dynamic";

        public static final String ID = "id_production_has_outgoing_dynamic";
        public static final String ID_OUTGOING_DYNAMIC = "outgoing_dynamic_id_outgoing_dynamic";
        public static final String ID_PRODUCTION = "production_id_production";
    }

    public static class Consumer {
        public static final String TABLE = "consumer";

        public static final String ID = "id_consumer";
        public static final String NAME = "name";
    }

    public static class Booking {
        public static final String TABLE = "booking";

        public static final String ID = "id_booking";
        public static final String NAME = "booking";
        public static final String ID_CONSUMER = "consumer_id_consumer";
        public static final String COEF_MULTIPLY = "coef_multiply";
    }

    public static class ProductionHasBooking {
        public static final String TABLE = "production_has_booking";

        public static final String ID = "id_production_has_booking";
        public static final String ID_PRODUCTION = "production_id_production";
        public static final String ID_BOOKING = "booking_id_booking";
    }
}
