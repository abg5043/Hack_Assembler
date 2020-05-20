import java.util.HashMap;

public class Code {

    HashMap< String, String > cMap = new HashMap< String, String >();
    HashMap< String, String > dMap = new HashMap< String, String >();
    HashMap< String, String > jMap = new HashMap< String, String >();

    Code() {
        // Populate the Comp Mnemonics HashMap
        cMap.put( "0", "0101010" ); cMap.put( "1", "0111111" ); cMap.put( "-1", "0111010" );
        cMap.put( "D", "0001100" ); cMap.put( "A", "0110000" ); cMap.put( "M", "1110000" );
        cMap.put( "!D", "0001101" ); cMap.put( "!A", "0110001" ); cMap.put( "!M", "1110001" );
        cMap.put( "-D", "0001111" ); cMap.put( "-A", "0110011" ); cMap.put( "-M", "1110011" );
        cMap.put( "D+1", "0011111" ); cMap.put( "A+1", "0110111" ); cMap.put( "M+1", "1110111" );
        cMap.put( "D-1", "0001110" ); cMap.put( "A-1", "0110010" ); cMap.put( "M-1", "1110010" );
        cMap.put( "D+A", "0000010" ); cMap.put( "D+M", "1000010" ); cMap.put( "D-A", "0010011" );
        cMap.put( "D-M", "1010011" ); cMap.put( "A-D", "0000111" ); cMap.put( "M-D", "1000111" );
        cMap.put( "D&A", "0000000" ); cMap.put( "D&M", "1000000" ); cMap.put( "D|A", "0010101" ); cMap.put( "D|M", "1010101" );;

        // Populate the Dest Mnemonics HashMap
        dMap.put( "", "000" ); dMap.put( "M", "001" ); dMap.put( "D", "010" );
        dMap.put( "MD", "011" ); dMap.put( "A", "100" ); dMap.put( "AM", "101" );
        dMap.put( "AD", "110" ); dMap.put( "AMD", "111" );

        // Populate the Jump Mnemonics HashMap
        jMap.put( "", "000" ); jMap.put( "JGT", "001" ); jMap.put( "JEQ", "010" );
        jMap.put( "JGE", "011" ); jMap.put( "JLT", "100" ); jMap.put( "JNE", "101" );
        jMap.put( "JLE", "110" ); jMap.put( "JMP", "111" );
    }

    String dest(String mnemonic) {
        if( dMap.get(mnemonic) != null) {
            return dMap.get(mnemonic);
        } else {
            return "000";
        }
    }

    String comp(String mnemonic) {
        if( cMap.get(mnemonic) != null) {
            return cMap.get(mnemonic);
        } else {
            return null;
        }
    }

    String jump(String mnemonic) {
        if( jMap.get(mnemonic) != null) {
            return jMap.get(mnemonic);
        } else {
            return "000";
        }
    }
}

