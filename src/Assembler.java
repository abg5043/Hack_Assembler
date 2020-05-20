import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;


/** This class contains an assembler for an assembly and machine language for a "hack computer".
 * This computer was built as part of the course "Building a Modern Computer From First Principals: Nand to Tetris (Part 1)
 */

public class Assembler {

    public static String toBinary(String number) {
        int intNum = Integer.parseInt(number);

        return String.format("%16s", Integer.toBinaryString(intNum)).replace(' ', '0');
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        int newRamAddress = 16;

        //Find the file and store it as temp
        Scanner fileLocation = new Scanner(System.in);
        System.out.println("What is your program called?");
        String path = fileLocation.nextLine();
        File temp = new File(path);

        //Create file to output to
        try {
            File hackFile = new File("Prog.hack");
            if (hackFile.createNewFile()) {
                System.out.println("File created: " + hackFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //Initializes a filewriter for the program
        FileWriter hackWriter = new FileWriter("Prog.hack");

        //Construct Parser and code
        Parser parserFP = new Parser(temp);
        Parser parserSP = new Parser(temp);
        Code code = new Code();
        SymbolTable symbolTable = new SymbolTable();

        //First Pass
        while(parserFP.hasMoreCommands()) {
            parserFP.advance();
            if(parserFP.commandType().equals("A_COMMAND") || parserFP.commandType().equals("C_COMMAND")) {
                symbolTable.address += 1;
            }
            if(parserFP.commandType().equals("L_COMMAND")) {
                symbolTable.addEntry(parserFP.symbol(), (symbolTable.address + 1));
            }
        }

        //Second Pass
        while(parserSP.hasMoreCommands()) {

            parserSP.advance();
            if(parserSP.commandType().equals("A_COMMAND")) {
                if(symbolTable.contains(parserSP.symbol())) {
                    int value = symbolTable.symbolMap.get(parserSP.symbol());
                    String valueStr = Integer.toString(value);
                    hackWriter.write(toBinary(valueStr) + "\n");
                } else if(isNumeric(parserSP.symbol())) {
                    hackWriter.write(toBinary(parserSP.symbol()) + "\n");
                } else {
                    symbolTable.addEntry(parserSP.symbol(), newRamAddress);
                    int value = symbolTable.symbolMap.get(parserSP.symbol());
                    String valueStr = Integer.toString(value);
                    hackWriter.write(toBinary(valueStr) + "\n");
                    newRamAddress ++;
                }

            } else if (parserSP.commandType().equals("C_COMMAND")) {
                String comp = parserSP.comp();
                String dest = parserSP.dest();
                String jump = parserSP.jump();

                String cc = code.comp(comp);
                String dd = code.dest(dest);
                String jj = code.jump(jump);


                hackWriter.write("111" + cc + dd + jj + "\n");
            }
        }
        hackWriter.close();

    }




}
