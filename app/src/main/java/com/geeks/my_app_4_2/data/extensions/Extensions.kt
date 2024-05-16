package com.geeks.my_app_4_2.data.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

private fun<T : Any> Fragment.setBackStackData(key: String, data: T, doBack: Boolean){
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key.toString(), data)
    if(doBack){
        findNavController().navigateUp()
    }
}
fun <T: Any> Fragment.getBackStackData(key: String, result:(T)->(Unit)){
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key.toString())
        ?.observe(viewLifecycleOwner){
            result(it)
        }
}
