package com.waes.assignment.diff.application.services;

import com.waes.assignment.diff.application.dto.DiffDTO;
import com.waes.assignment.diff.application.dto.DiffResponseDTO;

public interface DiffService {

    void saveDiff(DiffDTO diffDTO);

    DiffResponseDTO getDifference(final Long id);
}