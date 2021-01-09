package com.example.asystentnauczyciela.Model.Repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.Model.Entities.Student
import com.example.asystentnauczyciela.Model.Dao.StudentDao

class StudentRepository(private val studentDao: StudentDao) {
    val readAll: LiveData<MutableList<Student>> = studentDao.getAllStudents()

    suspend fun add(student: Student) {
        studentDao.insert(student)
    }

    suspend fun delete(student: Student) = studentDao.delete(student)

    fun editStudent(newName: String, newLastName: String, idStudent: Int)=studentDao.editStudent(newName, newLastName, idStudent)
}