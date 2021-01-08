package com.example.asystentnauczyciela.Model.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystentnauczyciela.Model.Entities.Remark

@Dao
interface RemarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remark: Remark)

    @Delete
    suspend fun delete(remark: Remark)

    @Query("select * from remark_table where idStudent = :id")
    fun getStudentsRemarks(id: Int) : LiveData<MutableList<Remark>>
}