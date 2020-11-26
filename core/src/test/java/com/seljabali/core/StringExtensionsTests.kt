package com.seljabali.core

import com.seljabali.core.utilities.*
import com.seljabali.core.utilities.areAllNotEmpty
import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionsTests {
    @Test
    fun `flatten 1 string`() {
        // given
        val delimiter = ", "
        val string1 = "hello"
        // when
        val expected = "hello"
        assertEquals(expected, String.getListFlattened(delimiter, string1))
    }

    @Test
    fun `flatten 2 strings into 1`() {
        // given
        val delimiter = ", "
        val string1 = "hello"
        val string2 = "world"
        // when
        val expected = "hello, world"
        assertEquals(expected, String.getListFlattened(delimiter, string1, string2))
    }

    @Test
    fun `areAllEmpty when they're not`() {
        // given
        val string1 = ""
        val string2 = "world"
        assertEquals(false, String.areAllEmpty(string1, string2))
    }

    @Test
    fun `areAllEmpty when they are`() {
        // given
        val string1 = ""
        val string2 = ""
        assertEquals(true, String.areAllEmpty(string1, string2))
    }

    @Test
    fun `areAllNotEmpty when they are`() {
        // given
        val string1 = "foo"
        val string2 = "bar"
        assertEquals(true, String.areAllNotEmpty(string1, string2))
    }

    @Test
    fun `areAnyEmpty when they are`() {
        // given
        val string1 = "foo"
        val string2 = ""
        assertEquals(true, String.areAnyEmpty(string1, string2))
    }

    @Test
    fun `areAnyEmpty when they're not`() {
        // given
        val string1 = "foo"
        val string2 = "bar"
        assertEquals(false, String.areAnyEmpty(string1, string2))
    }

    @Test
    fun `areAnyNotEmpty when there is one that is not empty`() {
        // given
        val string1 = "foo"
        val string2 = ""
        assertEquals(true, String.areAnyNotEmpty(string1, string2))
    }

    @Test
    fun `areAnyNotEmpty when there are none that are not empty`() {
        // given
        val string1 = ""
        val string2 = ""
        assertEquals(false, String.areAnyNotEmpty(string1, string2))
    }
}