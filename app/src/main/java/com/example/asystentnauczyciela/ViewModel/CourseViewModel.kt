package com.example.asystentnauczyciela.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.Model.Entities.Course
import com.example.asystentnauczyciela.Model.Entities.Student
import com.example.asystentnauczyciela.Model.MainDatabase
import com.example.asystentnauczyciela.Model.Repositories.CourseRepository
import com.example.asystentnauczyciela.Model.ValuesHolder
import kotlinx.coroutines.launch

class CourseViewModel(application: Application): AndroidViewModel(application) {

    val courses: LiveData<MutableList<Course>>
    val courseRepository: CourseRepository

    init{
        courses = MainDatabase.getDatabase(application).courseDao().getAllCourses()
        courseRepository = CourseRepository(MainDatabase.getDatabase(application).courseDao())

    }

    fun addCourse(name: String) {
        viewModelScope.launch {
            courseRepository.add(Course(name = name, id = 0))
        }
    }

    fun deleteCourse(course: Course) {
        viewModelScope.launch {
            courseRepository.delete(course)
        }
    }


}