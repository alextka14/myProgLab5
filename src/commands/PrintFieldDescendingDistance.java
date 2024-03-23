package commands;

import managers.CollectionManager;
import models.Route;
import utility.Console;
import utility.ExecutionResponse;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Команда 'print_field_descending_distance'. Выводит значения поля distance всех элементов в порядке убывания.
 */
public class PrintFieldDescendingDistance extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintFieldDescendingDistance(Console console, CollectionManager collectionManager) {
        super("print_field_descending_distance", "вывести значения поля distance всех элементов в порядке убывания");
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
        if (arguments.length != 0) {
            if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }

        var routes = collectionManager.getCollection().stream()
                .sorted(Comparator.comparing(Route::getDistance).reversed())
                .map(route -> route.getDistance().toString())
                .collect(Collectors.toList());

        if (routes.isEmpty()) {
            return new ExecutionResponse(false, "Коллекция пуста.");
        } else {
            console.println("Значения поля distance всех элементов в порядке убывания:");
            routes.forEach(console::println);
            return new ExecutionResponse(true, "Значения успешно выведены.");
        }
    }
}
