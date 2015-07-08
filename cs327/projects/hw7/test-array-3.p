program test;
var 
   x, i, j	: integer;
   a	:  array[10] of integer;
begin
   i := 0;
   read(x);
   while i < 10 do
   begin
      read(a[i]);
      i := i + 1
   end;
   i := 0;
   while (i < 10) and (a[i] <> x) do
   begin
      i := i + 1
   end;
   if i = 10 then i := -1;
   writeln(i)
end.
