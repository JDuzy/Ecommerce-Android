package org.bedu.proyectobedu.login



import com.google.android.material.textfield.TextInputEditText

class EditTextInputValidator {

    companion object{
        //Validates if given EditText is not empty, if it is shows the given error msg and return false
         fun validateEditText(editText: TextInputEditText, errorMsg: String) : Boolean{
            if(editText.text.let { it.isNullOrBlank() }){
                editText.error = errorMsg
                return false
            }
            return true
        }
    }
}