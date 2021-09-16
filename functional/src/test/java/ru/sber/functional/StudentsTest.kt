package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StudentsTest{

    @Test
    fun `filterByPredicate must return filtered list of Student`() {
        val studentsGroup = StudentsGroup()
        studentsGroup.initializeStudents(
                listOf(
                        Student(firstName = "Kirill", lastName = "Vasilev", averageRate = 4.0),
                        Student(firstName = "Vladislav", lastName = "Yakupov", averageRate = 4.25),
                        Student(firstName = "Aleksandr", lastName = "Sisoy", averageRate = 3.89),
                        Student(firstName = "Dmitriy", lastName = "Zhigalkin", averageRate = 4.22),
                        Student(firstName = "Aleksey", lastName = "Knyazhev", averageRate = 3.72),
                        Student(firstName = "Aleksey", lastName = "Titorenko", averageRate = 4.5)
                )
        )

        fun predicate1(student: Student) = student.firstName == "Aleksey"
        fun predicate2(student: Student) = student.averageRate > 4.0
        fun predicate3(student: Student) = student.middleName === "No middleName"

        assertEquals(2, studentsGroup.filterByPredicate(::predicate1).size)
        assertEquals(3, studentsGroup.filterByPredicate(::predicate2).size)
        assertEquals(studentsGroup.students.size, studentsGroup.filterByPredicate(::predicate3).size)
    }
}