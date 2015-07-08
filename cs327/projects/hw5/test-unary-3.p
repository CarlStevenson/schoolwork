program testUnary3;
var a, b :  integer;
begin
   a := -1+2- -3;
   write(a); writeln();
   b := -a- -a + +a * -a + -(a + +a);
   write(b); writeln()
end.
