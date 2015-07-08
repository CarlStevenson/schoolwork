program tc16;
var 
   x : integer;
   y : integer;
   z : integer;
   f : boolean;
   g :  boolean;
begin
   x := 0;
   y := 10;
   
   f := not (false and true);
   g := false and false;
   while (x < 10) and (f or g) do
   begin
      if x mod 2 = 0 then
      begin
	 z := x;
	 while z < 10 or (f and g) do
	 begin
	    write(z);
	    z := z + 1
	 end
      end;
      x := x + 1;
      writeln()
   end
end.
