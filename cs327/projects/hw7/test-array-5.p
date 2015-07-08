program test;
var 
   a : array[10] of integer;
   i, j, p, t :  integer;
begin
   i := 0;
   while i < 10 do
   begin
      read(a[i]);
      i := i + 1
   end;
   i := 0;
   while i < 9 do
   begin
      p := i;
      j := i + 1;
      while j < 10 do
      begin
	 if a[j] < a[p] then p := j;
	 j := j + 1
      end;
      t := a[i];
      a[i] := a[p];
      a[p] := t;
      i := i + 1
   end;
   i := 0;
   while i < 10 do
   begin
      writeln(a[i]);
      i := i + 1
   end
end.
