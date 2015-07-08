program tc14;
var
   x, y : integer;
   s, t :  boolean;
begin
   x := 0;
   y := 1;
   s := true;
   t := false;
   if s and t + 2 then
      write(x)
   else
      write(y);
   writeln()
end.
