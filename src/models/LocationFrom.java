package models;

import java.util.Objects;

/**
 * Класс, представляющий место окончания маршрута.
 */
public class LocationFrom {
    private float x;
    private int y;
    private Double z;
    private String name;

    /**
     * Конструктор для создания объекта места окончания маршрута.
     *
     * @param x     координата X
     * @param y     координата Y
     * @param z     координата Z
     * @param name  название места
     */
    public LocationFrom(float x, int y, double z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     * Получить координату X места.
     *
     * @return координата X
     */
    public float getX() {
        return x;
    }

    /**
     * Получить координату Y места.
     *
     * @return координата Y
     */
    public int getY() {
        return y;
    }

    /**
     * Получить координату Z места.
     *
     * @return координата Z
     */
    public Double getZ() {
        return z;
    }

    /**
     * Получить название места.
     *
     * @return название места
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationFrom that = (LocationFrom) o;
        return Float.compare(that.x, x) == 0 &&
                y == that.y &&
                Objects.equals(z, that.z) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }

    @Override
    public String toString() {
        return "LocationFrom{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}
