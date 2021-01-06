package com.example.asystentnauczyciela.Model.Repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.Model.Dao.CourseDao
import com.example.asystentnauczyciela.Model.Entities.Course

class CourseRepository(val courseDao: CourseDao) {

    val readAll: LiveData<MutableList<Course>> = courseDao.getAllCourses()

    suspend fun add(course: Course) {
        courseDao.insert(course)
    }

//    fun getTeachersCourses(teacherId: Int) : LiveData<MutableList<Course>>
//    {
//        return courseDao.teachersCourses(teacherId)
//    }
//
//    fun getStudentsCourses(studentId: Int) : LiveData<MutableList<Course>>
//    {
//        return courseDao.studentsCourses(studentId)
//    }

    suspend fun delete(course: Course)=courseDao.delete(course)
}