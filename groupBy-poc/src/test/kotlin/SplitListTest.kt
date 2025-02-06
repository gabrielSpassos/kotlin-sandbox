import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SplitListTest {

    @Test
    fun shouldSplitList() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val chunks = list.chunked(4)
        chunks.forEach(::println)

        assertEquals(3, chunks.size)
        assertEquals(listOf(1, 2, 3, 4), chunks[0])
        assertEquals(listOf(5, 6, 7, 8), chunks[1])
        assertEquals(listOf(9, 10), chunks[2])
    }
}