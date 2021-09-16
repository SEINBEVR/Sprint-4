package ru.sber.generics


// 1.
fun <T1, T2> compare(p1: Pair<T1, T2>, p2: Pair<T1, T2>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    var counter = 0
    anArray.forEach {
        if(elem < it) counter++
    }
    return counter
}

// 3.
class Sorter <T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        for(i in list.lastIndex downTo 1) {
            val current = list[i]
            val previous = list[i-1]
            if(current < previous) {
                list[i] = previous
                list[i-1] = current
            } else break
        }
    }
}

// 4.
class Stack <T> {
    private val stack = mutableListOf<T>()

    fun push(value : T) = stack.add(value)

    fun pop() : T? {
        val item = stack.lastOrNull()
        if(!isEmpty())
            stack.removeLast()
        return item
    }

    fun isEmpty() = stack.isEmpty()
}