#ifndef STRUCT_UTILS_H
#define STRUCT_UTILS_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#define STUD_CNT 20

enum StudentType {
    BSC,
    MSC,
    PHD

};

struct phdInfo {
    double impact_factor;
    int Erdos_number;
};

union StudentInfo {
    int number_of_course;
    double credit_index;
    struct phdInfo phd_info;

};

struct _Student {
    int id;
    double avg;
    short age;
    enum StudentType type;
    union StudentInfo student_info;
};

typedef struct _Student Student;

int generateRandom(int min, int max);
void fill_students(Student* studs);
void print_student(Student stud);
Student* max_avg(Student* studs);

#endif // STRUCT_UTILS_H