program test;
var 
   i, j	: integer;
   a	:  array[5] of integer;
begin
   i := 0;
   while i < 5 do
   begin
      read(a[i]);
      i := i + 1
   end;
   i := 0;
   while i < 5 do
   begin
      write(a[i]); writeln();
      i := i + 1
   end
end.
