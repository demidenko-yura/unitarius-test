package ru.unitarius.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayShifterTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6,7,8,9; 1,2,3,4,5,6,7,8,9; 0",
            "1,2,3,4,5,6,7,8,9; 2,3,4,5,6,7,8,9,1; 1",
            "1,2,3,4,5,6,7,8,9; 3,4,5,6,7,8,9,1,2; 2",
            "1,2,3,4,5,6,7,8,9; 4,5,6,7,8,9,1,2,3; 3",
            "1,2,3,4,5,6,7,8,9; 5,6,7,8,9,1,2,3,4; 4",
            "1,2,3,4,5,6,7,8,9; 6,7,8,9,1,2,3,4,5; 5",
            "1,2,3,4,5,6,7,8,9; 7,8,9,1,2,3,4,5,6; 6",
            "1,2,3,4,5,6,7,8,9; 8,9,1,2,3,4,5,6,7; 7",
            "1,2,3,4,5,6,7,8,9; 9,1,2,3,4,5,6,7,8; 8",
            "1,2,3,4,5,6,7,8,9; 1,2,3,4,5,6,7,8,9; 9"
    }, delimiter = ';')
    public void testShiftArray(String inputArrayString, String expectedArrayString, String moveIndex) {
        var inputArray = Arrays.stream(inputArrayString.trim().split(",")).mapToInt(Integer::parseInt).toArray();
        var expectedArray = Arrays.stream(expectedArrayString.trim().split(",")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(moveIndex.trim());

        var arrayShifter = new ArrayShifter(inputArray);
        arrayShifter.shiftFrom(m);

        assertArrayEquals(expectedArray, arrayShifter.getArray());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5,6,7,8,9; -1",
            "1,2,3,4,5,6,7,8,9; 10",
    }, delimiter = ';')
    public void testInvalidShiftArray(String inputArrayString, String moveIndex) {
        var inputArray = Arrays.stream(inputArrayString.trim().split(",")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(moveIndex.trim());

        var arrayShifter = new ArrayShifter(inputArray);

        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> arrayShifter.shiftFrom(m)
        );
        assertTrue(exception.getMessage().contains("Invalid index " + m));
    }
}
