package com.galaxy.intergalactic;

import com.galaxy.intergalactic.service.InputLineService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@ComponentScan
public class InterGalacticMain {

    public static void main(String[] args) throws IOException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(InterGalacticMain.class);
        InputLineService inputLineService = context.getBean(InputLineService.class);
        Files.lines(Paths.get("src", "main", "resources", "TestInput.txt"))
              .forEach(line -> inputLineService.processInputLine(line));
    }
}
