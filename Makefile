# Makefile from hk-mac-pro
# test
CC      = gcc
CCFLAGS = -O2 -s
BIN     = test
OBJ     = root_password_tool_win32.o

$(BIN) : $(OBJ)
    $(CC) $(CCFLAGS) -o test $(OBJ)

$(OBJ) : root_password_tool_win32.c
    $(CC) $(CCFLAGS) -c root_password_tool_win32.c

.PHONY : clean
clean :
    -rm $(BIN) $(OBJ)
