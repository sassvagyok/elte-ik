#include "utils.h" // nem <> hanem ""

int add(int a, int b){
    return a + b;
}

void static_func() {
    static int st_val = 1;
    st_val += 1;
    printf("st_val: %d\n", st_val);
}

void swap(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int* greater(int* a, int* b) {
    return *a > *b ? a : b;

}

int main () {
    int a = 10, b = 20;
    swap(&a, &b);
    printf("a: %d b: %d\n", a, b);

    void swap(int* a, int* b) {
        *a += 1;
        *b -= 1;
    }

    swap(&a, &b);
    printf("a: %d b: %d\n", a, b);

    static_func();
    static_func();
    static_func();
    static_func();

    return 0;
}