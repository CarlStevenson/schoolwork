/* A Bison parser, made by GNU Bison 2.5.  */

/* Bison interface for Yacc-like parsers in C
   
      Copyright (C) 1984, 1989-1990, 2000-2011 Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     T_CONST = 258,
     T_IDENT = 259,
     T_SNLNOT = 260,
     T_LPAREN = 261,
     T_RPAREN = 262,
     T_ASSIGN = 263,
     T_SEMI = 264,
     T_PERIOD = 265,
     T_COLON = 266,
     T_COMMA = 267,
     T_LBRACK = 268,
     T_RBRACK = 269,
     T_VAR = 270,
     T_INTEGER = 271,
     T_NOT = 272,
     T_BOOLEAN = 273,
     T_IF = 274,
     T_THEN = 275,
     T_ELSE = 276,
     T_WHILE = 277,
     T_DO = 278,
     T_PROGRAM = 279,
     T_BEGIN = 280,
     T_END = 281,
     T_WRITE = 282,
     T_WRITELN = 283,
     T_READ = 284,
     T_PP = 285,
     T_MM = 286,
     T_FALSE = 287,
     T_TRUE = 288,
     T_OR = 289,
     T_AND = 290,
     T_GT = 291,
     T_GE = 292,
     T_LE = 293,
     T_LT = 294,
     T_NE = 295,
     T_EQ = 296,
     T_MINUS = 297,
     T_PLUS = 298,
     T_MOD = 299,
     T_DIV = 300,
     T_TIMES = 301,
     T_UMINUS = 302,
     T_UPLUS = 303
   };
#endif



#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED

# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


