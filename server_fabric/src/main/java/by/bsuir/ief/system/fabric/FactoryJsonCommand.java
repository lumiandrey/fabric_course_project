package by.bsuir.ief.system.fabric;

import by.bsuir.ief.system.fabric.command.ErrorCommand;
import by.bsuir.ief.system.fabric.command.ICommand;
import org.jetbrains.annotations.NotNull;
import connection.CodeConnection;

import java.util.HashMap;
import java.util.Map;

public class FactoryJsonCommand {

    private static Map<String, ICommand> classMap = new HashMap<>();

    public static void addCommand(@NotNull final String nameCommand, final ICommand clazz) {
        classMap.put(nameCommand, clazz);
    }

    static ICommand generate(@NotNull final String nameCommand){

        ICommand command = classMap.get(nameCommand);

        if(command != null){
            //stub

        } else {
            command = new ErrorCommand(CodeConnection.BAD_REQUEST, "No support server_support this method");
        }

        return command;
    }
}
