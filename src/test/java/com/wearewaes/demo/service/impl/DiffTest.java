package com.wearewaes.demo.service.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiffTest {

    @Test
    public void instance() {
        byte[] left = {1, 2};
        byte[] right = {2, 3};
        Diff diff = Diff.instance(left, right);
        assertNotNull(diff);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instanceException() {
        Diff diff = Diff.instance(null, null);
    }

    @Test
    public void getOffset() {
        byte[] left = {1, 3, 3, 6, 5};
        byte[] right = {1, 2, 3, 4, 5};
        Diff diff = Diff.instance(left, right);
        assertNotNull(diff);
        assertEquals(1, diff.getOffset());
    }

    @Test
    public void getOffsetSame() {
        byte[] left = {1, 3, 3, 6, 5};
        byte[] right = {1, 3, 3, 6, 5};
        Diff diff = Diff.instance(left, right);
        assertNotNull(diff);
        assertEquals(0, diff.getOffset());
    }

    @Test
    public void getDiffSize() {
        byte[] left = {1, 3, 3, 6, 5};
        byte[] right = {1, 2, 3, 4, 5};
        Diff diff = Diff.instance(left, right);
        assertNotNull(diff);
        assertEquals(2, diff.getDiffSize());
    }

    @Test
    public void getDiffSizeDifferentSize() {
        byte[] left = {1, 3, 3, 6, 5, 6, 7, 8, 9};
        byte[] right = {1, 2, 3, 4, 5};
        Diff diff = Diff.instance(left, right);
        assertNotNull(diff);
        assertEquals(0, diff.getDiffSize());
    }

    @Test
    public void isEqual() {
        byte[] left = {1, 2, 3, 4, 5};
        byte[] right = {1, 2, 3, 4, 5};
        Diff diff = Diff.instance(left, right);
        assertNotNull(diff);
        assertTrue(diff.isEqual());
    }

    @Test
    public void isSizeEqualSameInput() {
        byte[] left = {1, 2, 3, 4, 5};
        byte[] right = {1, 2, 3, 4, 5};
        Diff diff = Diff.instance(left, right);
        assertNotNull(diff);
        assertTrue(diff.isSizeEqual());
    }


    @Test
    public void isSizeEqualDiffInput() {
        byte[] left = {1, 3, 5, 7, 5};
        byte[] right = {1, 2, 3, 4, 5};
        Diff diff = Diff.instance(left, right);
        assertNotNull(diff);
        assertTrue(diff.isSizeEqual());
    }

    @Test
    public void isSizeEqualFalse() {
        byte[] left = {1, 3, 5, 7, 5, 8, 9};
        byte[] right = {1, 2, 3, 4, 5};
        Diff diff = Diff.instance(left, right);
        assertNotNull(diff);
        assertFalse(diff.isSizeEqual());
    }
}