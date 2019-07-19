import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
class KBudgetCategory(
    var name: String? = null,
    var plannedAmount: Double = 0.00,
    var transactions: Array<KTransaction>? = null
) {

    fun jsonString() = Json.stringify(serializer(), this)
    fun spent() = transactions?.sumByDouble { if (it.income) -it.amount else it.amount } ?: 0.0

    companion object {
        fun decodeFrom(string: String) = KCoder().json.parse(serializer(), KCoder().validateJSON(string))
    }
}
