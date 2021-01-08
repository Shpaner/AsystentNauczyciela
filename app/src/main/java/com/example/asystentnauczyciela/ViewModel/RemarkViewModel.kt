package com.example.asystentnauczyciela.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.Model.Entities.Remark
import com.example.asystentnauczyciela.Model.MainDatabase
import com.example.asystentnauczyciela.Model.Repositories.RemarkRepository
import com.example.asystentnauczyciela.Model.ValuesHolder
import kotlinx.coroutines.launch

class RemarkViewModel(application: Application): AndroidViewModel(application) {

    //val students: LiveData<MutableList<Student>>
    val remarkRepository: RemarkRepository
    var studentsRemarks: LiveData<MutableList<Remark>>

    init{
        //students = AssistentDatabase.getDatabase(application).studentDao().allStudents()
        remarkRepository = RemarkRepository(MainDatabase.getDatabase(application).remarkDao())
        studentsRemarks = MainDatabase.getDatabase(application).remarkDao().getStudentsRemarks(ValuesHolder.chosenStudentId)
    }

    fun addRemark(description: String, idStudent: Int, name: String)
    {
        viewModelScope.launch {
            remarkRepository.insert(Remark(description = description, idStudent = idStudent, name = name, id=0))
        }
    }

    fun deleteRemark(remark: Remark){
        viewModelScope.launch {
            remarkRepository.delete(remark)
        }
    }

}