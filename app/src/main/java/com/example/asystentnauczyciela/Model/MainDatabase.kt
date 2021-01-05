package com.example.asystentnauczyciela.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// TODO dopisac reszte klas w entities

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao
//    abstract fun teacherDao(): TeacherDao
//    abstract fun courseDao(): CourseDao
//    abstract fun participantDao(): ParticipantDao
//    abstract fun gradeDao(): GradeDao
//    abstract fun testDao(): TestDao

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