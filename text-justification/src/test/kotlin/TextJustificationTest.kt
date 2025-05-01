import com.gabrielspassos.TextJustification
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TextJustificationTest {

    @Test
    fun example1() {
        val textJustification = TextJustification()
        val words = listOf("This", "is", "an", "example", "of", "text", "justification.")
        val maxWidth = 16
        val expected = listOf(
            "This    is    an",
            "example  of text",
            "justification.  "
        )

        val result = textJustification.justify(words, maxWidth)

        assertEquals(expected, result)
    }

    @Test
    fun example2() {
        val textJustification = TextJustification()
        val words = listOf("What", "must", "be", "acknowledgment", "shall", "be")
        val maxWidth = 16
        val expected = listOf(
            "What   must   be",
            "acknowledgment  ",
            "shall be        "
        )

        val result = textJustification.justify(words, maxWidth)

        assertEquals(expected, result)
    }

    @Test
    fun example3() {
        val textJustification = TextJustification()

        val words = listOf("Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a",
            "computer.", "Art", "is", "everything", "else", "we", "do")
        val maxWidth = 20
        val expected = listOf(
            "Science  is  what we",
            "understand      well",
            "enough to explain to",
            "a  computer.  Art is",
            "everything  else  we",
            "do                  "
        )

        val result = textJustification.justify(words, maxWidth)

        assertEquals(expected, result)
    }

    @Test
    fun shouldReturn8() {
        val words = listOf("This", "is", "an")
        val textJustification = TextJustification()

        val result = textJustification.sumCharsOfList(words)

        assertEquals(8, result)
    }

    @Test
    fun shouldReturn15() {
        val words = listOf("Science", "is", "what", "we")
        val textJustification = TextJustification()

        val result = textJustification.sumCharsOfList(words)

        assertEquals(15, result)
    }
}