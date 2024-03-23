package commands;

import managers.CollectionManager;
import utility.Console;
import models.Route;
import utility.ExecutionResponse;

/**
 * Команда 'remove_all_by_distance'. Удаляет из коллекции все элементы с заданным значением поля distance.
 */
public class RemoveAllByDistance extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveAllByDistance(Console console, CollectionManager collectionManager) {
        super("remove_all_by_distance <distance>", "удалить из коллекции все элементы с заданным значением поля distance");
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
            int initialSize = collectionManager.getCollection().size();
            collectionManager.getCollection().removeIf(route -> route.getDistance().equals(distance));
            int removedCount = initialSize - collectionManager.getCollection().size();
            collectionManager.update();
            return new ExecutionResponse("Удалено из коллекции " + removedCount + " элементов с расстоянием " + distance);
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "Неверный формат расстояния! Убедитесь, что значение поля distance - числовое.");
        }
    }
}

