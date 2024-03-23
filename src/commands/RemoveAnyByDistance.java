package commands;

import managers.CollectionManager;
import utility.Console;
import models.Route;
import utility.ExecutionResponse;

import java.util.Iterator;

/**
 * Команда 'remove_any_by_distance'. Удаляет из коллекции один элемент с заданным значением поля distance.
 */
public class RemoveAnyByDistance extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveAnyByDistance(Console console, CollectionManager collectionManager) {
        super("remove_any_by_distance <distance>", "удалить из коллекции один элемент с заданным значением поля distance");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     *
     * @param arguments Аргументы команды.
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length < 2) {
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + " <distance>'");
        }

        try {
            double distance = Double.parseDouble(arguments[1]);

            Iterator<Route> iterator = collectionManager.getCollection().iterator();
            while (iterator.hasNext()) {
                Route route = iterator.next();
                if (route.getDistance().equals(distance)) {
                    iterator.remove();
                    collectionManager.update();
                    return new ExecutionResponse("Удален элемент с расстоянием " + distance);
                }
            }

            return new ExecutionResponse(false, "Элемент с расстоянием " + distance + " не найден в коллекции.");
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "Неверный формат расстояния! Убедитесь, что значение поля distance - числовое.");
        }
    }
}
