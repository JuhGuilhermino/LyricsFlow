package com.example.lyricsflow_backend.controller;

import com.example.lyricsflow_backend.business.DashboardBusiness;
import com.example.lyricsflow_backend.dto.DashboardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardBusiness dashboardBusiness;

    public DashboardController(DashboardBusiness dashboardBusiness) {
        this.dashboardBusiness = dashboardBusiness;
    }

    // GET /api/dashboard/{userId}
    @GetMapping("/{userId}")
    public ResponseEntity<DashboardDTO> getDashboard(@PathVariable Long userId) {
        DashboardDTO dashboard = dashboardBusiness.getUserDashboard(userId);
        return ResponseEntity.ok(dashboard);
    }
}
