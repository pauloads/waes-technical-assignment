package com.waes.assignment.diff.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.waes.assignment.diff.domain.model.ContentStatus;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DiffResponseDTO {

    private ContentStatus status;

    private String offsets;

    private Integer lenght;

    public String getOffsets() {
        return offsets;
    }

    public void setOffsets(String offsets) {
        this.offsets = offsets;
    }

    public Integer getLenght() {
        return lenght;
    }

    public void setLenght(Integer lenght) {
        this.lenght = lenght;
    }

    public ContentStatus getStatus() {
        return status;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
    }
}
