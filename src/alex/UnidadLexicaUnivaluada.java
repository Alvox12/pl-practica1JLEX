package alex;


public class UnidadLexicaUnivaluada extends UnidadLexica{

	public UnidadLexicaUnivaluada(int fila, ClaseLexica clase) {
		super(fila, clase);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String lexema() {
		throw new UnsupportedOperationException();
	}
	
	public String toString() {
		return "[clase: "+clase()+",fila: "+fila() +"]";
	}

}
