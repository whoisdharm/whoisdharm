package com.whoisdharm.expense_tracker;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "The page you are looking for doesn't exist or an unexpected error occurred.");
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
