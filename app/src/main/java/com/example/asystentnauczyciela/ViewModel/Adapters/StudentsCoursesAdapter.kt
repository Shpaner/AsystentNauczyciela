package com.example.asystentnauczyciela.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.Model.Entities.Course
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.DeleteButtonClickListener

class StudentsCoursesAdapter (var studentsCoursesList: LiveData<MutableList<Course>>,
                              val deleteButtonClickListener: DeleteButtonClickListener
): RecyclerView.Adapter<StudentsCoursesAdapter.StudentsCoursesHolder>() {
    inner class StudentsCoursesHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsCoursesHolder {
        val view= LayoutInflater.from(parent.context)
                .inflate(R.layout.one_row_students_course,parent,false)
        return StudentsCoursesHolder(view)
    }

    override fun onBindViewHolder(holder: StudentsCoursesHolder, position: Int) {
        var studentCourseIndex = holder.itemView.findViewById<TextView>(R.id.studentsCourseIndex)
        var studentsCourseName = holder.itemView.findViewById<TextView>(R.id.studentsCourseName)
        var delButton = holder.itemView.findViewById<Button>(R.id.deleteStudentsCourseBtn)
        var goToGradesBtn = holder.itemView.findViewById<Button>(R.id.goToGradesBtn)

        studentCourseIndex.text = studentsCoursesList.value?.get(position)?.id.toString()
        studentsCourseName.text = studentsCoursesList.value?.get(position)?.name

        goToGradesBtn.setOnClickListener{
            view -> view.findNavController().navigate(R.id.action_fragmentStudentsCourseList_to_fragmentStudentsCourseGrades)
            ValuesHolder.chosenStudentsCourseId = studentsCoursesList.value?.get(position)?.id ?: 0
            ValuesHolder.chosenCourseName = studentsCourseName.text.toString()
        }

        delButton.setOnClickListener{
            ValuesHolder.chosenStudentsCourseId = studentsCoursesList.value?.get(position)?.id ?: 0
            deleteButtonClickListener.onDelBtnClick(position)
        }

    }

    override fun getItemCount(): Int {
        return studentsCoursesList.value?.size?:0
    }
}