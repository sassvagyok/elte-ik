#include "string.h"

void count_string() {
    char str[128];
    printf("Give me smotehing: ");
    fgets(str, 128, stdin);

    int size = 0;
    int words = 1;
    
    while(str[size] != '\0') {
        if (str[size] == ' ') {
            words ++;
        }
        size++;
    }
    printf("Size: %d\nWords: %d\n", size, words);
    printf("Size: %ld\n", strlen(str));
}

void string_equal() {
    char str1[128];
    char str2[128];
    printf("First line: ");
    fgets(str1, 128, stdin);
    printf("Second line: ");
    fgets(str2, 128, stdin);
    int result = strcmp(str1, str2);
    // -1 ha str1 van elobb az abcben
    // 0 ha egyenlo a ket szo
    // 1 ha str2 van elobb az abcben

    printf("The strings are %s equal\n", result == 0 ? "" : "not");
}

void string_copy() {
    char str1[128];
    char str2[128];
    printf("Give me someting: ");
    fgets(str1, 128, stdin);
    strcpy(str2, str1);
    printf("str2: %s\n", str2);

}

void read_file() {
    char str[10][128];
    FILE* f = fopen("file.txt", "r");
    int line = 0;
    while (fgets(str[line], 128, f) != NULL) {
        line++;
    }
    for (int i = 0; i < line; i++) {
        printf("%d. line: %s", i + 1, str[i]);
    }
    fclose(f);
}

void write_to_file() {
    char str[128];
    printf("Give me something: ");
    fgets(str, 128, stdin);
    FILE* f = fopen("file.txt", "w");
    fprintf(f, str);
    fclose(f);
}

int main(int argc, char** argv) {
    printf("Program: %s\n", argv[0]);

    if(argc != 2) {
        //fprinf(stderr, "")
        printf("We need only one argument!\n");
        return 1;
    }

    char str1[] = "Hello";
    char* str2 = "Hello"; // konstansnak minosul
    str1[0] = 'J';
    //str2[1] = 'J';
    printf("str1: %s\nstr2: %s\n", str1, str2);

    int choice = atoi(argv[1]); // atoi: "123" -> 123

    switch (choice)
    {
        case 0:
            printf("Hello! Your choice is awesome!\n");
            break;
        case 1:
            count_string();
            break;
        case 2:
            string_equal();
            break;
        case 3:
            string_copy();
            break;
        case 4:
            read_file();
            break;
        case 5:
            write_to_file();
            break;
        default:
            printf("There is no such option!\n");
            break;
    }

    return 0;
}