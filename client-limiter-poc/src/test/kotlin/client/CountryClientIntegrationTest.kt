package client

import com.gabrielspassos.factory.BeanFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.Locale

class CountryClientIntegrationTest {

    @Test
    fun `should get country by name`() {
        // Arrange
        val countryName = "brazil"
        val countryClient = BeanFactory.buildCountryClient()

        // Act
        val country = countryClient.getCountryByName(countryName)

        // Assert
        assertNotNull(country)
        assertEquals(1, country.size)
        assertEquals(countryName, country[0].name.common.lowercase(Locale.getDefault()))
    }
}