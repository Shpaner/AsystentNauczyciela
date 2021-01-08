package com.example.asystentnauczyciela.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.Model.Entities.Grade
import com.example.asystentnauczyciela.Model.MainDatabase
import com.example.asystentnauczyciela.Model.Repositories.GradeRepository
import com.example.asystentnauczyciela.Model.ValuesHolder
import kotlinx.coroutines.launch

class GradeViewModel(application: Application): AndroidViewModel(application) {

    val grades: LiveData<MutableList<Grade>>
    val gradeRepository: GradeRepository
    var studentsGrades: LiveData<MutableList<Grade>>
    var reportGrades: LiveData<MutableList<Grade>>
    //var particularGrade: Grade

    init{
        grades = MainDatabase.getDatabase(application).gradeDao().getAllGrades()
        gradeRepository = GradeRepository(MainDatabase.getDatabase(application).gradeDao())
        studentsGrades = MainDatabase.getDatabase(application).gradeDao().getStudentsGrades(ValuesHolder.chosenStudentId, ValuesHolder.chosenStudentsCourseId)
        reportGrades = MainDatabase.getDatabase(application).gradeDao().getAllGrades()
        //particularGrade = MainDatabase.getDatabase(application).gradeDao().getGrade(ValuesHolder.chosenGradeIndex)
    }

    fun addGrade(studentId: Int, description: String, courseId: Int, grade: String, date: String, name: String)
    {
        viewModelScope.launch {
            gradeRepository.add(Grade(id = 0, idCourse = courseId, idStudent = studentId, gradeDescription = description, gradeValue = grade, date = date, gradeName = name))
        }
    }

    fun deleteGrade(grade: Grade)
    {
        viewModelScope.launch {
            gradeRepository.delete(grade)
        }
    }
}