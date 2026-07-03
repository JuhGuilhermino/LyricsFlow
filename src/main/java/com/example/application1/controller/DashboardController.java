package com.example.application1.controller;

import com.example.application1.dto.DashboardDTO;
import com.example.application1.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<?> getUserDashboard(@RequestParam Long userId) {
        try {
            DashboardDTO dashboardData = this.dashboardService.getDashboardData(userId);
            return ResponseEntity.ok(dashboardData);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao carregar os dados de progresso: " + e.getMessage());
        }
    }
}
