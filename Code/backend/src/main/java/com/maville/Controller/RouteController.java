package com.maville.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RouteController {
    @RequestMapping("/")
    public String index() {
        return "index.jsx";
    }
    
}
