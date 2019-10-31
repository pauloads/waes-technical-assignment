package com.waes.assignment.diff.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.waes.assignment.diff.domain.model.ContentStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(description = "Contains data that represents the difference between contents")
public class DiffResponseDTO {

    private ContentStatus status;

    @ApiModelProperty(notes = "Represents positions of the differences")
    private String offsets;

    @ApiModelProperty(notes = "Represents the content size if they are of same size and different content")
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
