package com.abhaempire.complifybook.dtos;

import com.abhaempire.complifybook.models.enums.StatusTypeEnum;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TestimonialResponse {
    private int id;
    private String imagePath;
    private String reviewerName;
    private String position;
    private float rating;
    private LocalDate createdAt;
    private StatusTypeEnum status;
}
