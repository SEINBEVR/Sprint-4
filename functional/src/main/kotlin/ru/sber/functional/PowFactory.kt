package ru.sber.functional

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */
    fun buildPowFunction(pow: Int) : (Any) -> Any {
        return { x : Any ->
            if(pow == 0) {
                when(x) {
                    is String, is Char -> x
                    is Double, is Int -> 1.0
                    else -> 1.0
                }
            } else if(x is String || x is Char) {
                var answer = ""
                repeat(pow) {
                    answer += x
                }
                answer
            } else {
                var answer = 1.0
                repeat(pow) {
                    answer *= (x).toString().toDouble()
                }
                answer
            }
        }
    }
}
