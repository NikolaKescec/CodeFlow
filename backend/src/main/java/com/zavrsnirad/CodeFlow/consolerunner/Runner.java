package com.zavrsnirad.CodeFlow.consolerunner;

import com.zavrsnirad.CodeFlow.domain.Task;
import com.zavrsnirad.CodeFlow.domain.TaskGrade;
import com.zavrsnirad.CodeFlow.domain.User;
import com.zavrsnirad.CodeFlow.repository.TaskGradeRepository;
import com.zavrsnirad.CodeFlow.repository.TaskRepository;
import com.zavrsnirad.CodeFlow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskGradeRepository taskGradeRepository;

    @Override
    public void run(String... args) throws Exception {
        //bigPP
        User PP = userRepository.save(new User("PP", "pp@email.com", "$2y$12$A5dMg1i8IBdguxPHW6y2T.ljLswMdiLoiH0CX3mAVZjHi1Oz9gJBS", "ADMIN", 1023));
        //catCatcat
        User BigBertha =userRepository.save(new User("BigBertha", "bigBertha@email.com", "$2y$12$6J74jRs1b6LVcLGvWVm37OsCGQpAUM7UDkoEzbnEneVm8kqLgV.ke","USER",255));
        //konzumWOM
        User Lelok =userRepository.save(new User("Lelok", "Lelok@email.com", "$2y$12$TM1DnU.F2iYaACgfQWFsruC2zcSxKM7xT6Mh/stbxWvlWz0roTffy", "USER", 332));
        //motoriImotori
        User Kovakov =userRepository.save(new User("Kovakov", "Kovakov@email.com", "$2y$12$oW4jFJOCFc07n4lmWbWSuOwAs0tnIKe4z4PZ1vtEeHojAFVy9oT9O",  "USER", 18));
        //lošMikrofonGuy
        User Mucero =userRepository.save(new User("Mucero", "Mucero@email.com", "$2y$12$W0EmyNEPJ7PyPubegYF/sO3uEp4EnhXBNMZmFVHI2U4a6ff1V2K36",  "USER", 2));

        // some tasks
        // TASK 1
        Task task1 = taskRepository.save(new Task(PP, "nekakvi zadatak u javi", "Java", "12", null));
        TaskGrade task1Grade1 = taskGradeRepository.save(new TaskGrade(3, Kovakov, task1));
        TaskGrade task1Grade2 = taskGradeRepository.save(new TaskGrade(2, BigBertha, task1));
        TaskGrade task1Grade3 = taskGradeRepository.save(new TaskGrade(3, Mucero, task1));
        TaskGrade task1Grade4 = taskGradeRepository.save(new TaskGrade(5, Lelok, task1));

        // TASK 2
        Task task2 = taskRepository.save(new Task(PP, "novi zadatak u javi", "Java", "11", "class Main {\n" +
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
                "}"));
        TaskGrade task2Grade1 = taskGradeRepository.save(new TaskGrade(1, Kovakov, task2));
        TaskGrade task2Grade2 = taskGradeRepository.save(new TaskGrade(2, BigBertha, task2));
        TaskGrade task2Grade3 = taskGradeRepository.save(new TaskGrade(3, Mucero, task2));
        TaskGrade task2Grade4 = taskGradeRepository.save(new TaskGrade(5, Lelok, task2));

        // TASK 3
        Task task3 = taskRepository.save(new Task(PP, "Obožavam javu...", "Java", "6", "public class QuotientRemainder {\n" +
                "\n" +
                "  public static void main(String[] args) {\n" +
                "\n" +
                "    int dividend = 25, divisor = 4;\n" +
                "\n" +
                "    int quotient = dividend / divisor;\n" +
                "    int remainder = dividend % divisor;\n" +
                "\n" +
                "    System.out.println(\"Quotient = \" + quotient);\n" +
                "    System.out.println(\"Remainder = \" + remainder);\n" +
                "  }\n" +
                "}"));

        // TASK 4
        Task task4 = taskRepository.save(new Task(BigBertha, "nekakvi zadatak u C", "C", "12", "#include <stdio.h>\n" +
                "int main() {\n" +
                "   // printf() displays the string inside quotation\n" +
                "   printf(\"Hello, World!\");\n" +
                "   return 0;\n" +
                "}"));

        // TASK 5
        Task task5 = taskRepository.save(new Task(BigBertha, "nekakvi zadatak u Pythonu", "Python", "Data", null));

        // TASK 6
        Task task6 = taskRepository.save(new Task(Mucero, "Javascript!!", "Javascript", "12", null));

        // TASK 7
        Task task7 = taskRepository.save(new Task(Mucero, "Volim javascript", "Javascript", "12", "const number = prompt('Enter the number: ');\n" +
                "\n" +
                "const result = Math.sqrt(number);\n" +
                "console.log(`The square root of ${number} is ${result}`);"));

        // TASK 8
        Task task8 = taskRepository.save(new Task(Mucero, "Zbroji dva i dva", "Javascript", "4", "console.log(2+2)"));

    }

}
