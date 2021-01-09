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

    @Query("select * from course_table where id in (select idCourse from students_course_table where students_course_table.idStudent = :studentId)")
    fun getStudentsCourses(studentId: Int) : LiveData<MutableList<Course>>

    @Query("select * from course_table where id not in (select idCourse from students_course_table where students_course_table.idStudent = :studentId)")
    fun getNotStudentsCourses(studentId: Int) : LiveData<MutableList<Course>>
}