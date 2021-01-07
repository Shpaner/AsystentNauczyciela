package com.example.asystentnauczyciela.Model.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "students_course_table", foreignKeys = [
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


data class StudentsCourse (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val idStudent: Int,
    val idCourse: Int
)
