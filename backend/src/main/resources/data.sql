/*LANGUAGES*/
INSERT INTO
    language (
        ts_created,
        ts_modified,
        language_id,
        language,
        imports,
        main
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        1,
        'Java',
        '',
        'public class Main {
            private static --RETURN-TYPE-- solution(--INPUT-ARGUMENTS--){}
            
            static void main(String[] args){
                //Write solution here...
            }
        }'
    );

INSERT INTO
    language (
        ts_created,
        ts_modified,
        language_id,
        language,
        imports,
        main
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        2,
        'C',
        '#include <stdio.h>',
        '--RETURN-TYPE-- solution(--INPUT-ARGUMENTS--){}
        int main() { 
            //Write your solution here...
            return 0;
        }'
    );

INSERT INTO
    language (
        ts_created,
        ts_modified,
        language_id,
        language,
        imports,
        main
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        3,
        'Python',
        '',
        '--SOLUTION-HERE--'
    );

INSERT INTO
    language (
        ts_created,
        ts_modified,
        language_id,
        language,
        imports,
        main
    )
VALUES
    (
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        4,
        'Javascript',
        '',
        '--SOLUTION-HERE--'
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
        5,
        'Ispišite sve brojeve do broja 10',
        null,
        '',
        '1 2 3 4 5 ...'
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
        2,
        'Custom sorter',
        null,
        '5 2 3 1',
        '1 2 3 5'
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
        5,
        3,
        'Number spliter',
        null,
        '54|21',
        '54 21'
    );

/* TASK LANGUAGES */
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

INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (2, 1);

INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (2, 2);

INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (3, 3);

INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (3, 4);

INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (4, 3);

INSERT INTO
    TASK_WRITTEN_IN (TASK_TASK_ID, WRITTEN_IN_LANGUAGE_ID)
VALUES
    (5, 4);

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
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2, 1, 3);

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
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 4, 2, 1);

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
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 2, 3, 2);

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
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 5, 4, 2);

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
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 4, 5, 2);

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
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 4, 3, 1);

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
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 3, 4, 1);

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
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, 2, 5, 1);

/* SOLUTIONS */
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
        2,
        'for(let i = 0; i < 10; i++) {
            Console.log(i+1);
        }',
        4,
        1,
        3
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
        '#include <stdio.h>\n
        int main() { 
            int n1, n2;
            scanf("%d %d", & number1, & number2);
            printf("%d", n1+n2);
            return 0;
        }',
        4,
        1,
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