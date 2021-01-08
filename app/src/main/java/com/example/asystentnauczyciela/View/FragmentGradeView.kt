package com.example.asystentnauczyciela.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.Adapters.GradesAdapter
import com.example.asystentnauczyciela.ViewModel.DeleteButtonClickListener
import com.example.asystentnauczyciela.ViewModel.GradeViewModel
import kotlinx.android.synthetic.main.fragment_grade_view.*
import kotlin.math.log

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentGradeView : Fragment(), DeleteButtonClickListener {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: GradeViewModel
    lateinit var myAdapter: GradesAdapter

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

        viewModel = ViewModelProvider(requireActivity()).get(GradeViewModel::class.java)
        myAdapter = GradesAdapter(viewModel.grades, this)

        viewModel.grades.observe(viewLifecycleOwner, Observer { myAdapter.notifyDataSetChanged() })
        return inflater.inflate(R.layout.fragment_grade_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentgName.text = ValuesHolder.student
        coursegName.text = ValuesHolder.chosenCourseName

        // TODO
        Log.d("name", "${viewModel.grades.value?.get(ValuesHolder.chosenGradeIndex)?.gradeName}")

        gradeName.text = viewModel.grades.value?.get(ValuesHolder.chosenGradeIndex)?.gradeName.toString()
        gradeValue.text = viewModel.grades.value?.get(ValuesHolder.chosenGradeIndex)?.gradeValue.toString()
        gradeDescription.text = viewModel.grades.value?.get(ValuesHolder.chosenGradeIndex)?.gradeDescription.toString()
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