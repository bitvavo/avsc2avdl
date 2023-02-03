package com.bitvavo.tools.avsc2avdl.api

import com.bitvavo.tools.avsc2avdl.adapter.filesystem.DefaultFileLoader
import com.bitvavo.tools.avsc2avdl.adapter.json.kotlinx.KotlinxSchemaReader
import com.bitvavo.tools.avsc2avdl.domain.FileLoader
import com.bitvavo.tools.avsc2avdl.domain.SchemaConverter
import com.bitvavo.tools.avsc2avdl.domain.SchemaPrinter

class Avsc2AvdlFacade(
    private val fileLoader: FileLoader,
    private val schemaConverter: SchemaConverter
) {
    fun convert(avscFileName: String): String =
        schemaConverter.convert(jsonSchema = fileLoader.loadFile(avscFileName))

    companion object {
        val INSTANCE = Avsc2AvdlFacade(
            DefaultFileLoader(),
            SchemaConverter(KotlinxSchemaReader(), SchemaPrinter())
        )
    }
}
