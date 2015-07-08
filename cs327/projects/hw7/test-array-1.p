program test;
var 
   i, j	: integer;
   a	:  array[5] of integer;
begin
   i := 0;
   while i < 5 do
   begin
      a[i] := i;
      i := i + 1
   end;
   i := 0;
   while i < 5 do
   begin
      write(i); writeln();
      i := i + 1
   end
end.
