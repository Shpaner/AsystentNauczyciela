package com.example.asystentnauczyciela.Model.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystentnauczyciela.Model.Entities.StudentsCourse

@Dao
interface StudentsCourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(studentsCourse: StudentsCourse)

    @Delete
    suspend fun delete(studentsCourse: StudentsCourse)

    @Query("select * from students_course_table")
    fun getAllStudentsCourses(): LiveData<MutableList<StudentsCourse>>
}