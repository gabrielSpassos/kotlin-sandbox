package com.gabrielspassos

class TextJustification {

    fun justify(words: List<String>, maxWidth: Int): List<String> {
        val lines = mutableListOf<String>()
        val line = mutableListOf<String>()
        var lineBuffer = 0

        for (word in words) {
            if (lineBuffer + word.length + 1 > maxWidth) {
                val separationBetweenWords = if (1 == line.size) { 1 } else { line.size - 1 }
                val missingSpaces = (maxWidth - sumCharsOfList(line))
                val evenSpaces = missingSpaces / separationBetweenWords
                // issue is with the even spaces
                val spaces = createSpaces(evenSpaces)
                println("Missing spaces $missingSpaces even $evenSpaces: $spaces")
                lines.add(line.joinToString(spaces).trimEnd())
                line.clear()
                lineBuffer = 0
                line.add(word)
                lineBuffer += (word.length)
            } else {
                line.add(word)
                lineBuffer += (word.length + 1)
            }
        }

        lines.add(line.joinToString(" ").trimEnd())

        return lines
    }

    fun sumCharsOfList(words: List<String>): Int {
        return words.joinToString(separator = "").length
    }

    private fun createSpaces(times: Int): String {
        var spaces = ""
        for (i in 1..times) {
            spaces += "*"
        }
        return spaces
    }

}