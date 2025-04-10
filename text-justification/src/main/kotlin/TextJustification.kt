package com.gabrielspassos

class TextJustification {

    fun justify(words: List<String>, maxWidth: Int): List<String> {
        val lines = mutableListOf<String>()
        var line = ""
        var lineBuffer = 0

        for (word in words) {
            if (word.length + lineBuffer + 1 <= maxWidth) {
                val wordWithSpace = word.plus(" ")
                line += wordWithSpace
                lineBuffer += wordWithSpace.length
            } else {
                lines.add(line.trimEnd())
                line = ""
                val wordWithSpace = word.plus(" ")
                line += wordWithSpace
                lineBuffer = 0
                lineBuffer += wordWithSpace.length
            }
        }

        lines.add(line.trimEnd())

        return lines
    }

}