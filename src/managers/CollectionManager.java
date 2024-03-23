package managers;

import models.Route;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CollectionManager {
    private int currentId = 1;
    private final List<Route> collection = new ArrayList<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final XMLDumpManager xmlDumpManager;

    public CollectionManager(XMLDumpManager xmlDumpManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.xmlDumpManager = xmlDumpManager;
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public List<Route> getCollection() {
        return collection;
    }

    public Route byId(long id) {
        for (Route route : collection) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }

    public boolean contains(Route route) {
        return route == null || byId(route.getId()) != null;
    }

    public int getFreeId() {
        while (byId(currentId) != null) {
            currentId++;
        }
        return currentId;
    }

    public boolean add(Route route) {
        if (contains(route)) {
            return false;
        }
        route.setId(getFreeId());
        collection.add(route);
        update();
        return true;
    }

    public boolean remove(long id) {
        Route route = byId(id);
        if (route == null) {
            return false;
        }
        collection.remove(route);
        update();
        return true;
    }

    public void update() {
        // Метод не требуется для ArrayList
    }

    public boolean loadCollection() {
        collection.clear();
        xmlDumpManager.readCollection(collection);
        lastInitTime = LocalDateTime.now();
        return true;
    }

    public void saveCollection() {
        xmlDumpManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";

        StringBuilder info = new StringBuilder();
        for (Route route : collection) {
            info.append(route+"\n\n");
        }
        return info.toString().trim();
    }

}
