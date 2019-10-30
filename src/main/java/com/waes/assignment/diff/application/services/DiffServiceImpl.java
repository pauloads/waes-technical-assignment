package com.waes.assignment.diff.application.services;

import com.waes.assignment.diff.application.dto.DiffDTO;
import com.waes.assignment.diff.application.dto.DiffResponseDTO;
import com.waes.assignment.diff.application.enums.Side;
import com.waes.assignment.diff.application.exception.DiffNotFoundException;
import com.waes.assignment.diff.application.exception.InvalidDiffException;
import com.waes.assignment.diff.domain.model.Diff;
import com.waes.assignment.diff.domain.model.LeftSide;
import com.waes.assignment.diff.domain.model.RightSide;
import com.waes.assignment.diff.domain.repository.DiffRepository;
import org.springframework.stereotype.Service;

@Service
public class DiffServiceImpl implements DiffService {

    private static final String DIFF_NOT_FOUNT = "Diff not fount";
    public static final String ONE_OR_BOTH_SIDES_ARE_EMPTY = "One or both sides are empty";

    private DiffRepository diffRepository;

    /**
     * Injecting in constructor to make explicit that this object
     * aways needs a DiffRepository (Good citizen pattern)
     *
     * @param diffRepository
     */
    public DiffServiceImpl(DiffRepository diffRepository) {
        this.diffRepository = diffRepository;
    }

    /**
     * Method responsible for persist Diff
     *
     * @param diffDTO
     */
    @Override
    public void saveDiff(DiffDTO diffDTO) {
        /*
         *   if Diff exists in the database, get from database
         */
        Diff diff = diffRepository.findById(diffDTO.getId())
                /*
                 *  else create a new instance
                 */
                .orElse(new Diff(diffDTO.getId()));

        /*
         *  verifying what side is being persisted and
         *  setting data according to the side
         */
        if (Side.LEFT.equals(diffDTO.getSide())) {
            diff.setLeft(new LeftSide(diffDTO.getBase64encodedJson()));
        } else if (Side.RIGHT.equals(diffDTO.getSide())) {
            diff.setRight(new RightSide(diffDTO.getBase64encodedJson()));
        }

        diffRepository.save(diff);
    }


    @Override
    public DiffResponseDTO getDifference(Long id) {

        Diff diff = diffRepository.findById(id)
                .orElseThrow(() -> new DiffNotFoundException(DIFF_NOT_FOUNT));

        if (!diff.hasBothSides()) {
            throw new InvalidDiffException(ONE_OR_BOTH_SIDES_ARE_EMPTY);
        }

        DiffResponseDTO responseDTO = new DiffResponseDTO();
        responseDTO.setOffsets(diff.positionsOfTheDifferences());
        responseDTO.setLenght(diff.getLeft().getEncodedValue().length());
        responseDTO.setStatus(diff.status());

        return responseDTO;
    }
}
