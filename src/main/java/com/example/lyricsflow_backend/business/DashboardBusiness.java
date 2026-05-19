package com.example.lyricsflow_backend.business;

import com.example.lyricsflow_backend.dto.DashboardDTO;
import com.example.lyricsflow_backend.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class DashboardBusiness {

    private final DashboardService dashboardService;

    public DashboardBusiness(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    public DashboardDTO getUserDashboard(Long userId) {
        return dashboardService.getDashboardData(userId);
    }
}