#include <stdio.h>

int global_variable = 10;

void func_2(int a, int b);
void func_2(int b, int a) {}

// DONT void func_3(int a, int a);

void func() {
    int variable_f = 20;
}

int main() {
    printf("global_variable: %d\n", global_variables);
    global_variable = 20;

    int var_if = 0;
    if () {
        int var_then;
    } else {
        // DONT var_then = 10;
    }
    // DONT var_then

    int a = 0;
    // a == 0
    {
        int a = 1;
        // a == 1
        {
            int a = 2;
            int b = 4;
            // a == 2
            {
                int a = 3;
                // a == 3
                // b elérhető
            }
            // a == 2
            // b elérhető
        }
        // a == 1
        // b nem elérhető
    }
    // a == 0
    // b nem elérhető


    return 0;
}