program test;
var 
   a : array[10] of integer;
   i :  integer;
begin
   i := 0;
   while i < 10 do
   begin
      a[i] := i;
      i := i + 1      
   end;
   i := 0;
   while i < 10 do
   begin
      a[i] := a[10 - a[i]];
      i := i + 1
   end;
   i := 0;
   while i < 10 do
   begin
      writeln(a[i] : a[i] * 2);
      i := i + 1
   end
end.
