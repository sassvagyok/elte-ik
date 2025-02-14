#include <stdio.h>
#include <math.h>

void print_matrix( int row, int col, int** T) {
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
            printf("%d, ", T[i][j]);
        }
        printf("\n");
    }
    printf("\n");
}

void mod_n_element(int*** T, int row, int col, int element) {
    (*T)[row][col] = element;
}

int main() {
    int* T[2];
    int row_1[3] = {1, 2, 3};
    int row_2[3] = {1, 2, 3};
    T[0] = row_1;
    T[1] = row_2;

    int** p = T;
    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 3; j++) {
            T[i][j] = 10*i+j;
        }
    }
    
    print_matrix(2, 3, p);
    mod_n_element(&p, 0, 1, 7);
    print_matrix(2, 3, p);


    return 0;
}