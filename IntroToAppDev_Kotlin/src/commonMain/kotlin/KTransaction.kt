import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
class KTransaction
    (
    var id: String? = null,
    var merchantName: String = "",
    var amount: Double = 0.00,
    var income: Boolean = false,
    var date: String? = null
) {

    fun jsonString() = Json.stringify(serializer(), this)

    companion object {
        fun decodeFrom(string: String) = KCoder().json.parse(serializer(), KCoder().validateJSON(string))
    }
}