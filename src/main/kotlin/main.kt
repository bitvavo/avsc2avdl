import com.bitvavo.tools.avsc2avdl.api.Avsc2AvdlFacade
import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty() || args.size > 2) {
        println("avsc2avdl INPUT [OUTPUT]")
        return
    }

    val output = Avsc2AvdlFacade.INSTANCE.convert(args[0])
    if (args.size == 2) {
        val file = File(args[1])
        file.writeText(output)
    } else {
        print(output)
    }
}
