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
public class ServiceDetailResponse {
    private int id;
    private String tabName;
    private int tabOrder;
    private StatusTypeEnum status;
    private LocalDate createdAt;
}
