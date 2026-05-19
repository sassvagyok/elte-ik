#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct grape
{
    unsigned int id;
    char place[50];
    char production_place[50];
    char table[50];
    char kind[50];
    unsigned int area;
    char type[50];
    unsigned int destruction_rate;
};

unsigned int id = 1;

void read_in_console_messages(const char *console_text, char *result)
{
    printf("%s", console_text);
    scanf(" %49[^\n]", result);
}

void file_creation()
{
    FILE *file = fopen("adatbazis.txt", "a");

    if (file == NULL)
    {
        fprintf(stderr, "File creation error!\n");
        exit(1);
    }

    fclose(file);

    file = NULL;
}

struct grape *file_read(FILE *file)
{
    struct grape *actual = malloc(sizeof(struct grape));

    if (actual == NULL)
        return NULL;

    if (fscanf(file, "%u\t%49[^\t]\t%49[^\t]\t%49[^\t]\t%49[^\t]\t%u\t%49[^\t]\t%u",
               &(actual->id),
               actual->place,
               actual->production_place,
               actual->table,
               actual->kind,
               &(actual->area),
               actual->type,
               &(actual->destruction_rate)) != 8)
    {
        free(actual);
        actual = NULL;

        return NULL;
    }

    return actual;
}

void print_database(const struct grape *grape)
{
    printf("ID              : %u\n",   grape->id);
    printf("Place           : %s\n",   grape->place);
    printf("Production place: %s\n",   grape->production_place);
    printf("Table           : %s tabla\n",   grape->table);
    printf("Kind            : %s\n",   grape->kind);
    printf("Area            : %u negyszogol\n", grape->area);
    printf("Type            : %s\n",   grape->type);
    printf("Destruction rate: %u%%\n\n", grape->destruction_rate);
}

void id_setup()
{
    FILE *infile = fopen("adatbazis.txt", "r");
    struct grape *actual = NULL;
    id = 0;

    if (infile != NULL)
    {
        while ((actual = file_read(infile)) != NULL)
        {
            id = actual->id;
            free(actual);
            actual = NULL;
        }

        fclose(infile);
        infile = NULL;
    }

    id++;
}

void grape_creation(const struct grape *creategrape)
{
    struct grape a = {0};

    char input[50];
    unsigned int area;
    unsigned int destruction_rate;

    if (creategrape != NULL)
        a = *creategrape;

    a.id = id++;

    read_in_console_messages("Place:\n", input);
    if (creategrape != NULL && strcmp(input, "-") == 0)
        strcpy(a.place, creategrape->place);
    else
        strcpy(a.place, input);

    read_in_console_messages("Production Place:\n", input);
    if (creategrape != NULL && strcmp(input, "-") == 0)
        strcpy(a.production_place, creategrape->production_place);
    else
        strcpy(a.production_place, input);

    read_in_console_messages("Table:\n", input);
    if (creategrape != NULL && strcmp(input, "-") == 0)
        strcpy(a.table, creategrape->table);
    else
        strcpy(a.table, input);

    read_in_console_messages("Kind:\n", input);
    if (creategrape != NULL && strcmp(input, "-") == 0)
        strcpy(a.kind, creategrape->kind);
    else
        strcpy(a.kind, input);

    printf("Area:\n");
    scanf("%u", &area);

    if (creategrape != NULL && area == 0)
        a.area = creategrape->area;
    else
        a.area = area;

    read_in_console_messages("Type:\n", input);
    if (creategrape != NULL && strcmp(input, "-") == 0)
        strcpy(a.type, creategrape->type);
    else
        strcpy(a.type, input);

    printf("destruction rate:\n");
    scanf("%u", &destruction_rate);

    if (creategrape != NULL && destruction_rate == 0)
        a.destruction_rate = creategrape->destruction_rate;
    else
        a.destruction_rate = destruction_rate;

    FILE *outfile = fopen("adatbazis.txt", "a");

    if (outfile == NULL)
    {
        fprintf(stderr, "File open error!\n");
        exit(1);
    }

    if (fprintf(outfile, "%u\t%s\t%s\t%s\t%s\t%u\t%s\t%u\n",
                a.id, a.place, a.production_place, a.table, a.kind, a.area, a.type, a.destruction_rate) > 0)
    {
        printf("Grape successfully added!\n");
    }
    else
    {
        printf("Write error occurred!\n");
    }

    fclose(outfile);
    outfile = NULL;
}

void grape_update()
{
    struct grape a = {0};
    char input[50];
    unsigned int search_id;
    unsigned int area = 0;
    unsigned int destruction_rate = 0.0;

    printf("Enter ID:\n");
    scanf("%u", &search_id);

    FILE *infile = fopen("adatbazis.txt", "r");

    if (infile == NULL)
    {
        fprintf(stderr, "File open error!\n");
        return;
    }

    struct grape *record = NULL;
    int found = 0;

    while ((record = file_read(infile)) != NULL)
    {
        if (record->id == search_id)
        {
            found = 1;
            a = *record;
            free(record);
            break;
        }
        free(record);
        record = NULL;
    }

    fclose(infile);
    infile = NULL;

    if (!found)
    {
        printf("Record not found!\n");
        return;
    }

    read_in_console_messages("Place:\n", input);
    if (strcmp(input, "-") != 0)
        strcpy(a.place, input);

    read_in_console_messages("Production Place:\n", input);
    if (strcmp(input, "-") != 0)
        strcpy(a.production_place, input);

    read_in_console_messages("Table:\n", input);
    if (strcmp(input, "-") != 0)
        strcpy(a.table, input);

    read_in_console_messages("Kind:\n", input);
    if (strcmp(input, "-") != 0)
        strcpy(a.kind, input);

    printf("Area:\n");
    scanf("%u", &area);

    if (area != 0)
        a.area = area;

    read_in_console_messages("Type:\n", input);
    if (strcmp(input, "-") != 0)
        strcpy(a.type, input);

    printf("Destruction rate:\n");
    scanf("%u", &destruction_rate);

    if (destruction_rate != 0)
        a.destruction_rate = destruction_rate;

    infile = fopen("adatbazis.txt", "r");
    FILE *tempfile = fopen("adatbazis_temp.txt", "w");

    if (infile == NULL || tempfile == NULL)
    {
        fprintf(stderr, "File operation error!\n");
        if (infile != NULL)
            fclose(infile);
        if (tempfile != NULL)
            fclose(tempfile);
        return;
    }

    while ((record = file_read(infile)) != NULL)
    {
        if (record->id == a.id)
        {
            fprintf(tempfile, "%u\t%s\t%s\t%s\t%s\t%u\t%s\t%u\n",
                    a.id, a.place, a.production_place, a.table, a.kind, a.area, a.type, a.destruction_rate);
        }
        else
        {
            fprintf(tempfile, "%u\t%s\t%s\t%s\t%s\t%u\t%s\t%u\n",
                    record->id, record->place, record->production_place, record->table, 
                    record->kind, record->area, record->type, record->destruction_rate);
        }

        free(record);
        record = NULL;
    }

    fclose(infile);
    fclose(tempfile);

    remove("adatbazis.txt");
    rename("adatbazis_temp.txt", "adatbazis.txt");

    printf("\nGrape successfully updated!\n");
}

void grape_remove()
{
    unsigned int search_id;

    printf("Enter ID to delete:\n");
    scanf("%u", &search_id);

    FILE *infile = fopen("adatbazis.txt", "r");

    if (infile == NULL)
    {
        fprintf(stderr, "File open error!\n");
        return;
    }

    struct grape *record = NULL;
    int found = 0;

    while ((record = file_read(infile)) != NULL)
    {
        if (record->id == search_id)
        {
            found = 1;
            free(record);
            break;
        }
        free(record);
        record = NULL;
    }

    fclose(infile);
    infile = NULL;

    if (!found)
    {
        printf("Record with ID %u not found!\n", search_id);
        return;
    }

    infile = fopen("adatbazis.txt", "r");
    FILE *tempfile = fopen("adatbazis_temp.txt", "w");

    if (infile == NULL || tempfile == NULL)
    {
        fprintf(stderr, "File operation error!\n");
        if (infile != NULL)
            fclose(infile);
        if (tempfile != NULL)
            fclose(tempfile);
        return;
    }

    while ((record = file_read(infile)) != NULL)
    {
        if (record->id != search_id)
        {
            fprintf(tempfile, "%u\t%s\t%s\t%s\t%s\t%u\t%s\t%u\n",
                    record->id, record->place, record->production_place, record->table, 
                    record->kind, record->area, record->type, record->destruction_rate);
        }

        free(record);
        record = NULL;
    }

    fclose(infile);
    fclose(tempfile);

    remove("adatbazis.txt");
    rename("adatbazis_temp.txt", "adatbazis.txt");

    printf("\nGrape successfully deleted!\n");
}

void list(int search_type)
{
    char search[50];
    if (search_type == 1) {
        read_in_console_messages("Production place: ", search);
        printf("\n");
    } else if (search_type == 2) {
        read_in_console_messages("Type: ", search);
        printf("\n");
    }

    FILE *f = fopen("adatbazis.txt", "r");
    if (f == NULL)
    {
        printf("File open error!\n");
        return;
    }

    struct grape *a;
    int count = 0;

    while ((a = file_read(f)) != NULL)
    {
        if (search_type == 0) {
            print_database(a);
            count++;
        } else if (search_type == 1 ? strcmp(a->production_place, search) == 0 : strcmp(a->type, search) == 0) {
            print_database(a);
            count++;
        }
        free(a);
    }

    fclose(f);

    if (count == 0)
        printf("None found!\n");
}

int main()
{
    int command;
    file_creation();
    id_setup();

    while (1)
    {
        printf("\n-----MENU-----\n");
        printf("Grape_felvetel - 1\n");
        printf("Grape_frissites - 2\n");
        printf("Grape_torles - 3\n");
        printf("List_mind - 4\n");
        printf("List_production_place - 5\n");
        printf("List_type - 6\n");
        printf("Kilep - 7\n\n");

        command = getchar();

        if (command == '\n')
            continue;

        switch (command)
        {
            case '1':
                grape_creation(NULL);
                break;

            case '2':
                grape_update();
                break;

            case '3':
                grape_remove();
                break;

            case '4':
                list(0);
                break;

            case '5':
                list(1);
                break;

            case '6':
                list(2);
                break;

            case '7':
                printf("Kilepes\n");
                exit(0);
            
            default:
                printf("Invalid command!\n");
        }

        while (getchar() != '\n');
    }

    return 0;
}
