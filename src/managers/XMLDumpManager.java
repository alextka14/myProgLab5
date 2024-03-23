package managers;

import models.Coordinates;
import models.LocationFrom;
import models.LocationTo;
import models.Route;
import utility.Console;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Set;


public class XMLDumpManager {
    private final String fileName;
    private final Console console;

    public XMLDumpManager(Console console) {
        this.fileName = System.getenv("FILENAME");
        this.console = console;
    }

    /**
     * Записывает коллекцию маршрутов в XML файл.
     *
     * @param collection коллекция маршрутов
     */
    public void writeCollection(Collection<Route> collection) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("routes");
            doc.appendChild(root);

            for (Route route : collection) {
                Element routeElement = doc.createElement("route");
                root.appendChild(routeElement);

                Element idElement = doc.createElement("id");
                idElement.appendChild(doc.createTextNode(String.valueOf(route.getId())));
                routeElement.appendChild(idElement);

                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(route.getName()));
                routeElement.appendChild(nameElement);

                Element coordinatesElement = doc.createElement("coordinates");
                routeElement.appendChild(coordinatesElement);

                Element xElement = doc.createElement("x");
                xElement.appendChild(doc.createTextNode(String.valueOf(route.getCoordinates().getX())));
                coordinatesElement.appendChild(xElement);

                Element yElement = doc.createElement("y");
                yElement.appendChild(doc.createTextNode(String.valueOf(route.getCoordinates().getY())));
                coordinatesElement.appendChild(yElement);

                Element creationDateElement = doc.createElement("creationDate");
                creationDateElement.appendChild(doc.createTextNode(route.getCreationDate().toString()));
                routeElement.appendChild(creationDateElement);

                Element fromElement = doc.createElement("from");
                routeElement.appendChild(fromElement);

                Element fromXElement = doc.createElement("x");
                fromXElement.appendChild(doc.createTextNode(String.valueOf(route.getFrom().getX())));
                fromElement.appendChild(fromXElement);

                Element fromYElement = doc.createElement("y");
                fromYElement.appendChild(doc.createTextNode(String.valueOf(route.getFrom().getY())));
                fromElement.appendChild(fromYElement);

                Element fromZElement = doc.createElement("z");
                fromZElement.appendChild(doc.createTextNode(String.valueOf(route.getFrom().getZ())));
                fromElement.appendChild(fromZElement);

                Element fromNameElement = doc.createElement("name");
                fromNameElement.appendChild(doc.createTextNode(route.getFrom().getName()));
                fromElement.appendChild(fromNameElement);

                Element toElement = doc.createElement("to");
                routeElement.appendChild(toElement);

                Element toXElement = doc.createElement("x");
                toXElement.appendChild(doc.createTextNode(String.valueOf(route.getTo().getX())));
                toElement.appendChild(toXElement);

                Element toYElement = doc.createElement("y");
                toYElement.appendChild(doc.createTextNode(String.valueOf(route.getTo().getY())));
                toElement.appendChild(toYElement);

                Element toZElement = doc.createElement("z");
                toZElement.appendChild(doc.createTextNode(String.valueOf(route.getTo().getZ())));
                toElement.appendChild(toZElement);

                Element toNameElement = doc.createElement("name");
                toNameElement.appendChild(doc.createTextNode(route.getTo().getName()));
                toElement.appendChild(toNameElement);

                Element distanceElement = doc.createElement("distance");
                distanceElement.appendChild(doc.createTextNode(String.valueOf(route.getDistance())));
                routeElement.appendChild(distanceElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fileOutputStream);
            transformer.transform(source, result);
            console.println("Коллекция маршрутов успешно сохранена в файл!");
        } catch (ParserConfigurationException | TransformerException | IOException e) {
            console.printError(e);
        }
    }

    /**
     * Считывает коллекцию из XML файла.
     */
    public Collection<Route> readCollection(List<Route> collection) {
        if (fileName != null && !fileName.isEmpty()) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(new File(fileName));

                NodeList routeNodes = doc.getElementsByTagName("route");
                for (int i = 0; i < routeNodes.getLength(); i++) {
                    Element routeElement = (Element) routeNodes.item(i);
                    long id = Long.parseLong(routeElement.getElementsByTagName("id").item(0).getTextContent());
                    String name = routeElement.getElementsByTagName("name").item(0).getTextContent();

                    Element coordinatesElement = (Element) routeElement.getElementsByTagName("coordinates").item(0);
                    long x = Long.parseLong(coordinatesElement.getElementsByTagName("x").item(0).getTextContent());
                    int y = Integer.parseInt(coordinatesElement.getElementsByTagName("y").item(0).getTextContent());

                    Element fromElement = (Element) routeElement.getElementsByTagName("from").item(0);
                    float fromX = Float.parseFloat(fromElement.getElementsByTagName("x").item(0).getTextContent());
                    int fromY = Integer.parseInt(fromElement.getElementsByTagName("y").item(0).getTextContent());
                    double fromZ = Double.parseDouble(fromElement.getElementsByTagName("z").item(0).getTextContent());
                    String fromName = fromElement.getElementsByTagName("name").item(0).getTextContent();

                    Element toElement = (Element) routeElement.getElementsByTagName("to").item(0);
                    int toX = Integer.parseInt(toElement.getElementsByTagName("x").item(0).getTextContent());
                    long toY = Long.parseLong(toElement.getElementsByTagName("y").item(0).getTextContent());
                    int toZ = Integer.parseInt(toElement.getElementsByTagName("z").item(0).getTextContent());
                    String toName = toElement.getElementsByTagName("name").item(0).getTextContent();

                    java.time.LocalDateTime creationDate = java.time.LocalDateTime.parse(routeElement.getElementsByTagName("creationDate").item(0).getTextContent());
                    double distance = Double.parseDouble(routeElement.getElementsByTagName("distance").item(0).getTextContent());

                    // Создание объекта Route и добавление его в коллекцию
                    Route route = new Route(id, name, new Coordinates(x, y), creationDate,
                            new LocationFrom(fromX, fromY, fromZ, fromName),
                            new LocationTo(toX, toY, toZ, toName),
                            distance);
                    collection.add(route);
                }

                console.println("Коллекция маршрутов успешно загружена!");
            } catch (ParserConfigurationException | SAXException | IOException e) {
                console.printError(e);
            }
        } else {
            console.printError("Переменная окружения с именем файла не найдена!");
        }
        return collection;
    }
}


