package com.example.asystentnauczyciela.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.Model.Entities.Grade
import com.example.asystentnauczyciela.Model.Entities.Remark
import com.example.asystentnauczyciela.Model.MainDatabase
import com.example.asystentnauczyciela.Model.Repositories.RemarkRepository
import com.example.asystentnauczyciela.Model.ValuesHolder
import kotlinx.coroutines.launch

class RemarkViewModel(application: Application): AndroidViewModel(application) {

    val remarkRepository: RemarkRepository
    var studentsRemarks: LiveData<MutableList<Remark>>
    var remarkList: List<Remark>

    init{
        remarkRepository = RemarkRepository(MainDatabase.getDatabase(application).remarkDao())
        studentsRemarks = MainDatabase.getDatabase(application).remarkDao().getStudentsRemarks(ValuesHolder.chosenStudentId)
        remarkList = listOf(Remark(999, 999, "tmp", "tmp"))
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

    fun getRemark(index: Int): Remark {

        viewModelScope.launch {
            remarkList = remarkRepository.getRemarkList()
        }

        remarkList.forEach {

            if (it.id == index) {
                return it
            }
        }
        return Remark(999, 999, "tmp", "tmp")
    }

}