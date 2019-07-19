import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class KCoder {
    val json = Json(JsonConfiguration(strictMode = false, indent = "    ", useArrayPolymorphism = true))

    fun validateJSON(json: String): String {
        var result = "{"
        if (json.first() != '{') result += json else result = json
        if (json.last() != '}') result = result + '}'
        return result.replace("""\""", "")
    }

}
