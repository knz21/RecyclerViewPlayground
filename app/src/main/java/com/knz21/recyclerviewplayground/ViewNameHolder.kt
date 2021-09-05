package com.knz21.recyclerviewplayground

object ViewNameHolder {

    val names: MutableMap<Int, String> = mutableMapOf()

    fun getName(code: Int): String {
        val name = names[code]
        if (name != null) {
            return name
        }
        val newName = "View: ${names.size}"
        names[code] = newName
        return newName
    }
}