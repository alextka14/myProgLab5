package models;

/**
 * Класс, представляющий координаты.
 */
public class Coordinates {
    private long x;
    private int y;

    /**
     * Конструктор для инициализации координат.
     *
     * @param x координата X
     * @param y координата Y
     */
    public Coordinates(long x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает координату X.
     *
     * @return значение координаты X
     */
    public long getX() {
        return x;
    }

    /**
     * Возвращает координату Y.
     *
     * @return значение координаты Y
     */
    public int getY() {
        return y;
    }

    /**
     * Переопределение метода toString для вывода информации о координатах.
     *
     * @return строковое представление объекта Coordinates
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
