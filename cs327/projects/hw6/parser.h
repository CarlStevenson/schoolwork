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
     T_VAR = 268,
     T_INTEGER = 269,
     T_NOT = 270,
     T_BOOLEAN = 271,
     T_IF = 272,
     T_THEN = 273,
     T_ELSE = 274,
     T_WHILE = 275,
     T_DO = 276,
     T_PROGRAM = 277,
     T_BEGIN = 278,
     T_END = 279,
     T_WRITE = 280,
     T_WRITELN = 281,
     T_PP = 282,
     T_MM = 283,
     T_FALSE = 284,
     T_TRUE = 285,
     T_OR = 286,
     T_AND = 287,
     T_GT = 288,
     T_GE = 289,
     T_LE = 290,
     T_LT = 291,
     T_NE = 292,
     T_EQ = 293,
     T_MINUS = 294,
     T_PLUS = 295,
     T_MOD = 296,
     T_DIV = 297,
     T_TIMES = 298,
     T_UMINUS = 299,
     T_UPLUS = 300
   };
#endif



#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED

# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


