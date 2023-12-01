package de.thb.groupChat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NumberController {

    @GetMapping("/")
    public String controlNumber(){
        return "";
    }
}
