package com.example.demo;

import com.example.demo.exceptions.UserNotLoggedInException;
import com.example.demo.exceptions.ValidationException;
import com.example.demo.servicies.impl.ExecutorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final ExecutorServiceImpl executorService;

    @Autowired
    public ConsoleRunner(ExecutorServiceImpl executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        String commandData = sc.nextLine();

        String result;

        try {
            result = executorService.execute(command, commandData);
        } catch (ValidationException | UserNotLoggedInException ex) {
            result = ex.getMessage();
        }

        System.out.println(result);


    }
}
