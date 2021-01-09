package com.example.asystentnauczyciela.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.Model.Entities.Course
import com.example.asystentnauczyciela.Model.Entities.StudentsCourse
import com.example.asystentnauczyciela.Model.MainDatabase
import com.example.asystentnauczyciela.Model.Repositories.CourseRepository
import com.example.asystentnauczyciela.Model.Repositories.StudentsCourseRepository
import com.example.asystentnauczyciela.Model.ValuesHolder
import kotlinx.coroutines.launch

class StudentsCoursesViewModel (application: Application): AndroidViewModel(application) {

    val studentsCourseRepository: StudentsCourseRepository
    var studentsCourses: LiveData<MutableList<Course>>
    var notStudentsCourses: LiveData<MutableList<Course>>

    val courseRepository: CourseRepository

    init{
        studentsCourseRepository = StudentsCourseRepository(MainDatabase.getDatabase(application)
            .studentsCourseDao())
        studentsCourses = MainDatabase.getDatabase(application).courseDao()
            .getStudentsCourses(ValuesHolder.chosenStudentId)
        notStudentsCourses = MainDatabase.getDatabase(application).courseDao()
            .getNotStudentsCourses(ValuesHolder.chosenStudentId)

        courseRepository = CourseRepository(MainDatabase.getDatabase(application).courseDao())
    }

    fun addStudentsCourse(idStudent: Int, idCourse: Int ) {
        viewModelScope.launch {
            studentsCourseRepository.add(StudentsCourse(idStudent = idStudent, idCourse = idCourse, id = 0))
        }
    }

    fun deleteStudentsCourse(studentsCourse: StudentsCourse) {
        viewModelScope.launch {
            studentsCourseRepository.delete(studentsCourse)
        }
    }
}