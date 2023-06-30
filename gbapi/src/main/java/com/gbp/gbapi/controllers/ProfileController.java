package com.gbp.gbapi.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

// TODO: TEST ROLE-BASED ACCESS

@RestController
@RequestMapping("/api")
public class ProfileController {

    @GetMapping("/profile")
    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String accessProtectedResource() {
        return "If you are reading this, you are authorized!";
    }
}


