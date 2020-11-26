package com.seljabali.core.time

import com.seljabali.core.utilities.time.parseLocalDate
import org.junit.Assert
import org.junit.Test

class LocalDateTimeExtensionsTests {
    @Test
    fun `parse a YYYY-MM-DD`() {
        // given
        val dateText = "2020-01-01"
        // when
        val dateParsed = dateText.parseLocalDate()
        Assert.assertNotNull(dateParsed)
    }

}