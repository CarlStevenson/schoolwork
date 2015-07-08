program testIncr3;
var a, b :  integer;
begin
   a := 1;
   b := 2;
   write(a); write(b); writeln();
   write(++a + a++ + --a - a--); writeln();
   write(a); write(b); writeln();
   write(++b - b++ - --b + b--); writeln();
   write(a); write(b); writeln();
   write(++a - b++ - --b + a--); writeln();
   write(a); write(b); writeln()
end.
   
