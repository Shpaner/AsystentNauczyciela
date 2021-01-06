package com.example.asystentnauczyciela.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.Model.Entities.Student
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.DeleteButtonClickListener

class StudentListAdapter(var studentList: LiveData<MutableList<Student>>,
                         val deleteButtonClickListener: DeleteButtonClickListener
                        ): RecyclerView.Adapter<StudentListAdapter.StudentHolder>() {

    inner class StudentHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.one_row_student_list, parent, false
        )

        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        var studentIndex = holder.itemView.findViewById<TextView>(R.id.studentIndex)
        var studentFirstName= holder.itemView.findViewById<TextView>(R.id.studentName)
        var studentLastName= holder.itemView.findViewById<TextView>(R.id.studentLastName)
        var editBtn = holder.itemView.findViewById<Button>(R.id.goToEditStudentBtn)
        var delButton = holder.itemView.findViewById<Button>(R.id.deleteStudentBtn)

        studentIndex.text = studentList.value?.get(position)?.id.toString()
        studentFirstName.text = studentList.value?.get(position)?.name
        studentLastName.text = studentList.value?.get(position)?.lastName

        editBtn.setOnClickListener{
            //TODO - dodac fragment studenta (gdzie dodajemy kursy itp)
            view -> view.findNavController().navigate(R.id.action_fragmentStudentList_to_fragmentSelectedStudent)
            ValuesHolder.chosenStudentIndex = position
            ValuesHolder.chosenStudentId = studentList.value?.get(position)?.id ?: 0
        }

        delButton.setOnClickListener{
            ValuesHolder.chosenStudentIndex = position
            ValuesHolder.chosenStudentId = studentList.value?.get(position)?.id ?: 0

            deleteButtonClickListener.onDelBtnClick(position)
        }
    }

    override fun getItemCount(): Int {
        return studentList.value?.size?:0
    }
}