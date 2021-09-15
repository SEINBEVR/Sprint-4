import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_data_with_bad_phone_length.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH)
    }

    @Test
    fun `fail save client - email validation error`() {
        val client = getClientFromJson("/fail/user_data_with_bad_email.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER)
    }

    @Test
    fun `fail save client - name validation error`() {
        val client = getClientFromJson("/fail/user_data_with_bad_name.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_CHARACTER)
    }

    @Test
    fun `fail save client - snils number validation error`() {
        val client = getClientFromJson("/fail/user_data_with_bad_snils_number.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS_NUMBER)
    }

    @Test
    fun `fail save client - snils length validation error`() {
        val client = getClientFromJson("/fail/user_data_with_bad_snils_length.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH)
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH)
        assertEquals(exception.errorCode[1], ErrorCode.CANNOT_BE_EMPTY)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_LENGTH)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_CHARACTER)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_SNILS_NUMBER)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}