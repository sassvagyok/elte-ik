#include "utils.h"

int generateRandom(int min, int max) {
    return rand() % (max - min) + min;
}

void fill_students(Student* studs) {
    for (int i = 0; i < STUD_CNT; i++) {
        studs[i].id = generateRandom(10000, 99999);
        studs[i].avg = generateRandom(10, 50) / 10.0;
        studs[i].age = (short)generateRandom(18, 25);
        studs[i].type = generateRandom(0, 3);
        if (studs[i].type == BSC) {
            studs[i].student_info.number_of_course = generateRandom(6, 12);

        }
        if (studs[i].type == MSC) {
            studs[i].student_info.credit_index = generateRandom(10, 60) / 10.0;
        }
        if (studs[i].type == PHD) {
            studs[i].student_info.phd_info.impact_factor = generateRandom(10, 50);
            studs[i].student_info.phd_info.Erdos_number = generateRandom(10, 100);

        }
    }
}

char* map_student_type(enum StudentType type) {
    if (type == BSC) {
        return "bsc";
    }
    if (type == MSC) {
        return "msc";
    }
    if (type == PHD) {
        return "phd";
    }
    return "unknown";
}

void print_student(Student stud) {
    printf("Student id: %d - avg: %.1f - age: %d - type: %s\n", stud.id, stud.avg, stud.age, map_student_type(stud.type));
}

Student* max_avg(Student* studs) {
    Student* nerd = &studs[0];
    for (int i = 0; i < STUD_CNT; i++) {
        if (nerd->avg < studs[i].avg) { // ha Student*, akkor nem . hanem -> van
            nerd = &studs[i];
        } 
    } 
}