program test3;
begin
   i := 1;
   while i <= 10 do
   begin
      write(i);
      i := i + 1
   end;
   writeln();
   i := 1;
   while i < 10 do
   begin
      write(i);
      i := i + 1
   end;
   writeln();
   i := 1;
   while 10 >= i do
   begin
      write(i);
      i := i + 1
   end;
   writeln();
   i := 1;
   while 10 > i do
   begin
      write(i);
      i := i + 1
   end;
   writeln();
   i := 1;
   while i <> 10 do
   begin
      write(i);
      i := i + 1
   end;
   writeln()
end.
