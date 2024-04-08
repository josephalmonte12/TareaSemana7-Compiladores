package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
%unicode

/* Expresiones regulares para partes del lenguaje */
L=[a-zA-Z_]
D=[0-9]
C="\""
A="\'"
NL=\r|\n|\r\n
WS=[ \t]

/* Patrones para diferentes tokens */
%%

/* Palabras clave */
"int"       { return T_dato; }
"long"      { return T_dato; }
"if"        { return If; }
"else"      { return Else; }
"for"       { return For; }
"printf"    { return Printf; }
"scanf"     { return Scanf; }
"return"    { return Return; }

/* Operadores */
"="         { return Igual; }
"<"         { return Op_relacional; }
"*"         { return Multiplicacion; }
"+"         { return Suma; }
"-"         { return Resta; }
"/"         { return Division; }
"&&"        { return Op_logico; }
"||"        { return Op_logico; }
"!"         { return Op_logico; }
"=="        { return Op_relacional; }
"!="        { return Op_relacional; }
">="        { return Op_relacional; }
"<="        { return Op_relacional; }
"++"        { return Op_incremento; }
"--"        { return Op_incremento; }

/* Símbolos */
";"         { return P_coma; }
","         { return Coma; }
"("         { return Parentesis_a; }
")"         { return Parentesis_c; }
"{"         { return Llave_a; }
"}"         { return Llave_c; }
"["         { return Corchete_a; }
"]"         { return Corchete_c; }
/* Identificadores */
{L}({L}|{D})*    { lexeme = yytext(); return Identificador; }

/* Números */
{D}+             { lexeme = yytext(); return Numero; }

/* Cadenas y caracteres */
{C}([^"\\\n]|\\[\\'"nrt])*{C}   { lexeme = yytext(); return Cadena; }
{A}([^'\\\n]|\\[\\'"nrt]){A}    { lexeme = yytext(); return Caracter; }

/* Espacios en blanco y saltos de línea */
{WS}+            { /* Ignorar espacios en blanco */ }
{NL}             { /* Ignorar saltos de línea */ }

/* Comentarios */
"//"[^"\n"]*     { /* Ignorar comentario de línea */ }
"/\*".*"\*/"     { /* Ignorar comentario de bloque */ }

/* Directivas del Preprocesador */
"#"[^"\n"]*      { /* Ignorar directivas del preprocesador */ }

/* Manejo de caracteres no reconocidos */
.                { /* Acción para manejar errores léxicos */ }
