program test2;
begin
   if true and true then write(1) else write(2); writeln();
   if false and true then write(1) else write(2); writeln();
   if true and false then write(1) else write(2); writeln();
   if false and false then write(1) else write(2); writeln();
   if true or true then write(1) else write(2); writeln();
   if false or true then write(1) else write(2); writeln();
   if true or false then write(1) else write(2); writeln();
   if false or false then write(1) else write(2); writeln()
end.
