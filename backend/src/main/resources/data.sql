/*LANGUAGES*/
INSERT INTO
    language (
        ts_created,
        ts_modified,
        language_id,
        language,
        imports,
        main,
        judge_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        1,
        'Java',
        '',
        'public class Main {
            public static void main(String[] args){
            }
        }',
        62
    );

INSERT INTO
    language (
        ts_created,
        ts_modified,
        language_id,
        language,
        imports,
        main,
        judge_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        2,
        'C',
        '#include <stdio.h>
        ',
        'int main() {
            return 0;
        }',
        50
    );

INSERT INTO
    language (
        ts_created,
        ts_modified,
        language_id,
        language,
        imports,
        main,
        judge_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        3,
        'Python',
        '',
        '--SOLUTION-HERE--',
        71
    );

INSERT INTO
    language (
        ts_created,
        ts_modified,
        language_id,
        language,
        imports,
        main,
        judge_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        4,
        'Javascript',
        '',
        '--SOLUTION-HERE--',
        63
    );

/* PROGRAMMERS */
INSERT INTO
    programmer (
        ts_created,
        ts_modified,
        programmer_id,
        username,
        email,
        password,
        role,
        task_points,
        solution_points
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        1,
        'PP',
        'pp@email.com',
        '$2y$12$A5dMg1i8IBdguxPHW6y2T.ljLswMdiLoiH0CX3mAVZjHi1Oz9gJBS',
        'ADMIN',
        1023,
        2212
    );

INSERT INTO
    programmer (
        ts_created,
        ts_modified,
        programmer_id,
        username,
        email,
        password,
        role,
        task_points,
        solution_points
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        2,
        'BigBertha',
        'bigBertha@email.com',
        '$2y$12$6J74jRs1b6LVcLGvWVm37OsCGQpAUM7UDkoEzbnEneVm8kqLgV.ke',
        'USER',
        255,
        1203
    );

INSERT INTO
    programmer (
        ts_created,
        ts_modified,
        programmer_id,
        username,
        email,
        password,
        role,
        task_points,
        solution_points
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        3,
        'Lelok',
        'Lelok@email.com',
        '$2y$12$TM1DnU.F2iYaACgfQWFsruC2zcSxKM7xT6Mh/stbxWvlWz0roTffy',
        'USER',
        332,
        13
    );

INSERT INTO
    programmer (
        ts_created,
        ts_modified,
        programmer_id,
        username,
        email,
        password,
        role,
        task_points,
        solution_points
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        4,
        'Kovakov',
        'Kovakov@email.com',
        '$2y$12$oW4jFJOCFc07n4lmWbWSuOwAs0tnIKe4z4PZ1vtEeHojAFVy9oT9O',
        'USER',
        18,
        5
    );

INSERT INTO
    programmer (
        ts_created,
        ts_modified,
        programmer_id,
        username,
        email,
        password,
        role,
        task_points,
        solution_points
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        5,
        'Mucero',
        'Mucero@email.com',
        '$2y$12$W0EmyNEPJ7PyPubegYF/sO3uEp4EnhXBNMZmFVHI2U4a6ff1V2K36',
        'USER',
        2,
        0
    );

/* TASKS */
--PP
INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        1,
        1,
        'Zbrojite dva broja',
        null,
        '3 4',
        '7'
    );
INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        2,
        1,
        'Oduzmite dva broja',
        null,
        '3 4',
        '-1'
    );
INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        3,
        1,
        'Pomnožite dva broja',
        null,
        '3 4',
        '12'
    );
INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        4,
        1,
        'Podijelite dva broja',
        null,
        '3 4',
        '0.75'
    );
--BIG BERTHA
INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        5,
        2,
        'Fibonnaci do 5!',
        null,
        '--NULL--',
        '0 1 1 2 3 5'
    );
INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        6,
        2,
        'Je li broj neparan broj?',
        null,
        '8',
        'False'
    );
    INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        7,
        2,
        'Custom sorter',
        null,
        '5 2 3 1',
        '1 2 3 5'
    );
--LELOK
INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        8,
        3,
        'Vjerojatnost da na kocki padne broj?',
        null,
        '1',
        '1/6'
    );
INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        9,
        3,
        'Izracunati koliko je elemenata u danom stringu?',
        null,
        'Marko jede zdravo.',
        '18'
    );
--KOVAKOV
INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        10,
        4,
        'Suma prvih n znakova',
        null,
        '3',
        '6'
    );
--MUCERO
INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        11,
        5,
        'Ispišite sve brojeve do broja 10',
        null,
        '--NULL--',
        '12345678910'
    );
INSERT INTO
    task (
        ts_created,
        ts_modified,
        task_id,
        author_id,
        task_text,
        author_solution_id,
        input_format,
        output_format
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        12,
        5,
        'Number spliter',
        null,
        '54|21',
        '54 21'
    );

/* TASK LANGUAGES */
--TASK_ID 1
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (1, 1);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (1, 2);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (1, 3);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (1, 4);
--TASK ID 2
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (2, 1);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (2, 2);
--TASK_ID 3
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (3, 3);

INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (3, 4);
--TASK ID 4
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (4, 2);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (4, 3);
--TASK ID 5
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (5, 1);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (5, 2);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (5, 3);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (5, 4);
--TASK ID 6
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (6, 1);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (6, 2);
--TASK ID 7
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (7, 1);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (7, 2);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (7, 4);
--TASK ID 8
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (8, 1);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (8, 2);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (8, 3);
--TASK ID 9
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (9, 4);
--TASK ID 10
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (10, 1);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (10, 3);
--TASK ID 11
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (11, 1);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (11, 2);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (11, 3);
--TASK ID 12
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (12, 1);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (12, 2);
INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (12, 3);

/* TASK COMMENTS */
INSERT INTO
    task_comment (
        ts_created,
        ts_modified,
        task_comment_id,
        comment,
        commenter_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        1,
        'Ne sviđa mi se.',
        2,
        1
    );

INSERT INTO
    task_comment (
        ts_created,
        ts_modified,
        task_comment_id,
        comment,
        commenter_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        2,
        'Nije loš!',
        1,
        3
    );

INSERT INTO
    task_comment (
        ts_created,
        ts_modified,
        task_comment_id,
        comment,
        commenter_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        3,
        'Potrudit cu se ubuduce da budu bolji!',
        1,
        1
    );

INSERT INTO
    task_comment (
        ts_created,
        ts_modified,
        task_comment_id,
        comment,
        commenter_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        4,
        'Bolje ga obrisi!',
        4,
        1
    );

INSERT INTO
    task_comment (
        ts_created,
        ts_modified,
        task_comment_id,
        comment,
        commenter_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        5,
        'Apsolutna koma...',
        3,
        1
    );

/* TASK GRADES */
--PP
INSERT INTO
    task_grade (
        ts_created,
        ts_modified,
        task_grade_id,
        grade,
        grader_id,
        task_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 4, 1, 9);
INSERT INTO
    task_grade (
        ts_created,
        ts_modified,
        task_grade_id,
        grade,
        grader_id,
        task_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 4, 1, 10);
--BIG BERTHA
INSERT INTO
    task_grade (
        ts_created,
        ts_modified,
        task_grade_id,
        grade,
        grader_id,
        task_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 2, 2, 1);
INSERT INTO
    task_grade (
        ts_created,
        ts_modified,
        task_grade_id,
        grade,
        grader_id,
        task_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 5, 2, 4);
--LELOK
INSERT INTO
    task_grade (
        ts_created,
        ts_modified,
        task_grade_id,
        grade,
        grader_id,
        task_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 2, 3, 6);
--MUCERO
INSERT INTO
    task_grade (
        ts_created,
        ts_modified,
        task_grade_id,
        grade,
        grader_id,
        task_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 1, 5, 12);

INSERT INTO
    task_grade (
        ts_created,
        ts_modified,
        task_grade_id,
        grade,
        grader_id,
        task_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 3, 5, 1);

/* SOLUTIONS */
--PP
INSERT INTO
    solution (
        ts_created,
        ts_modified,
        solution_id,
        code,
        language_id,
        author_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        1,
        'const readline = require("readline").createInterface({
        input: process.stdin,
        output: process.stdout
        });

        readline.on("line", input => {
            process.stdout.write(input.length);
            readline.close();
        })',
        4,
        1,
        9
    );
INSERT INTO
    solution (
        ts_created,
        ts_modified,
        solution_id,
        code,
        language_id,
        author_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        2,
        'input = int(input())
        for i in range(input):
            print(i)',
        3,
        1,
        10
    );
INSERT INTO
    solution (
        ts_created,
        ts_modified,
        solution_id,
        code,
        language_id,
        author_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        3,
        'const readline = require("readline").createInterface({
        input: process.stdin,
        output: process.stdout
        });

        readline.on("line", input => {
            const array = input.split(" ");
            process.stdout.write(array[0] + array[1]);
            readline.close();
        })',
        4,
        1,
        1
    );

--BIG BERTHA
INSERT INTO
    solution (
        ts_created,
        ts_modified,
        solution_id,
        code,
        language_id,
        author_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        4,
        '#include <stdio.h>
        int main() { 
            int number1, number2, sum;
            scanf("%d %d", &number1, & number2);
            sum = number1 + number2;
            printf("%d", sum);
            return 0;
        }',
        2,
        2,
        1
    );

INSERT INTO
    solution (
        ts_created,
        ts_modified,
        solution_id,
        code,
        language_id,
        author_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        5,
        '#include <stdio.h>
        int main() { 
            int number1, number2;
            double division;
            scanf("%d %d", &number1, & number2);
            division = (number1 \ (double) number2);
            printf("%f", division);
            return 0;
        }',
        2,
        2,
        4
    );
--LELOK
INSERT INTO
    solution (
        ts_created,
        ts_modified,
        solution_id,
        code,
        language_id,
        author_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        6,
        '#include <stdio.h>
        int main() { 
            int n1;
            scanf("%d", & n1);
            if(n1 % 2 != 0) printf("False");
            else printf("True");
            return 0;
        }',
        2,
        3,
        6
    );
--MUCERO
INSERT INTO
    solution (
        ts_created,
        ts_modified,
        solution_id,
        code,
        language_id,
        author_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        7,
        'const readline = require("readline").createInterface({
        input: process.stdin,
        output: process.stdout
        });

        readline.on("line", input => {
            const array = input.split("|");
            process.stdout.write(array.join(" "));
            readline.close();
        })',
        4,
        5,
        12
    );
INSERT INTO
    solution (
        ts_created,
        ts_modified,
        solution_id,
        code,
        language_id,
        author_id,
        task_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        8,
        'const readline = require("readline").createInterface({
        input: process.stdin,
        output: process.stdout
        });

        readline.on("line", input => {
            const array = input.split(" ");
            process.stdout.write(array[0] * 1 + array[1] * 1);
            readline.close();
        })',
        4,
        5,
        1
    );

UPDATE
    task
SET
    author_solution_id = 3,
    ts_modified = CURRENT_TIMESTAMP
WHERE
    task.author_id = 1
    and task.task_id = 1;

/* SOLUTION COMMENTS */
INSERT INTO
    solution_comment (
        ts_created,
        ts_modified,
        solution_comment_id,
        comment,
        commenter_id,
        solution_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        1,
        'Veoma jednostavno i elegantno.',
        2,
        1
    );

INSERT INTO
    solution_comment (
        ts_created,
        ts_modified,
        solution_comment_id,
        comment,
        commenter_id,
        solution_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        2,
        'Baš zgodno!',
        1,
        1
    );

INSERT INTO
    solution_comment (
        ts_created,
        ts_modified,
        solution_comment_id,
        comment,
        commenter_id,
        solution_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        3,
        'One liner javascriptni!',
        1,
        2
    );

INSERT INTO
    solution_comment (
        ts_created,
        ts_modified,
        solution_comment_id,
        comment,
        commenter_id,
        solution_id
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        4,
        'Daj razmisli jel se može kraće.',
        4,
        2
    );

/* SOLUTION GRADES */
INSERT INTO
    solution_grade (
        ts_created,
        ts_modified,
        solution_grade_id,
        grade,
        grader_id,
        solution_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2, 1, 1);

INSERT INTO
    solution_grade (
        ts_created,
        ts_modified,
        solution_grade_id,
        grade,
        grader_id,
        solution_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 4, 2, 2);

INSERT INTO
    solution_grade (
        ts_created,
        ts_modified,
        solution_grade_id,
        grade,
        grader_id,
        solution_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 2, 3, 1);

INSERT INTO
    solution_grade (
        ts_created,
        ts_modified,
        solution_grade_id,
        grade,
        grader_id,
        solution_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 5, 1, 2);

INSERT INTO
    solution_grade (
        ts_created,
        ts_modified,
        solution_grade_id,
        grade,
        grader_id,
        solution_id
    )
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 4, 4, 2);

/* TEST CASES */
--TASK 1
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        1,
        '3 4',
        '7'
    );
--TASK 2
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        2,
        '3 4',
        '-1'
    );
--TASK 3
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        3,
        '3 4',
        '12'
    );
--TASK 4
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        4,
        '3 4',
        '0.75'
    );
--TASK 5
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        5,
        '--NULL--',
        '0 1 1 2 3 5'
    );
--TASK 6
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        6,
        '8',
        'False'
    );
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        6,
        '9',
        'True'
    );
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        6,
        '1',
        'True'
    );
--TASK 7
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        7,
        '1 3 2 4 5',
        '1 2 3 4 5'
    );
--TASK 8
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        8,
        '3',
        '1/6'
    );
--TASK 9
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        9,
        'Dan',
        '3'
    );
--TASK 10
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        10,
        '4',
        '10'
    );
--TASK 11
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        11,
        '--NULL--',
        '12345678910'
    );
--TASK 12
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        12,
        '22|31',
        '22 31'
    );
INSERT INTO
    test_case (
        ts_created,
        ts_modified,
        task_id,
        input_data,
        output_data
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        12,
        '33|123|31',
        '33 123 31'
    );
/* FOLLOWERSHIPS */
--PP
INSERT INTO
    followership (
        ts_created,
        ts_modified,
        pending,
        follower,
        programmer
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'FALSE',
        5,
        1
    );
INSERT INTO
    followership (
        ts_created,
        ts_modified,
        pending,
        follower,
        programmer
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'FALSE',
        4,
        1
    );
INSERT INTO
    followership (
        ts_created,
        ts_modified,
        pending,
        follower,
        programmer
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'FALSE',
        3,
        1
    );
INSERT INTO
    followership (
        ts_created,
        ts_modified,
        pending,
        follower,
        programmer
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'FALSE',
        2,
        1
    );
--BERTHA
INSERT INTO
    followership (
        ts_created,
        ts_modified,
        pending,
        follower,
        programmer
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'FALSE',
        1,
        2
    );
INSERT INTO
    followership (
        ts_created,
        ts_modified,
        pending,
        follower,
        programmer
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'FALSE',
        3,
        2
    );
--MUCERO
INSERT INTO
    followership (
        ts_created,
        ts_modified,
        pending,
        follower,
        programmer
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'FALSE',
        2,
        5
    );
INSERT INTO
    followership (
        ts_created,
        ts_modified,
        pending,
        follower,
        programmer
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'FALSE',
        1,
        5
    );