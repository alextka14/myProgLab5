package models;

import utility.Validatable;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Абстрактный класс, представляющий элемент коллекции.
 * Реализует интерфейсы Comparable, Validatable, Serializable.
 */
public abstract class Element implements Comparable<Element>, Validatable, Serializable, Comparator<Element> {

    /**
     * Абстрактный метод для получения идентификатора элемента.
     *
     * @return идентификатор элемента
     */
    abstract public long getId();
    abstract public int compareTo();
    abstract public int compare();
}

