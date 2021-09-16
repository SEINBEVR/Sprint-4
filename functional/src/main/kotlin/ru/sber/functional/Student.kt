package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "No middleName",
    val age: Int = 0,
    val averageRate: Double,
    val city: String = "No city",
    val specialization: String = "No specialization",
    val prevEducation: String? = null,
)
