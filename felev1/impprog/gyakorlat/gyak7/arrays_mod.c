#include <stdio.h>

void print_array(int* T, int size) {
    for (int i = 0; i < size; i++) {
        printf("%d, ", T[i]);
    }
    printf("\n");
}

void mod_n_element(int** T, int nth, int element) {
    (*T)[nth] = element;
}

int main() {
    int T[] = {1, 2, 3, 4};
    int* p = T;
    print_array(T, sizeof(T)/sizeof(T[0]));
    mod_n_element(&p, 0, 5);
    print_array(T, sizeof(T)/sizeof(T[0]));

    return 0;
}