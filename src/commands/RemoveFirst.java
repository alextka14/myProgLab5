package commands;

import managers.CollectionManager;
import models.Route;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда 'remove_first'. Удаляет первый элемент из коллекции.
 */
public class RemoveFirst extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveFirst(Console console, CollectionManager collectionManager) {
        super("remove_first", "удалить первый элемент из коллекции");
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

        Route firstRoute = routes.iterator().next();

        if (firstRoute != null) {
            collectionManager.remove(firstRoute.getId());
            return new ExecutionResponse("Первый маршрут успешно удален!");
        } else {
            return new ExecutionResponse(false, "Ошибка при удалении первого маршрута!");
        }
    }
}
