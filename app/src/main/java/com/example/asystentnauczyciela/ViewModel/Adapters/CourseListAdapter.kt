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

class CourseListAdapter(var courseList: LiveData<MutableList<Course>>,
                     val deleteButtonClickListener: DeleteButtonClickListener
                    ): RecyclerView.Adapter<CourseListAdapter.CoursesHolder>() {
    inner class CoursesHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesHolder {
        val view= LayoutInflater.from(parent.context)
                .inflate(R.layout.one_row_course_list,parent,false)
        return CoursesHolder(view)
    }

    override fun onBindViewHolder(holder: CoursesHolder, position: Int) {
        var courseIndex = holder.itemView.findViewById<TextView>(R.id.courseIndex)
        var courseName = holder.itemView.findViewById<TextView>(R.id.courseName)
        var delButton = holder.itemView.findViewById<Button>(R.id.deleteCourseBtn)

        courseIndex.text = courseList.value?.get(position)?.id.toString()
        courseName.text = courseList.value?.get(position)?.name

        delButton.setOnClickListener{
            ValuesHolder.chosenStudentIndex = position
            ValuesHolder.chosenStudentId = courseList.value?.get(position)?.id ?: 0

            deleteButtonClickListener.onDelBtnClick(position)
        }
    }

    override fun getItemCount(): Int {
        return courseList.value?.size?:0
    }
}