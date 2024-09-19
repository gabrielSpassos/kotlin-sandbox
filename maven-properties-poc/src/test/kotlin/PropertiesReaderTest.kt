import com.gabrielspassos.PropertiesReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PropertiesReaderTest {

    @Test
    fun shouldReadProperties() {
        val propertiesReader = PropertiesReader("properties-from-pom.properties")

        val javaVersion = propertiesReader.getProperty("java_version")
        val kotlinVersion = propertiesReader.getProperty("kotlin.version")
        val kotlinCodeStyle = propertiesReader.getProperty("kotlin.code.style")
        val mavenCompilerTarget = propertiesReader.getProperty("maven.compiler.target")

        assertEquals("21", javaVersion)
        assertEquals("2.0.20", kotlinVersion)
        assertEquals("21", mavenCompilerTarget)
        assertEquals("official", kotlinCodeStyle)
    }
}