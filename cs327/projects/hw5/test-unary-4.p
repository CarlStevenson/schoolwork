program testUnary4;
var a, b :  boolean;
begin
   a := true;
   if not a then
      write(1)
   else
      write(2);
   writeln();
   if a and not true then
   if not a then
      write(1)
   else
      write(2);
   writeln();
   if not (not a and not false) then
   if not a then
      write(1)
   else
      write(2);
   writeln();
   b := false;
   if not (not a or not b) then
      write(1)
   else
      write(2);
   writeln()
end.
