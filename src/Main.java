import commands.*;
import managers.CollectionManager;
import managers.CommandManager;
import managers.XMLDumpManager;
import utility.Launcher;
import utility.StandartConsole;

public class Main {
    public static void main(String[] args) {
        var console = new StandartConsole();

        var xmlDumpManager = new XMLDumpManager(console);
        var collectionManager = new CollectionManager(xmlDumpManager);
        if (!collectionManager.loadCollection()) {
            System.exit(1);
        }

        var commandManager = new CommandManager() {{
            register("help", new Help(console, this));
            register("info", new Info(console, collectionManager));
            register("show", new Show(console, collectionManager));
            register("add", new Add(console, collectionManager));
            register("update", new Update(console, collectionManager));
            register("remove_by_id", new RemoveById(console, collectionManager));
            register("clear", new Clear(console, collectionManager));
            register("save", new Save(console, collectionManager));
            register("execute_script", new ExecuteScript(console));
            register("exit", new Exit(console));
            register("remove_first", new RemoveFirst(console, collectionManager));
            register("remove_last", new RemoveLast(console, collectionManager));
            register("reorder", new Reorder(console, collectionManager));
            register("remove_all_by_distance", new RemoveAllByDistance(console, collectionManager));
            register("remove_any_by_distance", new RemoveAnyByDistance(console, collectionManager));
            register("print_field_descending_distance", new PrintFieldDescendingDistance(console, collectionManager));
        }};

        new Launcher(console, commandManager).interactiveMode();
    }
}
