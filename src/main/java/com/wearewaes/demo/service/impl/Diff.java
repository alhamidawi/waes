package com.wearewaes.demo.service.impl;

/**
 * Helper class which is used to calculate difference between two arrays.
 */
class Diff {

    private final byte[] left;
    private final byte[] right;
    private int offset;
    private int diffSize;
    private boolean isEqual = true;
    private boolean isSizeEqual;


    private Diff(byte[] left, byte[] right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Provide prover byte array");
        }
        this.left = left;
        this.right = right;
        calculate();
    }

    static Diff instance(byte[] left, byte[] right) {
        return new Diff(left, right);
    }


    int getOffset() {
        return offset;
    }

    int getDiffSize() {
        return diffSize;
    }

    boolean isEqual() {
        return isEqual;
    }

    boolean isSizeEqual() {
        return isSizeEqual;
    }

    private void calculate() {
        //check the size of arrays
        if (left.length == right.length) {
            //their size is the same
            isSizeEqual = true;
            boolean offsetFound = false;
            //iterate to compare content
            for (int i = 0; i < left.length; i++) {
                if (left[i] != right[i]) {
                    //content is not the same, we need to find offset and diff length
                    diffSize++;
                    isEqual = false;
                    if (!offsetFound) {
                        offset = i;
                        offsetFound = true;
                    }
                }
            }
        } else {
            isEqual = false;
        }
    }
}
