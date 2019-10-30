package com.waes.assignment.unit.domain.model;

import com.waes.assignment.diff.domain.model.ContentStatus;
import com.waes.assignment.diff.domain.model.Diff;
import com.waes.assignment.diff.domain.model.LeftSide;
import com.waes.assignment.diff.domain.model.RightSide;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
public class DiffTest {

    /*
     *  declaring these variables just to keep it visual
     *
     *  A and B have same size, but different content
     *  C has different size
     */
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
    public void should_return_status_EQUALS_when_contents_are_equal() {
        LeftSide leftSide = new LeftSide(encodedJsonA);
        RightSide rightSide = new RightSide(encodedJsonA);
        Diff diff = new Diff(1l);
        diff.setRight(rightSide);
        diff.setLeft(leftSide);

        ContentStatus contentStatus = diff.status();

        assertEquals(ContentStatus.EQUAL, contentStatus);
    }

    @Test
    public void should_return_status_DIFFERENT_SIZES_when_contents_have_different_sizes() {
        LeftSide leftSide = new LeftSide(encodedJsonA);
        RightSide rightSide = new RightSide(encodedJsonC);
        Diff diff = new Diff(1l);
        diff.setRight(rightSide);
        diff.setLeft(leftSide);

        ContentStatus contentStatus = diff.status();

        assertEquals(ContentStatus.DIFFERENT_SIZES, contentStatus);
    }

    @Test
    public void should_return_status_SAME_SIZE_DIFFERENT_CONTENT_when_contents_are_different_but_with_same_size() {
        LeftSide leftSide = new LeftSide(encodedJsonA);
        RightSide rightSide = new RightSide(encodedJsonB);
        Diff diff = new Diff(1l);
        diff.setRight(rightSide);
        diff.setLeft(leftSide);

        ContentStatus contentStatus = diff.status();

        assertEquals(ContentStatus.SAME_SIZE_DIFFERENT_CONTENT, contentStatus);
    }

    @Test
    public void should_not_throw_null_pointer_exception_when_get_status_and_one_or_both_sides_are_not_defined() {
        fail();
    }

    @Test
    public void should_return_positions_of_differences() {
        fail();
    }

    @Test
    public void should_validate_if_diff_has_both_sides() {
        fail();
    }
}
