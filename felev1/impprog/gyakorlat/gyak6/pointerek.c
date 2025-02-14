#include <stdio.h>
// zh: matrix, tombok, fajkezelés, pointerek, dinamikus memoriakezeles, inputrol bekeres

int main() {

    int num = 10;
    int* p_num = &num; // int *p_num

    *p_num = 5; //*: dereferálás, ellentéte: & (referálás)
    printf("num: %d, &num: %d, p_num: %d, &p_num: %d", num, &num, p_num, &p_num);


    int** pp_num = &p_num;
    // **pp_num = *p_num = num
    // *pp_num = p_num = &num
    // pp_num = &p_num

    // n = *p = **p
    // p = *pp = &n
    // pp = &p

    **pp_num = 6;

    // magara nem mutat a pointer / nincs ertelme




    return 0;
}