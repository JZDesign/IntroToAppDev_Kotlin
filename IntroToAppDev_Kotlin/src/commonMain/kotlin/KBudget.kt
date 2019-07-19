import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
class KBudget(var budget: Array<KBudgetCategory>? = null) {
    fun jsonString() = Json.stringify(serializer(), this)

    companion object {
        fun decodeFrom(string: String) = KCoder().json.parse(serializer(), KCoder().validateJSON(string))
    }

    fun planned() = budget?.sumByDouble { it.plannedAmount } ?: 0.0
    fun spent() = budget?.sumByDouble { it.spent() } ?: 0.0
    fun remaining() = planned().minus(spent())
}

