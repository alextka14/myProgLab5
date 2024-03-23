package commands;

import managers.CollectionManager;
import models.Route;
import utility.Console;
import utility.ExecutionResponse;

import java.util.Iterator;

/**
 * Команда 'clear'. Очищает коллекцию.
 */
public class Clear extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        Iterator<Route> iterator = collectionManager.getCollection().iterator();
        while (iterator.hasNext()) {
            Route route = iterator.next();
            iterator.remove();
            collectionManager.remove(route.getId());
        }

        return new ExecutionResponse("Коллекция маршрутов очищена!");
    }
}
