package dev.sorokin.service;

import org.springframework.stereotype.Component;

@Component
public class UniversalService {
    public static double rounder(double num, int places) {
        double divider = Math.pow(10, places);
        return Math.ceil(num * divider) / divider;
    }
}
