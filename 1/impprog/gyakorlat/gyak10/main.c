#include "dyn_mem.h"

int main(int argc, char** argv) {
    if(argc != 2) {
        printf("We need only one argument!\n");
        return 1;
    }

    int choice = atoi(argv[1]);

    switch (choice)
    {
    case 0:
        printf("Program: %s\n", argv[0]);
        break;
    case 1:
        read_word();
        break;
    case 2:
        read_5_words();
        break;
    case 3:
        read_words();
        break;
    case 4:
        break;
    case 5:
        break;
    default:
        printf("Wrong option!\n");
        break;
    }

    if(choice == 4) {
        char* s = read_word_return();
        reverse(s);
        printf("s outside: %s\n", s);
        free(s);
    }

    if(choice == 5) {
        char* s = read_word_return();
        char* ret = reverse2(s);
        printf("s outside: %s\n", ret);
        free(s);
        free(ret);
    }

    return 0;
}