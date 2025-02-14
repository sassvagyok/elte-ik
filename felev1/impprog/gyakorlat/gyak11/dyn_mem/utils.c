#include "utils.h"

void read_string_dyn() {
    printf("Give me something: ");
    char* str = malloc(sizeof(char));
    int size = 0;
    str[size] = getchar();

    while (str[size] != '\n') {
        size++;
        str = realloc(str, sizeof(char) * (size + 1));
        str[size] = getchar();
    }
    
    str[size] = "\0";
    printf("str: %s\n", str);
    free(str);
}