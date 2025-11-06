package com.josigtz.cryptoModule.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FielDto {
    private String certificateSerie;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;
}
