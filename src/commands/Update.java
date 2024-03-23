package commands;

import managers.CollectionManager;
import models.Route;
import utility.Ask;
import utility.Console;
import utility.ExecutionResponse;

/**
 * Команда 'update'. Удаляет элемент по ID и добавляет новый элемент.
 */
public class Update extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Update(Console console, CollectionManager collectionManager) {
        super("update <ID>", "удалить элемент по ID и добавить новый элемент");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду.
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (arguments.length < 2) {
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }

        long id;
        try {
            id = Long.parseLong(arguments[1]);
        } catch (NumberFormatException e) {
            return new ExecutionResponse(false, "Неверный формат ID");
        }

        if (collectionManager.byId(id) == null) {
            return new ExecutionResponse(false, "Элемент с указанным ID не найден");
        }

        try {
            // Удаляем элемент по ID
            collectionManager.remove(id);

            // Запрашиваем данные для нового маршрута
            console.println("* Создание нового маршрута:");
            Route newRoute = Ask.askRoute(console, id);

            if (newRoute != null && newRoute.validate()) {
                // Добавляем новый маршрут в коллекцию
                collectionManager.add(newRoute);
                return new ExecutionResponse(true, "Маршрут успешно обновлен");
            } else {
                return new ExecutionResponse(false, "Поля маршрута не валидны! Маршрут не обновлен");
            }
        } catch (Ask.AskBreak e) {
            // Обработка отмены ввода пользователем
            return new ExecutionResponse(false, "Отмена обновления маршрута");
        }
    }
}
