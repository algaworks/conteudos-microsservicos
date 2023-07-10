package com.algaworks.example.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @GetMapping
    public String ok() {
        return "OK";
    }

    @PostMapping
    public void receive(@RequestBody String message) {
        System.out.println("Mensagem recebida:");
        System.out.println(message);
    }

}
