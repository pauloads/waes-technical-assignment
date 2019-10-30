package com.waes.assignment.diff.application.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DiffRequestDTO {

    @NotNull
    @NotEmpty
    private String base64EncodedJson;

    public String getBase64EncodedJson() {
        return base64EncodedJson;
    }

    public void setBase64EncodedJson(String base64EncodedJson) {
        this.base64EncodedJson = base64EncodedJson;
    }
}
