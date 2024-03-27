package solution.test_java;

import java.util.HashMap;

abstract class Command {
    abstract int execute(String text);
}

class CountWordsCommand extends Command {
    @Override
    int execute(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }
}

public class Program {
    private HashMap<String, Command> commands;

    public Program() {
        this.commands = new HashMap<>();
    }

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public int executeCommand(String commandName, String text) {
        if (commands.containsKey(commandName)) {
            Command command = commands.get(commandName);
            return command.execute(text);
        } else {
            throw new IllegalArgumentException("Command not found");
        }
    }

    public static void main(String[] args) {
        Program program = new Program();
        program.registerCommand("CountWords", new CountWordsCommand());

        // Example usage
        String commandName = "CountWords"; // You can input this dynamically if needed
        String text = "This is a sample text."; // You can input this dynamically if needed

        try {
            int result = program.executeCommand(commandName, text);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
