package com.lyugge.alpha_task.Controllers;

import com.lyugge.alpha_task.Services.ICurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ICurrencyService service;

    @GetMapping("/relation/{from}/usd")
    public String getGif(@PathVariable String from, Model model) {
        model.addAttribute("url", service.necessaryGif(from.toUpperCase(Locale.ROOT)));
        return "index";
    }
}
