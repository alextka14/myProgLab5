package commands;

import managers.CollectionManager;
import models.Route;
import utility.Console;
import utility.ExecutionResponse;

import java.util.Collections;
import java.util.List;

/**
 * Команда 'reorder'. Переупорядочивает коллекцию в порядке, обратном текущему.
 */
public class Reorder extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Reorder(Console console, CollectionManager collectionManager) {
        super("reorder", "переупорядочить коллекцию в порядке, обратном текущему");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }

        List<Route> routes = List.copyOf(collectionManager.getCollection());
        if (routes.isEmpty()) {
            return new ExecutionResponse(false, "Коллекция маршрутов пуста!");
        }

        Collections.reverse(routes);
        collectionManager.getCollection().clear();
        routes.forEach(route -> collectionManager.add(route));
        collectionManager.update();

        return new ExecutionResponse("Коллекция успешно переупорядочена в порядке, обратном текущему!");
    }
}
