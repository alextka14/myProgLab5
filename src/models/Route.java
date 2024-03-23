package models;

import utility.Validatable;

import java.util.Objects;

/**
 * Класс, представляющий маршрут.
 */
public class Route implements Validatable {
    private long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationDate;
    private LocationFrom from;
    private LocationTo to;
    private Double distance;

    /**
     * Конструктор для создания объекта маршрута.
     *
     * @param id           уникальный идентификатор
     * @param name         название маршрута
     * @param coordinates  координаты маршрута
     * @param creationDate дата создания
     * @param from         место начала маршрута
     * @param to           место окончания маршрута
     * @param distance     дистанция маршрута
     */
    public Route(long id, String name, Coordinates coordinates, java.time.LocalDateTime creationDate, LocationFrom from, LocationTo to, Double distance) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    /**
     * Получить уникальный идентификатор маршрута.
     *
     * @return уникальный идентификатор
     */
    public long getId() {
        return id;
    }

    /**
     * Установить ID маршрута.
     *
     * @param id ID маршрута
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Получить название маршрута.
     *
     * @return название маршрута
     */
    public String getName() {
        return name;
    }

    /**
     * Получить координаты маршрута.
     *
     * @return координаты маршрута
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Получить дату создания маршрута.
     *
     * @return дата создания маршрута
     */
    public java.time.LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Получить место начала маршрута.
     *
     * @return место начала маршрута
     */
    public LocationFrom getFrom() {
        return from;
    }

    /**
     * Получить место окончания маршрута.
     *
     * @return место окончания маршрута
     */
    public LocationTo getTo() {
        return to;
    }

    /**
     * Получить дистанцию маршрута.
     *
     * @return дистанция маршрута
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * Проверяет, является ли объект маршрута допустимым.
     *
     * @return true, если объект допустим, в противном случае - false
     */
    @Override
    public boolean validate() {
        return id > 0 && name != null && !name.isEmpty() && coordinates != null &&
                creationDate != null && from != null && distance != null && distance > 1;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id &&
                Objects.equals(name, route.name) &&
                Objects.equals(coordinates, route.coordinates) &&
                Objects.equals(creationDate, route.creationDate) &&
                Objects.equals(from, route.from) &&
                Objects.equals(to, route.to) &&
                Objects.equals(distance, route.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, from, to, distance);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", from=" + from +
                ", to=" + to +
                ", distance=" + distance +
                '}';
    }

    public int compareTo(Element element) {
        return (int)(this.id - element.getId());
    }
}
