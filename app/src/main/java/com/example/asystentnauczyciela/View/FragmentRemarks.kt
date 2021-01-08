package com.example.asystentnauczyciela.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.Adapters.GradesAdapter
import com.example.asystentnauczyciela.ViewModel.Adapters.RemarkAdapter
import com.example.asystentnauczyciela.ViewModel.Adapters.StudentsCoursesAdapter
import com.example.asystentnauczyciela.ViewModel.DeleteButtonClickListener
import com.example.asystentnauczyciela.ViewModel.GradeViewModel
import com.example.asystentnauczyciela.ViewModel.RemarkViewModel
import com.example.asystentnauczyciela.ViewModel.StudentsCoursesViewModel
import kotlinx.android.synthetic.main.fragment_grades_list.*
import kotlinx.android.synthetic.main.fragment_grades_list.*
import kotlinx.android.synthetic.main.fragment_remarks.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentRemarks : Fragment(), DeleteButtonClickListener {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: RemarkViewModel
    lateinit var myAdapter: RemarkAdapter
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(RemarkViewModel::class.java)
        viewModel.studentsRemarks = viewModel.remarkRepository.getStudentsRemarks(ValuesHolder.chosenStudentId)
        myAdapter = RemarkAdapter(viewModel.studentsRemarks, this)

        viewModel.studentsRemarks.observe(viewLifecycleOwner, Observer { myAdapter.notifyDataSetChanged() })
        return inflater.inflate(R.layout.fragment_remarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentNameOnRemark.text = ValuesHolder.student

        //TODO
        goToAddRemarkButton.setOnClickListener {
                view -> view.findNavController().navigate(R.id.action_fragmentRemarks_to_fragmentAddRemark)
        }

        recyclerView = RemarksRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentGradesList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDelBtnClick(position: Int) {
        viewModel.studentsRemarks.value?.get(ValuesHolder.chosenStudentIndex)?.let {
            viewModel.deleteRemark(it)
        }
    }
}