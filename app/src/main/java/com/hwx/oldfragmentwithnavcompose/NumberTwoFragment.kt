package com.hwx.oldfragmentwithnavcompose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NumberTwoFragment : Fragment() {

    companion object {
        fun newInstance(param1: String) =
            NumberTwoFragment().apply {
                arguments = Bundle().apply {
                    putString(PARAM_ONE, param1)
                }
            }

        const val PARAM_ONE = "PARAM_ONE"
    }

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(PARAM_ONE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_number_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = view.findViewById<TextView>(R.id.tv_example)

        text.text = param1?:"Empty"
    }
}