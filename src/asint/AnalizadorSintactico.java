package asint;

import java.io.IOException;
import java.io.Reader;

import alex.*;
import errors.GestionErrores;

public class AnalizadorSintactico {

	private UnidadLexica anticipo;
	private AnalizadorLexico alex;
	private GestionErrores errores;
	   
	public AnalizadorSintactico(Reader input) {
		errores = new GestionErrores();
	    alex = new AnalizadorLexico(input);
	    alex.fijaGestionErrores(errores);
	    sigToken();
	}
	
	public void Sp() {
	      S();
	      empareja(ClaseLexica.EOF);
	}
	
	private void S() {
	     switch(anticipo.clase()) {
	     	case NUM: case BOOL:
	     		LDs();
	     		empareja(ClaseLexica.SEPARADOR);
	     		LD2();
	     		break;
	     	default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(), ClaseLexica.NUM, ClaseLexica.BOOL);
	   }
	}
	
	private void LDs() {
		
	}
	
	
	private void D() {
		
	}
	
	private void RLDs() {
		
	}
	
	private void LD2() {
		
	}
	
	private void D2() {
		
	}
	
	private void RLD2() {
		
	}
	
	private void empareja(ClaseLexica claseEsperada) {
	      if (anticipo.clase() == claseEsperada)
	          sigToken();
	      else errores.errorSintactico(anticipo.fila(),anticipo.clase(),claseEsperada);
	}
	
	private void sigToken() {
	      try {
	        anticipo = alex.yylex();
	      }
	      catch(IOException e) {
	        errores.errorFatal(e);
	      }
	 }
	
}