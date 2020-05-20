import java.util.HashMap;

public class SymbolTable {
    int address = -1;
    int ramAddress = 16;
    HashMap< String, Integer > symbolMap;

    SymbolTable() {
         symbolMap = new HashMap< String, Integer >();

        // SYMBOL TABLE INITIALIZATION
        symbolMap.put( "R0", 0 ); symbolMap.put( "R1", 1 ); symbolMap.put( "R2", 2 ); symbolMap.put( "R3", 3 ); symbolMap.put( "R4", 4 ); symbolMap.put( "R5", 5 ); symbolMap.put( "R6", 6 ); symbolMap.put( "R7", 7 );
        symbolMap.put( "R8", 8 ); symbolMap.put( "R9", 9 ); symbolMap.put( "R10", 10 ); symbolMap.put( "R11", 11 ); symbolMap.put( "R12", 12 ); symbolMap.put( "R13", 13 ); symbolMap.put( "R14", 14 ); symbolMap.put( "R15", 15 );
        symbolMap.put( "SCREEN", 16384 ); symbolMap.put( "KBD", 24576 );
        symbolMap.put( "SP", 0 ); symbolMap.put( "LCL", 1 ); symbolMap.put( "ARG", 2 ); symbolMap.put( "THIS", 3 ); symbolMap.put( "THAT", 4 );
    }

    void addEntry(String symbol, int address) {
        symbolMap.put(symbol, address);
    }

    Boolean contains(String symbol) {
        return symbolMap.containsKey(symbol);
    }

    int getAddress(String symbol) {
        return symbolMap.get(symbol);
    }

}
