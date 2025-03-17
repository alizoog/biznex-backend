package com.biznex.model.career;

import com.biznex.common.constant.JobTypeEnum;
import com.biznex.common.constant.Name;
import com.biznex.common.constant.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CareerPayload {

    @NotNull
    private Name name;
    @NotNull
    private Name description;
    @NotNull
    private Name salaryRange;
    private JobTypeEnum jobType;
    private Status status;
}
