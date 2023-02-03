package com.bitvavo.tools.avsc2avdl.domain

interface SchemaReader {
    fun read(jsonString: String): Schema
}
