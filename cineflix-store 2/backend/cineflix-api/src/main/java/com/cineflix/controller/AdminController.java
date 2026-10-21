package com.cineflix.controller;

import com.cineflix.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/estadisticas")
    public Map<String, Object> estadisticas() {
        return adminService.estadisticas();
    }
}
