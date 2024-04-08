package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
"//"[^"\n"]*  {/*Ignore*/}
"/\*".*"\*/" {/*Ignore*/}

/* Comillas para cadenas de texto */
"\"" { return new Symbol(sym.Comillas, yychar, yyline, yytext()); }

/* Palabras clave y tipos de datos */
"int"   { return new Symbol(sym.Int, yychar, yyline, yytext()); }
"long"  { return new Symbol(sym.T_dato, yychar, yyline, yytext()); }
"if"    { return new Symbol(sym.If, yychar, yyline, yytext()); }
"else"  { return new Symbol(sym.Else, yychar, yyline, yytext()); }
"for"   { return new Symbol(sym.For, yychar, yyline, yytext()); }
"printf"{ return new Symbol(sym.Printf, yychar, yyline, yytext()); }
"scanf" { return new Symbol(sym.Scanf, yychar, yyline, yytext()); }
"return"{ return new Symbol(sym.Return, yychar, yyline, yytext()); }

/* Operadores */
"="     { return new Symbol(sym.Igual, yychar, yyline, yytext()); }
"+"     { return new Symbol(sym.Suma, yychar, yyline, yytext()); }
"-"     { return new Symbol(sym.Resta, yychar, yyline, yytext()); }
"*"     { return new Symbol(sym.Multiplicacion, yychar, yyline, yytext()); }
"/"     { return new Symbol(sym.Division, yychar, yyline, yytext()); }
"&&"    { return new Symbol(sym.Op_logico, yychar, yyline, yytext()); }
"||"    { return new Symbol(sym.Op_logico, yychar, yyline, yytext()); }
"!"     { return new Symbol(sym.Op_logico, yychar, yyline, yytext()); }
"=="    { return new Symbol(sym.Op_relacional, yychar, yyline, yytext()); }
"!="    { return new Symbol(sym.Op_relacional, yychar, yyline, yytext()); }
">="    { return new Symbol(sym.Op_relacional, yychar, yyline, yytext()); }
"<="    { return new Symbol(sym.Op_relacional, yychar, yyline, yytext()); }
">"     { return new Symbol(sym.Op_relacional, yychar, yyline, yytext()); }
"<"     { return new Symbol(sym.Op_relacional, yychar, yyline, yytext()); }
"++"    { return new Symbol(sym.Op_incremento, yychar, yyline, yytext()); }
"--"    { return new Symbol(sym.Op_incremento, yychar, yyline, yytext()); }

/* Símbolos */
"("     { return new Symbol(sym.Parentesis_a, yychar, yyline, yytext()); }
")"     { return new Symbol(sym.Parentesis_c, yychar, yyline, yytext()); }
"{"     { return new Symbol(sym.Llave_a, yychar, yyline, yytext()); }
"}"     { return new Symbol(sym.Llave_c, yychar, yyline, yytext()); }
"["     { return new Symbol(sym.Corchete_a, yychar, yyline, yytext()); }
"]"     { return new Symbol(sym.Corchete_c, yychar, yyline, yytext()); }
";"     { return new Symbol(sym.P_coma, yychar, yyline, yytext()); }

/* Identificadores y números */
{L}({L}|{D})* { return new Symbol(sym.Identificador, yychar, yyline, yytext()); }
{D}+          { return new Symbol(sym.Numero, yychar, yyline, yytext()); }

/* Error de análisis */
. { return new Symbol(sym.ERROR, yychar, yyline, yytext()); }
