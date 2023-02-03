package com.bitvavo.tools.avsc2avdl.domain

interface FileLoader {
    fun loadFile(fileName: String): String
}
