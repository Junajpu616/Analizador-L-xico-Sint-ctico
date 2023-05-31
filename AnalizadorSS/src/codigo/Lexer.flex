package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%line
%column
%type Tokens
L = [a-zA-Z]+
D = [0-9]+
O = ["^"]
S = ["ª"]
espacioUno=[ ]+
espacioDos=[\t]+
espacioTres=[\r]+
espacioCuatro=[\n]+
%{
    public String lexeme;
    public int column;
    public int line;
%}
%%
{espacioUno} {/*Ignore*/}
{espacioDos} {/*Ignore*/}
{espacioTres} {/*Ignore*/}
{espacioCuatro} {/*Ignore*/}
("(")* {line=yyline; column=yycolumn; lexeme=yytext(); return Parentesis_A;}
(")")* {line=yyline; column=yycolumn; lexeme=yytext(); return Parentesis_C;}
("{")* {line=yyline; column=yycolumn; lexeme=yytext(); return Llave_A;}
("}")* {line=yyline; column=yycolumn; lexeme=yytext(); return Llave_C;}
("=")* {line=yyline; column=yycolumn; lexeme=yytext(); return Igual;}
("+")* {line=yyline; column=yycolumn; lexeme=yytext(); return Suma;}
("-")* {line=yyline; column=yycolumn; lexeme=yytext(); return Resta;}
("*")* {line=yyline; column=yycolumn; lexeme=yytext(); return Multiplicacion;}
("/")* {line=yyline; column=yycolumn; lexeme=yytext(); return Division;}
({L}({L})*) {line=yyline; column=yycolumn; lexeme=yytext(); return Variable;}
(("(-"{D}+")")|{D})+ {line=yyline; column=yycolumn; lexeme=yytext(); return Numero;}
({O}) {line=yyline; column=yycolumn; lexeme=yytext(); return Exponente;}
({S}) {line=yyline; column=yycolumn; lexeme=yytext(); return SimbEsp;}
. {line=yyline; column=yycolumn; lexeme=yytext(); return ERROR;}
