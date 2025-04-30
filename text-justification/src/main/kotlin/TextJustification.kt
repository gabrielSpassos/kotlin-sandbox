package com.gabrielspassos

class TextJustification {

    fun justify(words: List<String>, maxWidth: Int): List<String> {
        val lines = mutableListOf<String>()
        val line = mutableListOf<String>()
        var lineBuffer = 0

        for (word in words) {
            if (lineBuffer + word.length + 1 > maxWidth) {
                val wordsInLine = line.size
                val missingSpaces = (maxWidth - lineBuffer) % wordsInLine
                val spaces = createSpaces(missingSpaces)
                println("Missing spaces $missingSpaces: $spaces")
                lines.add(line.joinToString(spaces).trimEnd())
                line.clear()
                lineBuffer = 0
                line.add(word)
                lineBuffer += (word.length + 1)
            } else {
                line.add(word)
                lineBuffer += (word.length + 1)
            }
        }

        lines.add(line.joinToString(" ").trimEnd())

        return lines
    }

    private fun createSpaces(times: Int): String {
        var spaces = ""
        for (i in 1..times) {
            spaces += "*"
        }
        return spaces
    }

}