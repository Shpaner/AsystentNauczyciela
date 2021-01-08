package com.example.asystentnauczyciela.Model.Repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.Model.Dao.GradeDao
import com.example.asystentnauczyciela.Model.Entities.Grade

class GradeRepository(val gradeDao: GradeDao) {
    val readAll: LiveData<MutableList<Grade>> = gradeDao.getAllGrades()

    suspend fun add(grade: Grade) {
        gradeDao.insert(grade)
    }

    fun getStudentsGrades(studentId: Int, courseId: Int) : LiveData<MutableList<Grade>>
    {
        return gradeDao.getStudentsGrades(studentId, courseId)
    }

    fun getReport(todaysDate: String) : LiveData<MutableList<Grade>> {
        return gradeDao.report(todaysDate)
    }

    suspend fun delete(grade: Grade) = gradeDao.delete(grade)

    fun getGrade(index: Int) : Grade {
        return gradeDao.getGrade(index)
    }
}