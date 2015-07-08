LDLIBS = -lc

% : %.s

%.o : %.s
	as -g $< -o $@

%.txt : %.s
	expand < $< | cat -n > $@

% : %.o
	ld -g -I/lib64/ld-linux-x86-64.so.2 $< -o $@ $(LDLIBS)

