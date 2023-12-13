package com.example.profilepage

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.profilepage.databinding.FragmentLanguageSelectionBinding


class LanguageSelectionFragment : Fragment() {
   private var _binding:FragmentLanguageSelectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel:ProfileViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentLanguageSelectionBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        val languages = arrayOf("English(US)", "Spanish (ESP)", "French(FR)", "German(DE)", "Chinese(CHN)", "Japanese(JP)","Georgian(KA)")


        for (language in languages) {
            val radioButton = RadioButton(requireContext())
            radioButton.text = language
            radioButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            radioButton.textSize = 22f
            val params = RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 40, 0, 0)
            radioButton.layoutParams = params
            binding.languageRadioGroup.addView(radioButton)

            if(radioButton.text == viewModel.languageName) {
                radioButton.isChecked = true
            }
        }
        binding.languageRadioGroup.setOnCheckedChangeListener {_,checkedId ->
            val selectedRadioButton = view?.findViewById<RadioButton>(checkedId)
            viewModel.languageName = selectedRadioButton?.text.toString()
        }
        binding.back.setOnClickListener {
            navigate()
        }
    }
    private fun navigate() {
        findNavController().navigate(R.id.action_languageSelectionFragment_to_profileFragment)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}