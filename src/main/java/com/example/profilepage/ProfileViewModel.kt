package com.example.profilepage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {

    var name by mutableStateOf("Name")
    var lastName by mutableStateOf("Lastname")
    var languageName by mutableStateOf("English(US)")

}