package com.gabrielspassos

class Solution {

    fun hello(): String {
        return "hello"
    }

    fun calculateWaterArea(heights: List<Int>): Int {
        var waterArea = 0
        var left = 0
        var right = heights.size - 1
        var leftDepth = heights[left]
        var rightDepth = heights[right]

        while (left < right) {
            if (leftDepth < rightDepth) {
                left++
                leftDepth = Math.max(leftDepth, heights[left])
                waterArea = waterArea + leftDepth - heights[left]
            } else {
                right --
                rightDepth = Math.max(rightDepth, heights[right])
                waterArea = waterArea + rightDepth - heights[right]
            }
        }

        return waterArea
    }

    fun rankBots(botsHeights: List<List<Int>>): Map<String, Int> {
        val map = mutableMapOf<String, Int>()
        for (botHeights in botsHeights){
            val waterArea = calculateWaterArea(botHeights)
            map[botHeights.toString()] = waterArea
        }

        return map.toList()
            .sortedByDescending { (_, value) -> value }
            .take(3)
            .toMap()
    }

}