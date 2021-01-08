package com.example.asystentnauczyciela.Model.Repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.Model.Dao.RemarkDao
import com.example.asystentnauczyciela.Model.Entities.Remark

class RemarkRepository(val remarkDao: RemarkDao) {

    suspend fun insert(remark: Remark) {
        remarkDao.insert(remark)
    }

    suspend fun delete(remark: Remark) = remarkDao.delete(remark)

    fun getStudentsRemarks(studentId: Int) : LiveData<MutableList<Remark>> {
        return remarkDao.getStudentsRemarks(studentId)
    }
}