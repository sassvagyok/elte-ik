#include "dyn_mem.h"

void read_word() {
    char buffer[20];

    printf("Give me something!\n");
    fgets(buffer, 20, stdin);

    char* str = malloc(sizeof(char) * strlen(buffer));
    strcpy(str, buffer);
    printf("str: %s\n", str);
    free(str); // minden malloc-hoz kell egy free
}

void read_5_words() {
    char buffer[20];
    char* words[5];

    for (int i = 0; i < 5; i++) {
        printf("Give me something!\n");
        fgets(buffer, 20, stdin);

        words[i] = malloc(sizeof(char) * strlen(buffer));
        strcpy(words[i], buffer);
    }

    for (int i = 0; i < 5; i++) {
        printf("%d. word: %s\n", i + 1, words[i]);
    }

    for (int i = 0; i < 5; i++) {
        free(words[i]);
    }
}

void read_words() {
    char buffer[128];
    char** words;
    int rows = 0;

    while (1) {
        printf("Give me something: ");

        if (fgets(buffer, 128, stdin) == NULL) {
            break;
        }

        if (rows == 0) {
            words = malloc(sizeof(char*));
        } else {
            words = realloc(words, (rows + 1) * sizeof(char*));
        }
        words[rows] = malloc(sizeof(char) * strlen(buffer));
        strcpy(words[rows], buffer);
        rows++;
    }

    for (int i = rows - 1; i >= 0; i--) {
        printf("\n%d. word: %s", i + 1, words[i]);
    }

    for (int i = 0; i < rows; i++) {
        free(words[i]);
        words[i] = NULL;
    }
    
    free(words);
    words = NULL;
}

void reverse(char* s) {
    for (int i = 0; i < (int)strlen(s) / 2; i++) {
        char temp = s[i];
        s[i] = s[strlen(s)-1-i];
        s[strlen(s)-1-i] = temp;
    }
    printf("s: %s\n", s);
}

char* read_word_return() {
    char buffer[20];

    printf("Give me something!\n");
    fgets(buffer, 20, stdin);

    char* str = malloc(sizeof(char) * strlen(buffer));
    strcpy(str, buffer);
    printf("str: %s\n", str);
    return str;
}

char* reverse2(char* s) {
    char* ret = malloc(sizeof(s));
    strcpy(ret, s);
    for (int i = 0; i < (int)strlen(ret) / 2; i++) {
        char temp = ret[i];
        ret[i] = s[strlen(ret)-1-i];
        ret[strlen(ret)-1-i] = temp;
    }
    return ret;
}