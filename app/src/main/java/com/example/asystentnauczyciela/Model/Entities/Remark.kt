package com.example.asystentnauczyciela.Model.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "remark_table", foreignKeys = [
    ForeignKey(
        entity = Student::class,
        parentColumns = ["id"],
        childColumns = ["idStudent"],
        onDelete = CASCADE
    )
])
class Remark (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val idStudent: Int,
    var name: String,
    var description: String
) {
}