
%%
%line
%class AnalizadorLexico
%type  UnidadLexica
%unicode

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digito = [0-9]
exponencial = [e,E][\+,\-]?{digito}{digito}*
separador = [ \t\r\b\n] 
num = num
bool = bool
and = and
or = or
not = not
identificador = {letra}({letra}|{digito}|\_)*
numeroEntero = [\+,\-]?{digito}{digito}*[exponencial]?
numeroReal = {numeroEntero}.{digito}{digito}*[exponencial]?
operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = /
parentesisApertura = \(
parentesisCierre = \)
igual = \=
mayor = \>
mayorigual = \>\=
menor = \<
menorigual = \<\=
puntocoma  = \;
equivalente = \=\=
diferente =  \!\=
seccion =  \&\&
%%
{separador}               {}
{num}                  	  {return ops.unidadNum();}
{bool}                    {return ops.unidadBool();}
{and}                     {return ops.unidadAnd();}
{or}                   	  {return ops.unidadOr();}
{not}                     {return ops.unidadNot();}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{numeroReal}              {return ops.unidadReal();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{mayor}                   {return ops.unidadMayor();} 
{mayorigual}              {return ops.unidadMayorIgual();} 
{menor}                   {return ops.unidadMenor();} 
{menorigual}              {return ops.unidadMenorIgual();} 
{igual}                   {return ops.unidadIgual();}
{equivalente}             {return ops.unidadEquivalente();} 
{diferente}               {return ops.unidadDiferente();} 
{puntocoma}               {return ops.unidadPuntoComa();}
{seccion}                 {return ops.unidadSeparador();}
[^]                       {ops.error();}  
