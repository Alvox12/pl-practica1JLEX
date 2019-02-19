

public class UnidadLexicaMultivaluada extends UnidadLexica{

	private String lexema;

	public UnidadLexicaMultivaluada(int fila, ClaseLexica clase, String lexema) {
		super(fila, clase);
		this.lexema = lexema;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String lexema() {
		// TODO Auto-generated method stub
		return lexema;
	}
	
	public String toString() {
		return "[clase: "+clase()+",fila: "+fila() +",lexema: " + lexema()+"]";
	}

}
