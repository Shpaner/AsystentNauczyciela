package com.example.asystentnauczyciela.Model.Repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.Model.Dao.StudentsCourseDao
import com.example.asystentnauczyciela.Model.Entities.StudentsCourse

class StudentsCourseRepository (val studentsCourseDao: StudentsCourseDao) {
    val readAll: LiveData<MutableList<StudentsCourse>> = studentsCourseDao.getAllStudentsCourses()

    suspend fun add(studentsCourse: StudentsCourse) {
        studentsCourseDao.insert(studentsCourse)
    }

    suspend fun delete(studentsCourse: StudentsCourse)=studentsCourseDao.delete(studentsCourse)
}