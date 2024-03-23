package commands;

import managers.CollectionManager;
import models.Route;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда 'remove_last'. Удаляет последний элемент из коллекции.
 */
public class RemoveLast extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveLast(Console console, CollectionManager collectionManager) {
        super("remove_last", "удалить последний элемент из коллекции");
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

        var routes = collectionManager.getCollection();
        if (routes.isEmpty()) {
            return new ExecutionResponse(false, "Коллекция маршрутов пуста!");
        }

        Route lastRoute = null;
        for (Route route : routes) {
            lastRoute = route;
        }

        if (lastRoute != null) {
            collectionManager.remove(lastRoute.getId());
            return new ExecutionResponse("Последний маршрут успешно удален!");
        } else {
            return new ExecutionResponse(false, "Ошибка при удалении последнего маршрута!");
        }
    }
}

