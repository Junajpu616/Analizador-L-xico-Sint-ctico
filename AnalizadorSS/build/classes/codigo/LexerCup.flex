package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%column
%full
%line
%char
L = [a-zA-Z]+ 
D = [0-9]+
O = ["^"]
S = ["ª"]
espacioUno=[ ]+
espacioDos=[\t]+
espacioTres=[\r]+
espacioCuatro=[\n]+
%{
    private Symbol symbol (int type, Object value){
        return new Symbol (type, yyline, yycolumn, value);
    }
    private Symbol symbol (int type){
        return new Symbol (type, yyline, yycolumn);
    }
%}
%%
{espacioUno} {/*Ignore*/}
{espacioDos} {/*Ignore*/}
{espacioTres} {/*Ignore*/}
( "//"(.)* ) {/*Ignore*/}
({espacioCuatro}) {return new Symbol(sym.espacioCuatro, yychar, yyline, yytext());}
( "(" ) {return new Symbol(sym.Parentesis_A, yychar, yyline, yytext());}
( ")" ) {return new Symbol(sym.Parentesis_C, yychar, yyline, yytext());}
( "{" ) {return new Symbol(sym.Llave_A, yychar, yyline, yytext());}
( "}" ) {return new Symbol(sym.Llave_C, yychar, yyline, yytext());}
( "=" ) {return new Symbol(sym.Igual, yychar, yyline, yytext());}
( "+" ) {return new Symbol(sym.Suma, yychar, yyline, yytext());}
( "-" ) {return new Symbol(sym.Resta, yychar, yyline, yytext());}
( "*" ) {return new Symbol(sym.Multiplicacion, yychar, yyline, yytext());}
( "/" ) {return new Symbol(sym.Division, yychar, yyline, yytext());}
({L})  {return new Symbol(sym.Variable, yychar, yyline, yytext());}
("(-"{D}+")")|{D}+ {return new Symbol (sym.Numero, yychar, yyline, yytext());}
({O}) {return new Symbol (sym.Exponente, yychar, yyline, yytext());}
({S}) {return new Symbol (sym.SimbEsp, yychar, yyline, yytext());}
. {return new Symbol (sym.ERROR, yychar, yyline, yytext());}

