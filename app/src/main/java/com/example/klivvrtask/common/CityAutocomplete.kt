package com.example.klivvrtask.common

import com.example.klivvrtask.domain.model.City
import java.util.Locale

class CityAutocomplete {
    private val rootNode = TrieNode()

    fun addCity(city: City) {
        var currentNode = rootNode
        for (char in city.name.toLowerCase(Locale.ROOT)) {
            currentNode = currentNode.children.computeIfAbsent(char) { TrieNode() }
        }
        currentNode.cityList.add(city)
    }

    fun findCitiesByPrefix(prefix: String): List<City> {
        var currentNode = rootNode

        for (char in prefix.toLowerCase(Locale.ROOT)) {
            currentNode = currentNode.children[char] ?: return emptyList()
        }
        return currentNode.cityList
    }

    private class TrieNode {
        val children: MutableMap<Char, TrieNode> = mutableMapOf()
        val cityList: MutableList<City> = mutableListOf()
    }
}