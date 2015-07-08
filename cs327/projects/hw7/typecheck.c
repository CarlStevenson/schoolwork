/** @file typecheck.c
 *  Type checking functions.
 */

#include <stdio.h>
#include <string.h>
#include "globals.h"


/**
 * Traverse the declarations list, storing types in
 * the symbol table.
 * @param p The parse tree.
 */

void declare_variables(prog *p) {
  decl_list *dl = p->decls;
  while (dl) {
    symbol_list *il = dl->first->variables;
    while (il) {
      if(dl->first->type == TY_NONE) {
        error("Type mismatch error");
      }
      il->first->type = dl->first->type;
      il = il->next;
    }
    dl = dl->next;
  }
}

int isBoolean(expr *e) {
  switch(e->tag){
    case E_BOOL_CONST:
                      return 1;
    case E_INT_CONST:
                      return 0;
    case E_VARIABLE: 
                      if(e->variable->symbol->type == TY_BOOLEAN){
                        return 1;
                      }else{
                        return 0;
                      }
    case E_UNARY: 
                      if(isBoolean(e->unary->rand)){
                        return 1;
                      }else{
                        return 0;
                      }
    case E_BINARY: 
                      if(isBoolean(e->binary->lrand) && isBoolean(e->binary->rrand)){
                        return 1;
                      }else{
                        return 0;
                      }
  }
}

int isInteger(expr *e) {
  switch(e->tag){
    case E_BOOL_CONST:
                      return 0;
    case E_INT_CONST:
                      return 1;
    case E_VARIABLE: 
                      if(e->variable->symbol->type == TY_INTEGER){
                        return 1;
                      }else{
                        return 0;
                      }
    case E_UNARY: 
                      if(isInteger(e->unary->rand)){
                        return 1;
                      }else{
                        return 0;
                      }
    case E_BINARY: 
                      if(isInteger(e->binary->lrand) && isInteger(e->binary->rrand)){
                        return 1;
                      }else{
                        return 0;
                      }
  }

}

type typecheck(expr *e) {

  if(isBoolean(e)){return TY_BOOLEAN;}
  else if(isInteger(e)){return TY_INTEGER;}
  else{error("Type mismatch error");}
}

