package service

import com.gabrielspassos.dao.BankDAO
import com.gabrielspassos.model.BankEntity
import com.gabrielspassos.service.BankService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class BankServiceTest {

    @Mock
    private lateinit var bankDAO: BankDAO

    @Test
    fun `should find bank by id`() {
        // given
        val bankService = BankService(bankDAO)
        val bankEntity = BankEntity(1L, "Banco do Brasil", "001")

        given(bankDAO.findById(1L)).willReturn(Optional.of(bankEntity))

        // when
        val result = bankService.findById(1L)

        // then
        val bank = result.get()
        assertEquals(1L, bank.id)
    }

}