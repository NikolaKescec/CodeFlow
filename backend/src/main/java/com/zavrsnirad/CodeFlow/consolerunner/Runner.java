/*
package com.zavrsnirad.CodeFlow.consolerunner;

import com.zavrsnirad.CodeFlow.domain.*;
import com.zavrsnirad.CodeFlow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private ProgrammerRepository programmerRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskGradeRepository taskGradeRepository;

    @Autowired
    private TaskCommentRepository taskCommentRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public void run(String... args) throws Exception {

        // LANGUAGES
        Language java = languageRepository.save(new Language("java", "", "public class Main {\n" +
                "\n" +
                "static void main(String[] args){\n" +
                "\n" +
                "//write solution here..." +
                "\t\n" +
                "}\n" +
                "}"));
        Language c = languageRepository.save(new Language("C", "#include <stdio.h>",
                "int main() {\n" +
                "   // write your solution here..." +
                "   return 0;\n" +
                "}"));

        //bigPP
        Programmer PP = programmerRepository.save(new Programmer("PP", "pp@email.com", "$2y$12$A5dMg1i8IBdguxPHW6y2T.ljLswMdiLoiH0CX3mAVZjHi1Oz9gJBS", "ADMIN", 1023, 2212));
        //catCatcat
        Programmer BigBertha = programmerRepository.save(new Programmer("BigBertha", "bigBertha@email.com", "$2y$12$6J74jRs1b6LVcLGvWVm37OsCGQpAUM7UDkoEzbnEneVm8kqLgV.ke","USER",255, 1203));
        //konzumWOM
        Programmer Lelok = programmerRepository.save(new Programmer("Lelok", "Lelok@email.com", "$2y$12$TM1DnU.F2iYaACgfQWFsruC2zcSxKM7xT6Mh/stbxWvlWz0roTffy", "USER", 332, 13));
        //motoriImotori
        Programmer Kovakov = programmerRepository.save(new Programmer("Kovakov", "Kovakov@email.com", "$2y$12$oW4jFJOCFc07n4lmWbWSuOwAs0tnIKe4z4PZ1vtEeHojAFVy9oT9O",  "USER", 18, 5));
        //lošMikrofonGuy
        Programmer Mucero = programmerRepository.save(new Programmer("Mucero", "Mucero@email.com", "$2y$12$W0EmyNEPJ7PyPubegYF/sO3uEp4EnhXBNMZmFVHI2U4a6ff1V2K36",  "USER", 2, 0));

        // some tasks
        // TASK 1
        Task task1 = taskRepository.save(new Task(PP, "nekakvi zadatak u javi", "Java", "12"));
        taskRepository.save(task1);
        TaskGrade task1Grade1 = taskGradeRepository.save(new TaskGrade(3, Kovakov, task1));
        TaskGrade task1Grade2 = taskGradeRepository.save(new TaskGrade(2, BigBertha, task1));
        TaskGrade task1Grade3 = taskGradeRepository.save(new TaskGrade(3, Mucero, task1));
        TaskGrade task1Grade4 = taskGradeRepository.save(new TaskGrade(5, Lelok, task1));
        TaskComment task1Comment1 = taskCommentRepository.save(new TaskComment("Dobar zadatak u javi", PP, task1));
        TaskComment task1Comment2 = taskCommentRepository.save(new TaskComment("pliz daj malo truda", Kovakov, task1));
        TaskComment task1Comment3 = taskCommentRepository.save(new TaskComment("Trivijalno.", BigBertha, task1));
        TaskComment task1Comment4 = taskCommentRepository.save(new TaskComment("Doslovno najbolji zadatak ikad.", Mucero, task1));

        Solution task1AuthorSolution = solutionRepository.save(new Solution("return 12", c, PP));
        task1.setAuthorSolution(task1AuthorSolution);
        task1.addSolution(task1AuthorSolution);
        taskRepository.save(task1);

        Solution task1Solution1 = solutionRepository.save(new Solution("class Main {\n" +
                "\n" +
                "  public static void main(String[] args) {\n" +
                "    \n" +
                "    System.out.println(\"Enter two numbers\");\n" +
                "    int first = 10;\n" +
                "    int second = 20;\n" +
                "    \n" +
                "    System.out.println(first + \" \" + second);\n" +
                "\n" +
                "    // add two numbers\n" +
                "    int sum = first + second;\n" +
                "    System.out.println(\"The sum is: \" + sum);\n" +
                "  }\n" +
                "}", java, Lelok));
        Solution task1Solution2 = solutionRepository.save(new Solution("public class MultiplyTwoNumbers {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "\n" +
                "        float first = 1.5f;\n" +
                "        float second = 2.0f;\n" +
                "\n" +
                "        float product = first * second;\n" +
                "\n" +
                "        System.out.println(\"The product is: \" + product);\n" +
                "    }\n" +
                "}", java, BigBertha));
        task1.addSolution(task1Solution1);
        task1.addSolution(task1Solution2);
        taskRepository.save(task1);

        // TASK 2
        Task task2 = taskRepository.save(new Task(PP, "Novi zadatak", "1 2 3", "4"));
        TaskGrade task2Grade1 = taskGradeRepository.save(new TaskGrade(1, Kovakov, task2));
        TaskGrade task2Grade2 = taskGradeRepository.save(new TaskGrade(2, BigBertha, task2));
        TaskGrade task2Grade3 = taskGradeRepository.save(new TaskGrade(3, Mucero, task2));
        TaskGrade task2Grade4 = taskGradeRepository.save(new TaskGrade(5, Lelok, task2));
        TaskComment task2Comment1 = taskCommentRepository.save(new TaskComment("Ne želim ni gledat, obriši ga ovog trena.", Kovakov, task2));
        TaskComment task2Comment2 = taskCommentRepository.save(new TaskComment("Zapravo, dođe mi da obrišem profil.", Kovakov, task2));
        TaskComment task2Comment3 = taskCommentRepository.save(new TaskComment("Ono što je kovakov reko.", BigBertha, task2));
        TaskComment task2Comment4 = taskCommentRepository.save(new TaskComment("Eh, loše.", Mucero, task2));
        TaskComment task2Comment5 = taskCommentRepository.save(new TaskComment("Big lajk", Lelok, task2));

        // TASK 3
        Task task3 = taskRepository.save(new Task(PP, "Obožavam javu...", "Novi gospodin", "1"));

        // TASK 4


        // TASK 5

        // TASK 6

        // TASK 7


        // TASK 8

    }

}
*/
