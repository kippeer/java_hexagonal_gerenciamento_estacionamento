package com.parking.infrastructure.adapter.in.web.dto;

import com.parking.domain.model.SlotStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlotStatusUpdateDto {
    @NotNull(message = "Status is required")
    private SlotStatus status;
}