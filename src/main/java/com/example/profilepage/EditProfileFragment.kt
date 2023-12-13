package com.example.profilepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.profilepage.databinding.FragmentEditProfileBinding
import kotlinx.coroutines.runBlocking

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var  myDataStore: MyDataStore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myDataStore = MyDataStore(requireContext())
        init()
    }

    private fun init() {
        runBlocking {
            binding.nameET.setText(myDataStore.getData("name"))
            binding.lastNameET.setText(myDataStore.getData("lastName"))
        }
//        binding.nameET.setText(viewModel.name)
//        binding.lastNameET.setText(viewModel.lastName)
        binding.updateBtn.setOnClickListener {
            runBlocking {
              myDataStore.saveData("name",binding.nameET.text.toString())
                myDataStore.saveData("lastName",binding.lastNameET.text.toString())
            }
            viewModel.name = binding.nameET.text.toString()
            viewModel.lastName = binding.lastNameET.text.toString()
            navigate()
        }

        binding.back.setOnClickListener {
            navigate()
        }
    }

    private fun navigate() {
        findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}