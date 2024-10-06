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
public class SubCategoryResponse {
    private int id;
    private String iconName;
    private String categoryName;
    private String subCategoryName;
    private StatusTypeEnum status;
    private LocalDate createdAt;
}
