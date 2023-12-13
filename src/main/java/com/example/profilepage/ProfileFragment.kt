package com.example.profilepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.profilepage.databinding.FragmentEditProfileBinding
import com.example.profilepage.databinding.FragmentProfileBinding
import kotlinx.coroutines.runBlocking

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by activityViewModels()
    private lateinit var  myDataStore: MyDataStore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myDataStore = MyDataStore(requireContext())
        init()
    }

    private fun init() {
        runBlocking {
            binding.name.setText(myDataStore.getData("name"))
            binding.lastName.setText(myDataStore.getData("lastName"))
        }
//        binding.name.setText(viewModel.name)
//        binding.lastName.setText(viewModel.lastName)
        binding.languageInput.setText(viewModel.languageName)
        binding.editProfileLL.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
        binding.languageLL.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_languageSelectionFragment)
        }
        binding.logOut.setOnClickListener {
            viewModel.name = "Name"
            viewModel.lastName = "LastName"
            binding.name.setText(viewModel.name)
            binding.lastName.setText(viewModel.lastName)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}