package com.example.asystentnauczyciela.ViewModel.Adapters

import com.example.asystentnauczyciela.Model.Entities.Remark
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.Model.Entities.Grade
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.View.FragmentGradeView
import com.example.asystentnauczyciela.ViewModel.DeleteButtonClickListener

class RemarkAdapter(
    var remarksList: LiveData<MutableList<Remark>>,
    val deleteButtonClickListener: DeleteButtonClickListener
): RecyclerView.Adapter<RemarkAdapter.RemarkHolder>() {

    inner class RemarkHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemarkHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.one_row_remark_list, parent, false
        )

        return RemarkHolder(view)
    }

    override fun onBindViewHolder(holder: RemarkHolder, position: Int) {
        var remarkIndex = holder.itemView.findViewById<TextView>(R.id.remarkIndex)
        var remarkName = holder.itemView.findViewById<TextView>(R.id.remarkName)

        var editBtn = holder.itemView.findViewById<Button>(R.id.goToEditRemarkButton)
        var delButton = holder.itemView.findViewById<Button>(R.id.deleteRemarkBtn)

        remarkIndex.text = remarksList.value?.get(position)?.id.toString()
        remarkName.text = remarksList.value?.get(position)?.name

        //TODO
        editBtn.setOnClickListener {
                view -> view.findNavController().navigate(R.id.action_fragmentRemarks_to_fragmentRemarkView)
            ValuesHolder.chosenRemarkIndex = remarksList.value?.get(position)?.id ?: 0
        }

        delButton.setOnClickListener{
            ValuesHolder.chosenRemarkIndex = remarksList.value?.get(position)?.id ?: 0
            deleteButtonClickListener.onDelBtnClick(position)
        }
    }

    override fun getItemCount(): Int {
        return remarksList.value?.size?:0
    }
}