package com.example.asystentnauczyciela.Model.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.asystentnauczyciela.Model.Entities.Student

@Dao
interface StudentDao {

    @Insert
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("select * from student_table")
    fun getAllStudents() : LiveData<MutableList<Student>>

    @Query("update student_table set name = :newName, lastName = :newLastName where id = :idStudent")
    fun editStudent(newName: String, newLastName: String, idStudent: Int)
}