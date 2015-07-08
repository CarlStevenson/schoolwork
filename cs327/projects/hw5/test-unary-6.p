program testUnary6;
var i, j :  integer;
    a, b : boolean;
begin
   i := 0;
   while i < 10 do
   begin
      if not (i mod 2 = 0) then
      begin
	 write(i); writeln()
      end
      else
      begin
	 write(-i); writeln()
      end;
      i := i + 1
   end;
   i := 10;
   while not (i = 0) do
   begin
      if i mod 2 = 0 snlnot then
      begin
	 write(i); writeln()
      end
      else
      begin
	 write(-i); writeln()
      end;
      i := i - 1
   end
end.
