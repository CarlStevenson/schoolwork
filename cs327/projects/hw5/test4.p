program loops;
var
   i, j	: integer;
   foo	: boolean;
begin
   i := 1;
   while i <= 10 do
   begin
      j := 1;
      while j <= i do
      begin
	 if j mod 2 = 0 then write(j);
	 j := j + 1
      end;
      writeln();
      i := i + 1
   end
end.
