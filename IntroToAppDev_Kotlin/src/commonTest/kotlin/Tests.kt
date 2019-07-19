import kotlin.test.*

class Tests {
    @Test fun `budget serializes from string`() {
        val kbudget = KBudget.decodeFrom(budget)
        assertEquals(kbudget.budget?.count(), 2)
        assertTrue { kbudget.budget?.filter { it.name == "bc2" }?.firstOrNull()?.plannedAmount == 100.00 }
    }

    @Test fun `category serializes from string`() {
        val cat = KBudgetCategory.decodeFrom(bc2)
        assertEquals(cat.name, "bc2")
        assertEquals(cat.plannedAmount, 100.00)
    }

    @Test fun `transaction serializes from string`() {
        val tran = KTransaction.decodeFrom(transaction2)
        assertEquals(tran.amount, 10.01)
    }

    @Test fun `category spent calculates properly with income`() {
        val cat = KBudgetCategory.decodeFrom(bc2)
        assertEquals(cat.spent(), -9.01)
    }

    @Test fun `category spent calculates without income`() {
        val cat = KBudgetCategory.decodeFrom(budgetCategory)
        assertEquals(cat.spent(), 2.00)
    }

    @Test fun `budget planned calculates properly`() {
        val kbudget = KBudget.decodeFrom(budget)
        assertEquals(kbudget.planned(), 110.00)
    }

    @Test fun `budget spent calculates properly`() {
        val kbudget = KBudget.decodeFrom(budget)
        assertEquals(kbudget.spent(), -7.01)
    }

    @Test fun `budget remaining calculates properly`() {
        val kbudget = KBudget.decodeFrom(budget)
        assertEquals(kbudget.remaining(), 117.01)
    }

    @Test fun `Coder toString works`() {
        val kbudget = KBudget.decodeFrom(budget)
        assertTrue(kbudget.jsonString().contains("plannedAmount"))
        assertTrue(kbudget.jsonString().contains("date"))
    }
}


val transaction = """{ "id": "1", "amount": 1.00 }"""
val transaction2 = """{ "id": "12", "amount": 10.01 "income": true }"""
val transaction3 = """{ "id": "1", "amount": 1.00 }"""
val budgetCategory = """{ "name": "Name", "plannedAmount": 10.00, "transactions": [$transaction, $transaction3] }"""
val bc2 = """{ "name": "bc2", "plannedAmount": 100.00, "transactions": [$transaction, $transaction2] }"""
val budget = """{ "budget": [$budgetCategory, $bc2] }"""
