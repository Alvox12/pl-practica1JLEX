package alex;

import errors.GestionErrores;

%%
%line
%class AnalizadorLexico
%type  UnidadLexica
%unicode

%{
  private ALexOperations ops;
  private GestionErrores errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public void fijaGestionErrores(GestionErrores errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digito = [0-9]
separador = [ \t\r\b\n] 
num = num
bool = bool
and = and
or = or
not = not
true = true
false = false
identificador = {letra}({letra}|{digito}|\_)*
exponencial = [eE][\+,\-]?{digito}{digito}*
numeroEnteroExp = [\+,\-]?{digito}{digito}*{exponencial}
numeroRealExp = {numeroEntero}.{digito}{digito}*{exponencial}
numeroEntero = [\+,\-]?{digito}{digito}*
numeroReal = {numeroEntero}.{digito}{digito}*
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
{true}					  {return ops.unidadTrue();}
{false}                   {return ops.unidadFalse();}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{numeroReal}              {return ops.unidadReal();}
{numeroEnteroExp}         {return ops.unidadEnt();}
{numeroRealExp}           {return ops.unidadReal();}
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
[^]                       {errores.errorLexico(fila(),lexema());}  
