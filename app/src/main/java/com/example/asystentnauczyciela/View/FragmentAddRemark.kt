package com.example.asystentnauczyciela.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.CourseViewModel
import com.example.asystentnauczyciela.ViewModel.RemarkViewModel
import kotlinx.android.synthetic.main.fragment_add_course.*
import kotlinx.android.synthetic.main.fragment_add_remark.*
import kotlinx.android.synthetic.main.fragment_remarks.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentAddRemark : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: RemarkViewModel

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
        return inflater.inflate(R.layout.fragment_add_remark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addNewRemarkButton.setOnClickListener {
            viewModel.addRemark(newRemarkDescription.text.toString(), ValuesHolder.chosenStudentId, newRemarkName.text.toString())

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentAddCourse().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}