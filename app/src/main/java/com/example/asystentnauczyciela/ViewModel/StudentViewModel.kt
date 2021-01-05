package com.example.asystentnauczyciela.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.Model.MainDatabase
import com.example.asystentnauczyciela.Model.Repositories.StudentRepository
import com.example.asystentnauczyciela.Model.Student
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application) {

    val students: LiveData<MutableList<Student>>
    private val studentRepository: StudentRepository

    init{
        students = MainDatabase.getDatabase(application).studentDao().getAllStudents()
        studentRepository = StudentRepository(MainDatabase.getDatabase(application).studentDao())
    }

    fun addStudent(name: String, lastName: String)
    {
        viewModelScope.launch {
            studentRepository.add(Student(name=name, lastName = lastName, id=0))
        }
    }

    fun deleteStudent(student: Student){
        viewModelScope.launch {
            studentRepository.delete(student)
        }
    }

    fun editStudent(newName: String, newLastName: String, idStudent: Int) {
        viewModelScope.launch {
            studentRepository.editStudent(newName = newName,newLastName = newLastName,idStudent = idStudent)
        }
    }
}