package utility;

import models.Coordinates;
import models.LocationFrom;
import models.LocationTo;
import models.Route;

import java.util.NoSuchElementException;

/**
 * Класс Ask предоставляет методы для ввода данных о маршруте с консоли.
 */
public class Ask {
    /**
     * Исключение, используемое для выхода из цикла ввода.
     */
    public static class AskBreak extends Exception {}

    /**
     * Запрашивает данные о маршруте с консоли.
     *
     * @param console консольный интерфейс
     * @param id      идентификатор маршрута
     * @return объект маршрута
     * @throws AskBreak если пользователь завершает ввод
     */
    public static Route askRoute(Console console, long id) throws AskBreak {
        try {
            String name = askName(console);
            Coordinates coordinates = askCoordinates(console);
            java.time.LocalDateTime creationDate = java.time.LocalDateTime.now();
            LocationFrom from = askLocationFrom(console);
            LocationTo to = askLocationTo(console);
            Double distance = askDistance(console);
            return new Route(id, name, coordinates, creationDate, from, to, distance);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    private static String askName(Console console) throws AskBreak {
        try {
            String name;
            while (true) {
                console.print("name: ");
                name = console.readln().trim();
                if (name.equals("exit")) throw new AskBreak();
                if (!name.isEmpty()) {
                    break;
                }
            }
            return name;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
    private static Coordinates askCoordinates(Console console) throws AskBreak {
        try {
            Long x = askX(console);
            Integer y = askY(console);
            return new Coordinates(x, y);
        } catch (AskBreak e) {
            throw new AskBreak();
        }
    }
    private static Long askX(Console console) throws AskBreak {
        try {
            console.print("coordinates.x: ");
            var lineX = console.readln().trim();
            if (lineX.equals("exit")) throw new AskBreak();
            if (!lineX.equals("")) {
                try {
                    Long x = Long.parseLong(lineX);
                    if (x > -415) {
                        // Значение x в пределах допустимого диапазона
                        return x;
                    } else {
                        console.print("Координата x должна быть больше -415\n");
                        return askX(console);
                    }
                } catch (NumberFormatException e) {
                    console.print("Неверный формат числа\n");
                    return askX(console);
                }
            } else {
                return askX(console);
            }
        } catch (AskBreak e) {
            throw new AskBreak();
        }
    }

    private static Integer askY(Console console) throws AskBreak {
        try {
            console.print("coordinates.y: ");
            var lineY = console.readln().trim();
            if (lineY.equals("exit")) throw new AskBreak();
            if (!lineY.equals("")) {
                try {
                    Integer y = Integer.parseInt(lineY);
                    // Ваше ограничение для y здесь
                    return y;
                } catch (NumberFormatException e) {
                    console.print("Неверный формат числа\n");
                    return askY(console);
                }
            } else {
                return askY(console);
            }
        } catch (AskBreak e) {
            throw new AskBreak();
        }
    }
    private static LocationFrom askLocationFrom(Console console) throws AskBreak {
        try {
            Float x = askFromX(console, "from.x");
            Integer y = askFromY(console, "from.y");
            Double z = askFromZ(console, "from.z");
            console.print("from.name: ");
            String name = console.readln().trim();
            return new LocationFrom(x, y, z, name);
        } catch (AskBreak e) {
            throw new AskBreak();
        }
    }

    private static LocationTo askLocationTo(Console console) throws AskBreak {
        try {
            Integer x = askToX(console, "to.x");
            Long y = askToY(console, "to.y");
            Integer z = askToZ(console, "to.z");
            console.print("to.name: ");
            String name = console.readln().trim();
            return new LocationTo(x, y, z, name);
        } catch (AskBreak e) {
            throw new AskBreak();
        }
    }

    private static Integer askToX(Console console, String prompt) throws AskBreak {
        try {
            console.print(prompt + ": ");
            var lineX = console.readln().trim();
            if (lineX.equals("exit")) throw new AskBreak();
            if (!lineX.equals("")) {
                try {
                    return Integer.parseInt(lineX);
                } catch (NumberFormatException e) {
                    console.print("Неверный формат числа\n");
                    return askToX(console, prompt);
                }
            } else {
                return askToX(console, prompt);
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения\n");
            throw new AskBreak();
        }
    }

    private static Long askToY(Console console, String prompt) throws AskBreak {
        try {
            console.print(prompt + ": ");
            var lineY = console.readln().trim();
            if (lineY.equals("exit")) throw new AskBreak();
            if (!lineY.equals("")) {
                try {
                    return Long.parseLong(lineY);
                } catch (NumberFormatException e) {
                    console.print("Неверный формат числа\n");
                    return askToY(console, prompt);
                }
            } else {
                return askToY(console, prompt);
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            console.print("Ошибка чтения\n");
            throw new AskBreak();
        }
    }

    private static Integer askToZ(Console console, String prompt) throws AskBreak {
        try {
            console.print(prompt + ": ");
            var lineZ = console.readln().trim();
            if (lineZ.equals("exit")) throw new AskBreak();
            if (!lineZ.equals("")) {
                try {
                    return Integer.parseInt(lineZ);
                } catch (NumberFormatException e) {
                    console.print("Неверный формат числа\n");
                    return askToZ(console, prompt);
                }
            } else {
                return askToZ(console, prompt);
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            console.print("Ошибка чтения\n");
            throw new AskBreak();
        }
    }


    private static Float askFromX(Console console, String prompt) throws AskBreak {
        try {
            console.print(prompt + ": ");
            var lineX = console.readln().trim();
            if (lineX.equals("exit")) throw new AskBreak();
            if (!lineX.equals("")) {
                try {
                    return Float.parseFloat(lineX);
                } catch (NumberFormatException e) {
                    console.print("Неверный формат числа\n");
                    return askFromX(console, prompt);
                }
            } else {
                return askFromX(console, prompt);
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            console.print("Ошибка чтения\n");
            throw new AskBreak();
        }
    }

    private static Integer askFromY(Console console, String prompt) throws AskBreak {
        try {
            console.print(prompt + ": ");
            var lineY = console.readln().trim();
            if (lineY.equals("exit")) throw new AskBreak();
            if (!lineY.equals("")) {
                try {
                    return Integer.parseInt(lineY);
                } catch (NumberFormatException e) {
                    console.print("Неверный формат числа\n");
                    return askFromY(console, prompt);
                }
            } else {
                return askFromY(console, prompt);
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            console.print("Ошибка чтения\n");
            throw new AskBreak();
        }
    }

    private static Double askFromZ(Console console, String prompt) throws AskBreak {
        try {
            console.print(prompt + ": ");
            var lineZ = console.readln().trim();
            if (lineZ.equals("exit")) throw new AskBreak();
            if (!lineZ.equals("")) {
                try {
                    return Double.parseDouble(lineZ);
                } catch (NumberFormatException e) {
                    console.print("Неверный формат числа\n");
                    return askFromZ(console, prompt);
                }
            } else {
                return askFromZ(console, prompt);
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            console.print("Ошибка чтения\n");
            throw new AskBreak();
        }
    }



    private static Double askDistance(Console console) throws AskBreak {
        try {
            Double distance;
            while (true) {
                console.print("distance: ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new AskBreak();
                if (!line.equals("")) {
                    try {
                        distance = Double.parseDouble(line);
                        if (distance <= 1) {
                            console.print("Дистанция должна быть больше 1\n");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        console.print("Некорректный формат дистанции\n");
                    }
                }
            }
            return distance;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
}
