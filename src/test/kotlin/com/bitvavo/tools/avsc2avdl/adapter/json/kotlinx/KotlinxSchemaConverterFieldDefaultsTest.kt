package com.bitvavo.tools.avsc2avdl.adapter.json.kotlinx

import com.bitvavo.tools.avsc2avdl.domain.DefaultBoolean
import com.bitvavo.tools.avsc2avdl.domain.DefaultEmptyArray
import com.bitvavo.tools.avsc2avdl.domain.DefaultEmptyMap
import com.bitvavo.tools.avsc2avdl.domain.DefaultNull
import com.bitvavo.tools.avsc2avdl.domain.DefaultNumber
import com.bitvavo.tools.avsc2avdl.domain.DefaultString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class KotlinxSchemaReaderFieldDefaultsTest {

    private val converter = KotlinxSchemaReader()

    @Test
    fun `should read null default`() {
        // given
        val defaultString = "null"

        // when
        val schema = converter.read(someSchemaWithField(defaultString))

        // then
        assertThat(schema.fields.first().default).isEqualTo(DefaultNull)
    }

    @Test
    fun `should read string default`() {
        // given
        val defaultString = """ "someValue" """

        // when
        val schema = converter.read(someSchemaWithField(defaultString))

        // then
        assertThat(schema.fields.first().default).isEqualTo(DefaultString("someValue"))
    }

    @Test
    fun `should read number default`() {
        // given
        val defaultString = "123"

        // when
        val schema = converter.read(someSchemaWithField(defaultString))

        // then
        assertThat((schema.fields.first().default as DefaultNumber).value.toLong()).isEqualTo(123L)
    }

    @Test
    fun `should read boolean default`() {
        // given
        val defaultString = "true"

        // when
        val schema = converter.read(someSchemaWithField(defaultString))

        // then
        assertThat(schema.fields.first().default).isEqualTo(DefaultBoolean(true))
    }

    @Test
    fun `should read array default`() {
        // given
        val defaultString = "[]"

        // when
        val schema = converter.read(someSchemaWithField(defaultString))

        // then
        assertThat(schema.fields.first().default).isEqualTo(DefaultEmptyArray)
    }

    @Test
    fun `should read map default`() {
        // given
        val defaultString = "{}"

        // when
        val schema = converter.read(someSchemaWithField(defaultString))

        // then
        assertThat(schema.fields.first().default).isEqualTo(DefaultEmptyMap)
    }


    private fun someSchemaWithField(defaultString: String): String =
        """
             {
                "type": "record",
                "name": "ObjectName",
                "namespace": "this.is.the.namespace",
                "doc": "Super object for test purposes",
                "fields": [
                  {
                      "name": "someStringField",
                      "type": "string",
                      "default": $defaultString
                  }
                ]
            }
        """.trimIndent()
}
