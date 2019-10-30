package com.waes.assignment.unit.application.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DiffServiceTest {

    private static final String LEFT = "{\"band\":{\"name\":\"Pink Floyd\",\"members\":[{\"name\":\"David Gilmour\"},{\"name\":\"Syd Barret\"},{\"name\":\"Roger Waters\"},{\"name\":\"Richard Wright\"},{\"name\":\"Nick Mason\"}]}}";

    private static final String RIGHT = "{\"band\":{\"name\":\"Pink Floyd\",\"members\":[{\"name\":\"David Gilmaur\"},{\"name\":\"Sid Barret\"},{\"name\":\"Roger Waters\"},{\"name\":\"Richard Wright\"},{\"name\":\"Nick Mason\"}]}}";

    @Test
    public void name() {

        System.out.println(buildDiffDetails(LEFT.getBytes(), RIGHT.getBytes()));
    }

    /**
     *
     * @param left
     * @param right
     * @return Offsets: 59 75 - [ Content length: 161 ]
     */
    private String buildDiffDetails(byte[] left, byte[] right) {

        StringBuilder sb = new StringBuilder("Offsets: ");

        for (int i = 0; i < right.length; i++) {
            if (left[i] != right[i]) {
                sb.append(String.format("%d ", i));
            }
        }

        sb.append(String.format("- [ Content length: %d ]", right.length));

        return sb.toString();
    }

}
