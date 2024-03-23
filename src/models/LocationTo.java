package models;

import java.util.Objects;

/**
 * Класс, представляющий местоположение назначения.
 */
public class LocationTo {
    private Integer x;
    private Long y;
    private Integer z;
    private String name;

    /**
     * Конструктор для создания объекта местоположения назначения.
     *
     * @param x     координата X
     * @param y     координата Y
     * @param z     координата Z
     * @param name  название местоположения
     */
    public LocationTo(Integer x, Long y, Integer z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     * Получить координату X местоположения назначения.
     *
     * @return координата X
     */
    public Integer getX() {
        return x;
    }

    /**
     * Получить координату Y местоположения назначения.
     *
     * @return координата Y
     */
    public Long getY() {
        return y;
    }

    /**
     * Получить координату Z местоположения назначения.
     *
     * @return координата Z
     */
    public Integer getZ() {
        return z;
    }

    /**
     * Получить название местоположения назначения.
     *
     * @return название местоположения
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationTo that = (LocationTo) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y) &&
                Objects.equals(z, that.z) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }

    @Override
    public String toString() {
        return "LocationTo{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}
