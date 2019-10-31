package com.waes.assignment.diff.application.controllers;

import com.waes.assignment.diff.application.dto.DiffDTO;
import com.waes.assignment.diff.application.dto.DiffRequestDTO;
import com.waes.assignment.diff.application.dto.DiffResponseDTO;
import com.waes.assignment.diff.application.services.DiffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Provides the difference between two Base64 encoded JSON")
@RestController
@RequestMapping("/v1/diff")
public class DiffController {

    private DiffService diffService;

    public DiffController(DiffService diffService) {
        this.diffService = diffService;
    }

    /**
     * Endpoint that handle requests to save the diff sides
     *
     * @param diffRequestDTO DTO responsible for requests
     * @param id             diff identity
     * @param side           the side of diff
     */
    @ApiOperation(value = "Save Base64 encoded value for a specified side")
    @PostMapping("/{id}/{side}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final DiffRequestDTO diffRequestDTO,
                       @PathVariable("id") final Long id, @PathVariable("side") final String side) {

        DiffDTO dto = new DiffDTO()
                .withEncodedJson(diffRequestDTO.getBase64EncodedJson())
                .withId(id)
                .withSide(side);

        diffService.saveDiff(dto);
    }

    /**
     * Endpoint that returns the difference of the two sides
     *
     * @param id the diff identity
     * @return the difference info
     */
    @ApiOperation(value = "Provides the difference between two Base64 encoded JSON")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DiffResponseDTO getDiff(@PathVariable("id") final Long id) {
        return diffService.getDifference(id);
    }
}
