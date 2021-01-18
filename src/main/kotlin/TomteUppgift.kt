/**
 * Created by Philip Zamayeri
 * Date: 2021-01-15
 * Time: 10:52
 * Project: InlämningTomte
 * Copyright: MIT
 */
/*
Inlämningsuppgift 2 - Rekursion
Tomtarna på Nordpolen har en strikt chefs-hierarki:
Högsta chefen för allt är "Tomten"
Under "Tomten" jobbar "Glader" och "Butter"
Under "Glader" jobbar "Tröger", "Trötter" och "Blyger"
Under "Butter" jobbar "Rådjuret", "Nyckelpigan", "Haren" och "Räven"
Under "Trötter" jobbar "Skumtomten"
Under "Skumtomten" jobbar "Dammråttan"
Under "Räven" jobbar "Gråsuggan" och "Myran"
Under "Myran" jobbar "Bladlusen"
Er uppgift har 2 delar:
• Illustrera tomtens chefshierarki i en lämplig datastruktur. Det finns flera tänkbara val här, ett
krav är att varje nisse bara får mappas mot dem som är direkt över- och underordnade i er
datastruktur. Direkta mappningar över flera nivåer är inte tillåtna.
• Skriv en rekursiv funktion där man antingen:
o Anger en nisses namn och får tillbaka en lista av alla denna nisses chefer, på samtliga
nivåer. Exempel: Om man skriver in ”Dammråttan” ska en lista innehållandes
Tomten, Glader, Trötter och Skumtomten returneras
o Anger en nisses namn och får tillbaka en lista på alla dess underordnade, på samtliga
nivåer. Exempel: Om man skriver in ”Räven” får man tillbaka en lista med
Gråsuggan, Myran och Bladlusen.
Inga tomtenamn får vara hårdkodade i den rekursiva loopen (det är ok att hårdkoda i skapandet av
datastrukturen). Du väljer själv om du skriver koden i Java eller Kotlin.
 */

var tree = mapOf("Glader" to "Tomten", "Butter" to "Tomten",
"Tröger" to "Glader", "Trötter" to "Glader", "Blyger" to "Glader",
"Rådjuret" to "Butter", "Nyckelpigan" to "Butter", "Haren" to "Butter", "Räven" to "Butter",
"Skumtomten" to "Trötter",
"Dammråttan" to "Skumtomten",
"Gråsuggan" to "Räven", "Myran" to "Räven",
"Bladlusen" to "Myran")


fun main() {
    while (true) {
        try {
            print("Enter a worker's name to find the worker's bosses: ")
            val name = readLine().toString()
            if (name.equals("Tomten", true)){
                println("Tomten is his own boss")
                println()
            }
            else if (name.equals("Break", true)){
                break
            }
            else if (!tree.contains((name.substring(0,1).toUpperCase().trim()
                        + name.substring(1).toLowerCase().trim()))){
                println("No such employee")
                println()
            }
            else {
                println("These are $name's bosses: " + getBosses(name))
                println()
            }
        } catch (e: NoSuchElementException) {
            e.printStackTrace()
            println("No such employee")
        } catch (e: Exception) {
            e.printStackTrace()
            println("Unknown error")
        }
    }
}

fun getBosses(input: String): MutableList<String> {
    fun bosses(employee: String, bossList: MutableList<String>): MutableList<String> {
        return try {
            val boss = tree.getValue(employee.substring(0,1).toUpperCase().trim()
                    + employee.substring(1).toLowerCase().trim())
            bossList.add(boss)
            bosses(boss, bossList)
        } catch (e: Exception) {
            bossList
        }
    }
    return bosses(input, mutableListOf())
}



