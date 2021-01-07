package com.example.asystentnauczyciela.NIEUZYWANE

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.Model.Entities.Course
import com.example.asystentnauczyciela.Model.Entities.StudentsCourse
import com.example.asystentnauczyciela.Model.MainDatabase
import com.example.asystentnauczyciela.Model.Repositories.CourseRepository
import com.example.asystentnauczyciela.Model.Repositories.StudentRepository
import com.example.asystentnauczyciela.Model.Repositories.StudentsCourseRepository
import com.example.asystentnauczyciela.Model.ValuesHolder
import kotlinx.coroutines.launch

//class AvailableCoursesViewModel(application: Application): AndroidViewModel(application) {
//
//    var studentsCourses: LiveData<MutableList<Course>>
//    var notStudentsCourses: LiveData<MutableList<Course>>
//    val courseRepository: CourseRepository
//    private val studentsCourseRepository: StudentsCourseRepository
//
//    init {
//        studentsCourses = MainDatabase.getDatabase(application).courseDao()
//            .getAllCourses(ValuesHolder.chosenCourseId)
//        notStudentsCourses = MainDatabase.getDatabase(application).studentDao()
//            .getNotParticipants(ValuesHolder.chosenCourseId)
//        studentRepository = StudentRepository(MainDatabase.getDatabase(application).studentDao())
//        studentsCourseRepository = StudentsCourseRepository(MainDatabase.getDatabase(application).studentsCourseDao())
//    }
//
//    fun addStudentsCourse(studentId: Int, courseId: Int)
//    {
//        viewModelScope.launch {
//            studentsCourseRepository.add(StudentsCourse(id = 0, idStudent = studentId, idCourse = courseId))
//        }
//    }
//
//    fun deleteParticipant(studentsCourse: StudentsCourse){
//        viewModelScope.launch {
//            studentsCourseRepository.delete(studentsCourse)
//        }
//    }
//}