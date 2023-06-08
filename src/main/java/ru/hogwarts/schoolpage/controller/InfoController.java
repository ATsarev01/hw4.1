package ru.hogwarts.schoolpage.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.schoolpage.service.InfoService;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final int port;

    public InfoController(@Value("${server.port}") int port) {
        this.port = port;
    }

    @GetMapping("/port")
    public int getPort() {
        return port;
    }
    @GetMapping
    public void testParallelStream() {
      InfoService.testParallelStream();
    }
}
