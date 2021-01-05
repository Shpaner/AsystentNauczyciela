package com.example.asystentnauczyciela.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.Adapters.StudentListAdapter
import com.example.asystentnauczyciela.ViewModel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.one_row_student_list.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentStudentList : Fragment(), StudentListAdapter.DeleteButtonClickListener {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: StudentViewModel
    private lateinit var myAdapter: StudentListAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        myAdapter = StudentListAdapter(viewModel.students, this)

        viewModel.students.observe(viewLifecycleOwner, androidx.lifecycle.Observer { myAdapter.notifyDataSetChanged() })
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goToAddStudentBtn.setOnClickListener{
            view -> view.findNavController().navigate(R.id.action_fragmentStudentList_to_fragmentAddStudent)
        }

        recyclerView = studentListRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentStudentList().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onDelBtnClick(position: Int) {
            viewModel.students.value?.get(ValuesHolder.chosenStudentIndex)?.let {
                viewModel.deleteStudent(it)
            }
    }
}