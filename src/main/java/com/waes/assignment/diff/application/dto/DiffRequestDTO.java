package com.waes.assignment.diff.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "Represent a valid request body to save a Base64 encoded JSON")
public class DiffRequestDTO {

    @NotEmpty
    @ApiModelProperty(notes = "Base64 encoded JSON")
    private String base64EncodedJson;

    public String getBase64EncodedJson() {
        return base64EncodedJson;
    }

    public void setBase64EncodedJson(String base64EncodedJson) {
        this.base64EncodedJson = base64EncodedJson;
    }
}
