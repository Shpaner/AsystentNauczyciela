package com.example.asystentnauczyciela.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
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
//        var goToGradesBtn = holder.itemView.findViewById<Button>(R.id.goToStudentsGrades)

        studentCourseIndex.text = studentsCoursesList.value?.get(position)?.id.toString()
        studentsCourseName.text = studentsCoursesList.value?.get(position)?.name

        delButton.setOnClickListener{
            ValuesHolder.chosenStudentsCourseId = studentsCoursesList.value?.get(position)?.id ?: 0

            deleteButtonClickListener.onDelBtnClick(position)
        }

        // TODO przejscie do ocen
//        goToGradesBtn.setOnClickListener {
//            view -> view.findNavController().navigate(R.id.action_fragmentStudentsCourses_to_fragmentStudentsGrades)
//            DataSource.chosenStudentsCourseId = studentCList.value?.get(position)?.id ?: 0
//        }

    }

    override fun getItemCount(): Int {
        return studentsCoursesList.value?.size?:0
    }
}