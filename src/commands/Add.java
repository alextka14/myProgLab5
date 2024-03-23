package commands;

import managers.CollectionManager;
import models.Route;
import utility.Ask;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда 'add'. Добавляет новый маршрут в коллекцию.
 */
public class Add extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Add(Console console, CollectionManager collectionManager) {
        super("add", "добавить новый маршрут в коллекцию");
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
        try {
            if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

            console.println("* Создание нового маршрута:");
            Route route = Ask.askRoute(console, collectionManager.getFreeId());

            if (route != null && route.validate()) {
                collectionManager.add(route);
                return new ExecutionResponse("Маршрут успешно добавлен!");
            } else return new ExecutionResponse(false, "Поля маршрута не валидны! Маршрут не добавлен!");
        } catch (Ask.AskBreak e) {
            return new ExecutionResponse(false, "Отмена...");
        }
    }
}

