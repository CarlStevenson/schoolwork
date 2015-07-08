program testIncr5;
var a,b :  integer;
begin
   a := 0;
   while ++a <= 10 do
   begin
      b := a;
      while --b > 0 do
	 write(b);
      writeln()
   end
end.
