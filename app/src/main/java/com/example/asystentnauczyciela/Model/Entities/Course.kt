package com.example.asystentnauczyciela.Model.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//@Entity(tableName = "course_table", foreignKeys = [
//    ForeignKey(
//            entity = Teacher::class,
//            parentColumns = ["id"],
//            childColumns = ["idTeacher"],
//            onDelete = ForeignKey.CASCADE
//    )
//])
//
//data class Course(
//        @PrimaryKey(autoGenerate = true)val id: Int,
//        val idTeacher: Int,
//        var name: String
//)

@Entity(tableName = "course_table")
data class Course(
        @PrimaryKey(autoGenerate = true)val id: Int,
        var name: String
)