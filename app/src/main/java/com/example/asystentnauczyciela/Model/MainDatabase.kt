package com.example.asystentnauczyciela.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.asystentnauczyciela.Model.Dao.*
import com.example.asystentnauczyciela.Model.Entities.*

@Database(entities = [Student::class, Course::class, StudentsCourse::class, Grade::class, Remark::class],
    version = 9, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun studentsCourseDao(): StudentsCourseDao
    abstract fun gradeDao(): GradeDao
    abstract fun remarkDao(): RemarkDao

    companion object{
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context):MainDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this)
                {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java,
                        "myDatabase"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }

}