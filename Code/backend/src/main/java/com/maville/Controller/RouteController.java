package com.maville.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travaux")
public class TravailController {
    @GetMapping
    public List<Travail> getAllTravaux() {
        return travailService.getAll();
    }
}
