package com.example.asystentnauczyciela.Model.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class Course(
        @PrimaryKey(autoGenerate = true)val id: Int,
        var name: String
)