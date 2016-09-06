#ifndef TOKEN_H_
#define TOKEN_H_

#pragma warning(disable:4996)

typedef enum TokenType
{
	BAD_TOKEN,
	NUMBER_TOKEN,
	ADD_OPR_TOKEN,
	SUB_OPR_TOKEN,
	MUL_OPR_TOKEN,
	DIV_OPR_TOKEN,
	END_OF_LINE_TOKEN
};
#define MAX_TOKEN_SIZE (100)
typedef struct Token
{
	TokenType type;
	double value;
	char str[MAX_TOKEN_SIZE];
};
void set_line(char *line);
void get_token(Token *token);

#endif
