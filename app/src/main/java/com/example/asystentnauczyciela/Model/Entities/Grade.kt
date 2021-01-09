package com.example.asystentnauczyciela.Model.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "grade_table", foreignKeys = [
    ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["idStudent"],
            onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
            entity = Course::class,
            parentColumns = ["id"],
            childColumns = ["idCourse"],
            onDelete = ForeignKey.CASCADE
    )
])
data class Grade(
        @PrimaryKey(autoGenerate = true)val id: Int,
        val idStudent: Int,
        val idCourse: Int,
        val gradeName: String,
        var gradeValue: String,
        var gradeDescription: String,
        var date: String,
        var studentName: String,
        var studentsCourseName: String) {
}