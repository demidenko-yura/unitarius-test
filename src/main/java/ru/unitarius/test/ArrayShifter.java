package ru.unitarius.test;

public class ArrayShifter {

    private final int[] array;

    public ArrayShifter(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void shiftFrom(int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("Invalid index " + index);
        }
        if (index == 0 || index == array.length) {
            return;
        }
        --index;
        reverse(0, index);
        reverse(index + 1, array.length - 1);
        reverse(0, array.length - 1);
    }

    private void reverse(int start, int end) {
        int position = start + end;
        for (int i = start; i <= (position) / 2; i++) {
            int temp = this.array[position - i];
            this.array[position - i] = this.array[i];
            this.array[i] = temp;
        }
    }
}
