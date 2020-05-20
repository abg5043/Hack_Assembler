import java.util.Scanner;
import java.io.File;
import java.io.IOException;



public class Parser  {
    private Scanner file;
    String command;

    Parser(File f) throws IOException {
        file = new Scanner(f);
    }

    boolean hasMoreCommands() {
        return file.hasNextLine();
    }

    void advance() {
        if(hasMoreCommands()) {
            String temp = file.nextLine();
            while(temp.isEmpty() || temp.startsWith("/")) {
                temp = file.nextLine();
            }
            temp = temp.replaceAll("\\s", "");
            if(temp.indexOf("/") != -1) {
                temp = temp.substring(0, temp.indexOf("/"));
            }
            command = temp;
        }

    }

    String commandType() {
        if(command.startsWith("@")) {
            return "A_COMMAND";
        } else if(command.startsWith("(")) {
            return "L_COMMAND";
        } else {
            return "C_COMMAND";
        }
    }

    String symbol() {
        String temp = command;
        if(commandType().equals("A_COMMAND")) {
            temp = temp.replace("@", "");
        } else {
            temp = temp.replace("(", "");
            temp = temp.replace(")", "");
        }
        return temp;
    }

    String dest() {
        String temp = command;
        int startIndex = temp.indexOf("=");
        if(commandType().equals("C_COMMAND") && startIndex != -1) {
            return temp.substring(0, startIndex);
        } else {
            return null;
        }
    }

    String comp() {
        String temp = command;
        if(commandType().equals("C_COMMAND")) {
            int startIndex = temp.indexOf("=") + 1;
            int endIndex = temp.indexOf(";");
            if (endIndex == -1) {
                return temp.substring(startIndex);
            } else {
                return temp.substring(startIndex, endIndex);
            }

        } else {
            return null;
        }
    }

    String jump() {
        String temp = command;
        int startIndex = temp.indexOf(";");
        if (commandType().equals("C_COMMAND") && startIndex != -1) {
            return temp.substring(startIndex+1);
        } else {
            return null;
        }
    }

}
