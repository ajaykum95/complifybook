package com.abhaempire.complifybook.dtos;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
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
public class PublicServiceResponse {
    private String name;
    private String slug;
    private String summary;
    private float rating;
    private int ratingCount;
}
