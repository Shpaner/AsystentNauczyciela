package com.example.asystentnauczyciela.Model.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.asystentnauczyciela.Model.Entities.Course

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: Course)

    @Delete
    suspend fun delete(course: Course)

    @Query("select * from course_table")
    fun getAllCourses() : LiveData<MutableList<Course>>

//    @Query("select * from course_table where idTeacher = :id")
//    fun teachersCourses(id: Int) : LiveData<MutableList<Course>>
//
//    @Query("select * from course_table where id in (select idCourse from participant_table where participant_table.idStudent = :studentId)")
//    fun studentsCourses(studentId: Int) : LiveData<MutableList<Course>>
}