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
		switch(anticipo.clase()) {
     		case NUM: case BOOL:
	     		D();
	     		RLDs();
	     		break;
     		default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(), ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}
	
	
	private void D() {
		switch(anticipo.clase()) {
	 		case NUM: case BOOL:
	     		T();
	     		empareja(ClaseLexica.IDEN);
	     		break;
	 		default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(), ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}
	
	private void RLDs() {
		switch(anticipo.clase()) {
	 		case PUNTOCOMA: 
	     		empareja(ClaseLexica.PUNTOCOMA);
	     		D();
	     		RLDs();
	     		break;
	 		case SEPARADOR: break;
	 		default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(), ClaseLexica.PUNTOCOMA, ClaseLexica.SEPARADOR);
		}
	}
	
	private void LD2() {
		switch(anticipo.clase()) {
			case IDEN:
				D2();
				RLD2();
				break;
			default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(), ClaseLexica.IDEN);
		}
	}
	
	private void D2() {
		switch(anticipo.clase()) {       
	       case IDEN:   
	        empareja(ClaseLexica.IDEN);
	        empareja(ClaseLexica.IGUAL);
	        E0();
	        break;
	       default: errores.errorSintactico(anticipo.fila(),anticipo.clase(),
	                                         ClaseLexica.IDEN);                                       
	     }
	}
	
	private void RLD2() {
		switch(anticipo.clase()) {
	       case PUNTOCOMA:    
	           empareja(ClaseLexica.PUNTOCOMA);
	           D2();
	           RLD2();
	           break;
	       case EOF: break;    
	       default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(),
	                                         ClaseLexica.PUNTOCOMA, ClaseLexica.EOF);                                       
	    }
	}
	
	private void E0() {
		switch(anticipo.clase()) {
	        case IDEN: case ENT: case REAL: case PAP: case NOT: case TRUE: case FALSE: case MENOS:
	            E1();
	            RE0();
	            break;
	        default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(),
	                                          ClaseLexica.IDEN,ClaseLexica.ENT,
	                                          ClaseLexica.REAL, ClaseLexica.PAP, ClaseLexica.NOT,  
	                                          ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.MENOS);                                    
		}
	}
	
	private void RE0() {
		switch(anticipo.clase()) {
	 		case MAS: case MENOS: 
	     		OP0();
	     		E1();
	     		RE1();
	     		break;
	 		case PCIERRE: case PUNTOCOMA: case EOF: break;
	 		default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(), ClaseLexica.MAS, ClaseLexica.MENOS);
		}
	}
	
	private void E1() {
		switch(anticipo.clase()) {
	        case IDEN: case ENT: case REAL: case PAP: case NOT: case TRUE: case FALSE: case MENOS:
	            E2();
	            RE1();
	            break;
	        default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(),
	                                          ClaseLexica.IDEN,ClaseLexica.ENT,
	                                          ClaseLexica.REAL, ClaseLexica.PAP, ClaseLexica.NOT,  
	                                          ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.MENOS);                                    
		}
	}
	
	private void RE1() {
		switch(anticipo.clase()) {
	 		case AND: 
	 			empareja(ClaseLexica.AND);
	     		E2();
	     		RE1();
	     		break;
	 		case OR:
	 			empareja(ClaseLexica.OR);
	 			E2();
	 			break;
	 		case PCIERRE: case MAS: case MENOS: case PUNTOCOMA: case EOF: break;
	 		default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(), ClaseLexica.MAS, ClaseLexica.MENOS, ClaseLexica.AND, ClaseLexica.OR);
		}
	}

	private void E2() {
		switch(anticipo.clase()) {
	        case IDEN: case ENT: case REAL: case PAP: case NOT: case TRUE: case FALSE: case MENOS:
	            E3();
	            RE2();
	            break;
	        default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(),
	                                          ClaseLexica.IDEN,ClaseLexica.ENT,
	                                          ClaseLexica.REAL, ClaseLexica.PAP, ClaseLexica.NOT,  
	                                          ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.MENOS);                                    
		}
	}
	
	private void RE2() {
		switch(anticipo.clase()) {
			case MAYOR: case MENOR: case MAYIGUAL: case MENIGUAL: case DIFERENTE: case EQUIVALENTE:
				OP2();
				E3();
				break;
			case PCIERRE: case MAS: case MENOS: case PUNTOCOMA: case AND: case OR: case EOF: break;
			default: errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                    ClaseLexica.MAYOR,ClaseLexica.MENOR,
                    ClaseLexica.MAYIGUAL, ClaseLexica.MENIGUAL, ClaseLexica.DIFERENTE,  
                    ClaseLexica.EQUIVALENTE, ClaseLexica.AND, ClaseLexica.OR, ClaseLexica.PCIERRE,
                    ClaseLexica.MAS, ClaseLexica.MENOS, ClaseLexica.PUNTOCOMA, ClaseLexica.EOF);     
		}
	}

	private void E3() {
		switch(anticipo.clase()) {
        case IDEN: case ENT: case REAL: case PAP: case NOT: case TRUE: case FALSE: case MENOS:
	            E4();
	            RE3();
	            break;
	        default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(),
	                                          ClaseLexica.IDEN,ClaseLexica.ENT,
	                                          ClaseLexica.REAL, ClaseLexica.PAP, ClaseLexica.NOT,  
	                                          ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.MENOS);                                    
		}
	}
	
	private void RE3() {
		switch(anticipo.clase()) {
			case POR: case DIV:
				OP3();
				E4();
				RE3();
				break;
			case PCIERRE: case MAS: case MENOS: case PUNTOCOMA: case AND: case OR: case EOF: 
				case MAYOR: case MENOR: case MAYIGUAL: case MENIGUAL: case DIFERENTE: case EQUIVALENTE: break;
			
			default: errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                    ClaseLexica.MAYOR,ClaseLexica.MENOR,
                    ClaseLexica.MAYIGUAL, ClaseLexica.MENIGUAL, ClaseLexica.DIFERENTE,  
                    ClaseLexica.EQUIVALENTE, ClaseLexica.AND, ClaseLexica.OR, ClaseLexica.PCIERRE,
                    ClaseLexica.MAS, ClaseLexica.MENOS, ClaseLexica.PUNTOCOMA, ClaseLexica.POR, ClaseLexica.DIV, ClaseLexica.EOF);
		}
	}

	private void E4() {
		switch(anticipo.clase()) {
	        case IDEN: case ENT: case REAL: case PAP: case TRUE: case FALSE:
		            E5();
		            break;
	        case MENOS:
	        	empareja(ClaseLexica.MENOS); 
	        	E4();
	        	break;
	        case NOT:
	        	empareja(ClaseLexica.NOT); 
	        	E5();
	        	break;
	        default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(),
		                                          ClaseLexica.IDEN,ClaseLexica.ENT,
		                                          ClaseLexica.REAL, ClaseLexica.PAP, ClaseLexica.NOT,  
		                                          ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.MENOS);                                    
		}
	}
	
	private void E5() {
		switch(anticipo.clase()) {
			case ENT: empareja(ClaseLexica.ENT); break;
			case REAL: empareja(ClaseLexica.REAL); break;
			case IDEN: empareja(ClaseLexica.IDEN); break;
			case TRUE: empareja(ClaseLexica.TRUE); break;
			case FALSE: empareja(ClaseLexica.FALSE); break;
			case PAP:
				empareja(ClaseLexica.PAP);
				E0();
				empareja(ClaseLexica.PCIERRE);
				break;
			default: errores.errorSintactico(anticipo.fila(),anticipo.clase(),
                    ClaseLexica.ENT,ClaseLexica.REAL,
                    ClaseLexica.IDEN, ClaseLexica.TRUE, ClaseLexica.FALSE,  
                    ClaseLexica.PAP, ClaseLexica.PCIERRE);     
		}
	}
	
	private void T() {
		switch(anticipo.clase()) {
	 		case NUM: 
	     		empareja(ClaseLexica.NUM);
	     		break;
	 		case BOOL: 
	 			empareja(ClaseLexica.BOOL);
	     		break;
	 		default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(), ClaseLexica.NUM, ClaseLexica.BOOL);
		}
	}
	
	private void OP0() {
		switch(anticipo.clase()) {
	 		case MAS: 
	     		empareja(ClaseLexica.MAS);
	     		break;
	 		case MENOS: 
	 			empareja(ClaseLexica.MENOS);
	     		break;
	 		default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(), ClaseLexica.MAS, ClaseLexica.MENOS);
		}
	}
	
	private void OP2() {
		switch(anticipo.clase()) {
			case MAYOR: empareja(ClaseLexica.MAYOR); break;
			case MENOR: empareja(ClaseLexica.MENOR); break;
			case MAYIGUAL: empareja(ClaseLexica.MAYIGUAL); break;
			case MENIGUAL: empareja(ClaseLexica.MENIGUAL); break;
			case EQUIVALENTE: empareja(ClaseLexica.EQUIVALENTE); break;
			case DIFERENTE: empareja(ClaseLexica.DIFERENTE); break;
			default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(), ClaseLexica.MAYOR, ClaseLexica.MENOR, ClaseLexica.MAYIGUAL,
					ClaseLexica.MENIGUAL, ClaseLexica.EQUIVALENTE, ClaseLexica.DIFERENTE);
		}
	}
	
	private void OP3() {
		switch(anticipo.clase()) {
	 		case DIV: 
	     		empareja(ClaseLexica.DIV);
	     		break;
	 		case POR: 
	 			empareja(ClaseLexica.POR);
	     		break;
	 		default:  errores.errorSintactico(anticipo.fila(),anticipo.clase(), ClaseLexica.DIV, ClaseLexica.POR);
		}
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
