package com.gabrielspassos

class TextJustification {

    fun justify(words: List<String>, maxWidth: Int): List<String> {
        val lines = mutableListOf<String>()
        var line = ""
        var lineBuffer = 0

        for (word in words) {
            if (lineBuffer + word.length + 1 <= maxWidth) {
                val wordWithSpace = word.plus(" ")
                line += wordWithSpace
                lineBuffer += wordWithSpace.length
            } else {
                lines.add(line.trimEnd())
                line = ""
                lineBuffer = 0
                val wordWithSpace = word.plus(" ")
                line += wordWithSpace
                lineBuffer += wordWithSpace.length
            }
        }

        lines.add(line.trimEnd())

        return lines
    }

}