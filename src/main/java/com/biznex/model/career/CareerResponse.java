package com.biznex.model.career;

import com.biznex.common.constant.JobTypeEnum;
import com.biznex.common.constant.Name;
import com.biznex.common.response.TechnicalFieldsResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CareerResponse extends TechnicalFieldsResponse {

    private Long id;
    private Name name;
    private Name description;
    private JobTypeEnum jobType;
    private Name salaryRange;

    public CareerResponse(Career career) {
        this.id = career.getId();
        this.name = career.getName();
        this.description = career.getDescription();
        this.jobType = career.getJobType();
        this.salaryRange = career.getSalaryRange();
        this.setCreatedAt(career.getCreatedAt());
        this.setStatus(career.getStatus());
    }

    public static CareerResponse minResponse(Career career) {
        CareerResponse response = new CareerResponse();
        response.setId(career.getId());
        response.setName(career.getName());
        response.setDescription(career.getDescription());
        response.setJobType(career.getJobType());
        response.setSalaryRange(career.getSalaryRange());
        return response;
    }
}
