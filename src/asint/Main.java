package asint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Reader input = new InputStreamReader(new FileInputStream("input6.txt"));
		AnalizadorSintactico asint = new AnalizadorSintactico(input);
	    asint.Sp();
	}

}
