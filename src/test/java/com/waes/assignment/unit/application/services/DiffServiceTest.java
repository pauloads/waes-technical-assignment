package com.waes.assignment.unit.application.services;

import com.waes.assignment.diff.application.dto.DiffDTO;
import com.waes.assignment.diff.application.dto.DiffResponseDTO;
import com.waes.assignment.diff.application.enums.Side;
import com.waes.assignment.diff.application.exception.DiffNotFoundException;
import com.waes.assignment.diff.application.exception.InvalidDiffException;
import com.waes.assignment.diff.application.services.DiffServiceImpl;
import com.waes.assignment.diff.domain.model.Diff;
import com.waes.assignment.diff.domain.model.LeftSide;
import com.waes.assignment.diff.domain.model.RightSide;
import com.waes.assignment.diff.domain.repository.DiffRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DiffServiceTest {

    @Mock
    private DiffRepository diffRepository;

    @InjectMocks
    private DiffServiceImpl diffService;

    private final String jsonA = "{\"band\":{\"name\":\"Pink Floyd\",\"members\":[{\"name\":\"David Gilmour\"},{\"name\":\"Syd Barret\"},{\"name\":\"Roger Waters\"},{\"name\":\"Richard Wright\"},{\"name\":\"Nick Mason\"}]}}";
    private final String jsonB = "{\"band\":{\"name\":\"Pink Floid\",\"members\":[{\"name\":\"Davyd Gylmour\"},{\"name\":\"Syd Barret\"},{\"name\":\"Roger Weterz\"},{\"name\":\"Richard Wright\"},{\"name\":\"Nick Mazon\"}]}}";
    private final String jsonC = "{\"band\":{\"name\":\"Ramones\",\"members\":[{\"name\":\"Tommy Ramone\"},{\"name\":\"Joey Ramone\"},{\"name\":\"Johnny Ramone\"},{\"name\":\"Dee Dee Ramone\"}]}}";

    private String encodedJsonA;

    private String encodedJsonB;

    private String encodedJsonC;

    @Before
    public void setUp() throws Exception {
        encodedJsonA = Base64.getEncoder().encodeToString(jsonA.getBytes());
        encodedJsonB = Base64.getEncoder().encodeToString(jsonB.getBytes());
        encodedJsonC = Base64.getEncoder().encodeToString(jsonC.getBytes());
    }


    @Test
    public void should_save_diff() {

        DiffDTO diffDTO = new DiffDTO().withSide(Side.LEFT.getValue());
        when(diffRepository.findById(anyLong())).thenReturn(Optional.empty());

        diffService.saveDiff(diffDTO);

        verify(diffRepository, times(1)).save(any(Diff.class));
    }

    @Test(expected = DiffNotFoundException.class)
    public void should_throw_exception_when_diff_does_not_exist() {
        when(diffRepository.findById(anyLong())).thenReturn(Optional.empty());

        diffService.getDifference(1l);
    }

    @Test(expected = InvalidDiffException.class)
    public void should_throw_exception_when_diff_does_not_have_two_sides() {
        Diff diff = new Diff(1l);
        when(diffRepository.findById(anyLong())).thenReturn(Optional.of(diff));

        diffService.getDifference(1l);
    }

    @Test
    public void should_return_only_status_when_contents_are_equals() {
        Diff diff = new Diff(1l);
        LeftSide leftSide = new LeftSide(encodedJsonA);
        RightSide rightSide = new RightSide(encodedJsonA);
        diff.setLeft(leftSide);
        diff.setRight(rightSide);

        when(diffRepository.findById(anyLong())).thenReturn(Optional.of(diff));
        DiffResponseDTO response = diffService.getDifference(1l);

        assertNotNull(response.getStatus());
        assertNull(response.getOffsets());
        assertNull(response.getLenght());
    }

    @Test
    public void should_return_only_status_when_contents_are_of_different_sizes() {
        Diff diff = new Diff(1l);
        LeftSide leftSide = new LeftSide(encodedJsonA);
        RightSide rightSide = new RightSide(encodedJsonC);
        diff.setLeft(leftSide);
        diff.setRight(rightSide);

        when(diffRepository.findById(anyLong())).thenReturn(Optional.of(diff));
        DiffResponseDTO response = diffService.getDifference(1l);

        assertNotNull(response.getStatus());
        assertNull(response.getOffsets());
        assertNull(response.getLenght());
    }

    @Test
    public void should_return_status_offsets_and_content_size_when_contents_are_of_equal_sizes_and_different_contents() {
        Diff diff = new Diff(1l);
        LeftSide leftSide = new LeftSide(encodedJsonA);
        RightSide rightSide = new RightSide(encodedJsonB);
        diff.setLeft(leftSide);
        diff.setRight(rightSide);

        when(diffRepository.findById(anyLong())).thenReturn(Optional.of(diff));
        DiffResponseDTO response = diffService.getDifference(1l);

        assertNotNull(response.getStatus());
        assertNotNull(response.getOffsets());
        assertNotNull(response.getLenght());
    }
}
