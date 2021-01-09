package com.example.asystentnauczyciela.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Database
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
    var gradeList: List<Grade>

    init{
        grades = MainDatabase.getDatabase(application).gradeDao().getAllGrades()
        gradeRepository = GradeRepository(MainDatabase.getDatabase(application).gradeDao())
        studentsGrades = MainDatabase.getDatabase(application).gradeDao().getStudentsGrades(ValuesHolder.chosenStudentId, ValuesHolder.chosenStudentsCourseId)
        reportGrades = MainDatabase.getDatabase(application).gradeDao().getAllGrades()

        gradeList = listOf(Grade(111, 999, 999, "tmp", "tmp", "tmp", "tmp", "tmp", "tmp"))
    }

    fun addGrade(studentId: Int, description: String, courseId: Int, grade: String, date: String, name: String, studentName: String, studentsCourseName: String) {
        viewModelScope.launch {
            gradeRepository.add(Grade(id = 0, idCourse = courseId, idStudent = studentId, gradeDescription = description,
                    gradeValue = grade, date = date, gradeName = name, studentName = studentName, studentsCourseName = studentsCourseName))
        }
    }

    fun deleteGrade(grade: Grade) {
        viewModelScope.launch {
            gradeRepository.delete(grade)
        }
    }

    fun getGrade(index: Int): Grade {

        viewModelScope.launch {
            gradeList = gradeRepository.getGradeList()
            Log.d("","scoped")
        }

        Log.d("index","$index")

        gradeList.forEach {

            Log.d("id", "${it.id}")

            if (it.id == index) {
                return it
            }
        }
        return Grade(999, 999, 999, "tmp", "tmp", "tmp", "tmp", "tmp", "tmp")
    }
}
