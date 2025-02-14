#include "utils.h"

int main() {
    srand(time(NULL));
    Student* studs = malloc(sizeof(Student) * STUD_CNT);

    fill_students(studs);

    for(int i = 0; i < STUD_CNT; i++) {
        print_student(studs[i]);
    }

    Student* nerd = max_avg(studs);
    printf("Nerd:\n");
    print_student(*nerd);
    free(studs);

    return 0;
}