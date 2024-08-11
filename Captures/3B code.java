import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// LogLevel enum representing severity levels
enum LogLevel {
    INFO, DEBUG, ERROR
}

// Command interface declaring the execute method
interface Command {
    void execute(String message);
}

// LogCommand class implementing the Command interface
class LogCommand implements Command {
    private LogHandler handler;

    public LogCommand(LogHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(String message) {
        handler.handleRequest(message);
    }
}

// Abstract LogHandler class
abstract class LogHandler {
    protected LogHandler nextHandler;

    public void setNextHandler(LogHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(String message) {
        if (nextHandler != null) {
            nextHandler.handleRequest(message);
        }
    }
}

// InfoHandler class for handling INFO messages
class InfoHandler extends LogHandler {
    @Override
    public void handleRequest(String message) {
        if (message.startsWith("INFO:")) {
            System.out.println("INFO: " + message.substring(6));
        } else {
            super.handleRequest(message);
        }
    }
}

// DebugHandler class for handling DEBUG messages
class DebugHandler extends LogHandler {
    @Override
    public void handleRequest(String message) {
        if (message.startsWith("DEBUG:")) {
            System.out.println("DEBUG: " + message.substring(7));
        } else {
            super.handleRequest(message);
        }
    }
}

// ErrorHandler class for handling ERROR messages
class ErrorHandler extends LogHandler {
    @Override
    public void handleRequest(String message) {
        if (message.startsWith("ERROR:")) {
            System.out.println("ERROR: " + message.substring(7));
        } else {
            super.handleRequest(message);
        }
    }
}

// Logger class that uses an iterator to process a list of commands
class Logger {
    private List<Command> commandList;

    public Logger() {
        commandList = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commandList.add(command);
    }

    public void processCommands() {
        Iterator<Command> iterator = commandList.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            command.execute("INFO: Sample info message");
            command.execute("DEBUG: Sample debug message");
            command.execute("ERROR: Sample error message");
        }
    }
}

// Client class to configure and demonstrate the logging system
public class Client {
    public static void main(String[] args) {
        // Create handlers
        InfoHandler infoHandler = new InfoHandler();
        DebugHandler debugHandler = new DebugHandler();
        ErrorHandler errorHandler = new ErrorHandler();

        // Set up the chain of responsibility
        infoHandler.setNextHandler(debugHandler);
        debugHandler.setNextHandler(errorHandler);

        // Create logger
        Logger logger = new Logger();

        // Create commands for each handler
        logger.addCommand(new LogCommand(infoHandler));
        logger.addCommand(new LogCommand(debugHandler));
        logger.addCommand(new LogCommand(errorHandler));

        // Process commands
        logger.processCommands();
    }
}
