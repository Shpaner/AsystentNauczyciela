package com.example.asystentnauczyciela.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.Adapters.GradesAdapter
import com.example.asystentnauczyciela.ViewModel.Adapters.RemarkAdapter
import com.example.asystentnauczyciela.ViewModel.DeleteButtonClickListener
import com.example.asystentnauczyciela.ViewModel.GradeViewModel
import com.example.asystentnauczyciela.ViewModel.RemarkViewModel
import kotlinx.android.synthetic.main.fragment_grade_view.*
import kotlinx.android.synthetic.main.fragment_remark_view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentRemarkView : Fragment(), DeleteButtonClickListener {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: RemarkViewModel
    lateinit var myAdapter: RemarkAdapter

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

        viewModel = ViewModelProvider(requireActivity()).get(RemarkViewModel::class.java)
        myAdapter = RemarkAdapter(viewModel.studentsRemarks, this)

        viewModel.studentsRemarks.observe(viewLifecycleOwner, Observer { myAdapter.notifyDataSetChanged() })
        return inflater.inflate(R.layout.fragment_remark_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentNameRV.text = ValuesHolder.student

        remarkTitle.text = viewModel.getRemark(ValuesHolder.chosenRemarkIndex).name
        remarkDescriptionRV.text = viewModel.getRemark(ValuesHolder.chosenRemarkIndex).description
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentGradeView().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDelBtnClick(position: Int) {

    }
}