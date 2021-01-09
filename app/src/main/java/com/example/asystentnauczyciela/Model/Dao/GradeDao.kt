package com.example.asystentnauczyciela.Model.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.asystentnauczyciela.Model.Entities.Grade

@Dao
interface GradeDao {
    @Insert
    suspend fun insert(grade: Grade)

    @Delete
    suspend fun delete(grade: Grade)

    @Query("select * from grade_table")
    fun getAllGrades(): LiveData<MutableList<Grade>>

    @Query("select * from grade_table where idStudent = :studentId and idCourse = :courseId")
    fun getStudentsGrades(studentId: Int, courseId: Int) : LiveData<MutableList<Grade>>

    @Query("select * from grade_table where date = :todaysDate")
    fun report(todaysDate: String) : LiveData<MutableList<Grade>>

    @Query("select * from grade_table")
    suspend fun getGradeList(): List<Grade>
}