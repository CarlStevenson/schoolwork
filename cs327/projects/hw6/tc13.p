program tc13;
var
   x, y : integer;
   s, t :  boolean;
begin
   x := 0;
   y := 1;
   s := true;
   t := false;
   if s and t then
      write(x)
   else
      write(y);
   writeln()
end.
