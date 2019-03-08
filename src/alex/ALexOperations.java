package alex;
public class ALexOperations {
  private AnalizadorLexico alex;
  public ALexOperations(AnalizadorLexico alex) {
   this.alex = alex;   
  }
  public UnidadLexica unidadId() {
     return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.IDEN,
                                         alex.lexema()); 
  } 
  public UnidadLexica unidadNum() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.NUM); 
  } 
  public UnidadLexica unidadBool() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.BOOL); 
  }
  public UnidadLexica unidadAnd() {
	     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.AND); 
	  }
  public UnidadLexica unidadOr() {
	     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.OR); 
	  }
  public UnidadLexica unidadNot() {
	     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.NOT); 
	  }
  public UnidadLexica unidadTrue() {
	     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.TRUE); 
	  }
  public UnidadLexica unidadFalse() {
	     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.FALSE); 
	  }
  public UnidadLexica unidadEnt() {
     return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.ENT,alex.lexema()); 
  } 
  public UnidadLexica unidadReal() {
     return new UnidadLexicaMultivaluada(alex.fila(),ClaseLexica.REAL,alex.lexema()); 
  } 
  public UnidadLexica unidadSuma() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MAS); 
  } 
  public UnidadLexica unidadResta() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MENOS); 
  } 
  public UnidadLexica unidadMul() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.POR); 
  } 
  public UnidadLexica unidadDiv() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.DIV); 
  } 
  public UnidadLexica unidadPAp() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PAP); 
  } 
  public UnidadLexica unidadPCierre() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PCIERRE); 
  }
  public UnidadLexica unidadMayor() {
	     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MAYOR);     
  }
  public UnidadLexica unidadMenor() {
	     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MENOR);     
  }
  public UnidadLexica unidadMayorIgual() {
	     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MAYIGUAL);     
  } 
  public UnidadLexica unidadMenorIgual() {
	     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.MENIGUAL);     
  } 
  public UnidadLexica unidadEquivalente() {
	     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.EQUIVALENTE);     
  }
  public UnidadLexica unidadDiferente() {
		return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.DIFERENTE);
}
  public UnidadLexica unidadIgual() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.IGUAL); 
  } 
  public UnidadLexica unidadPuntoComa() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.PUNTOCOMA); 
  } 
  public UnidadLexica unidadSeparador() {
	     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.SEPARADOR); 
  } 
  public UnidadLexica unidadEof() {
     return new UnidadLexicaUnivaluada(alex.fila(),ClaseLexica.EOF); 
  }
  /*public void error() {
    System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
  }*/
}
