package com.example.asystentnauczyciela.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

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

//    @Query("select * from student_table where id not in (select idStudent from participant_table where idCourse = :courseId)")
//    fun getNotParticipants(courseId: Int) : LiveData<MutableList<Student>>
//
//    @Query("select * from student_table where id in (select idStudent from participant_table where idCourse = :courseId)")
//    fun getParticipants(courseId: Int) : LiveData<MutableList<Student>>
}