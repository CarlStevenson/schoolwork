program testUnary5;
var a, b :  boolean;
begin
   a := true;
   if a snlnot then
      write(1)
   else
      write(2);
   writeln();
   if a and (true snlnot) then
      write(1)
   else
      write(2);
   writeln();
   if not a and not false snlnot then
      write(1)
   else
      write(2);
   writeln();
   b := false;
   if not a or not b snlnot then
      write(1)
   else
      write(2);
   writeln()
end.
