package ru.hogwarts.schoolpage.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.schoolpage.service.InfoService;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }
    @GetMapping
    public void testParallelStream() {
      infoService.testParallelStream();
    }

    @GetMapping("/printStudents")
    public void printStudents() {
        infoService.printStudents();
    }

    @GetMapping("/printStudentsSync")
    public void printStudentsSync() {
        infoService.printStudentsSync();
    }


}
