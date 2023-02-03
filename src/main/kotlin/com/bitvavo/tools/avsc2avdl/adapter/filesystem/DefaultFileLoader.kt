package com.bitvavo.tools.avsc2avdl.adapter.filesystem

import com.bitvavo.tools.avsc2avdl.domain.FileLoader
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

class DefaultFileLoader : FileLoader {
    override fun loadFile(fileName: String): String {
        val stringBuilder = StringBuilder()
        Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)
            .forEach { stringBuilder.append(it) }

        return stringBuilder.toString()
    }
}
