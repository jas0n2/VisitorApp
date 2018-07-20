package com.example.jason.visitorapp.util;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.jason.visitorapp.SigninForm;

public class Validators {

    static  String  spinnerValidationtext ="";
private Context context;
static Boolean validate = false;
String ValidatorValues = "";

public Validators(Context context){
    this.context = context;
}

public  static boolean notEmpty(final TextInputEditText textInputEditText, final TextInputLayout layout){

    final Boolean v = false;
    String value = textInputEditText.getText().toString();

    if(value.isEmpty()){
        layout.setErrorEnabled(true);
        layout.setError("This Field is Required");
        return false;
        //Log.i("else",value);
    }else  {
        layout.setErrorEnabled(false);
        Log.i("else","true");

        return  true;
    }


}


public  static   boolean notEmptyAutocomplete(AutoCompleteTextView textView, TextInputLayout textInputLayout){
String autocomplete = textView.getText().toString();
if(autocomplete.isEmpty()){
    textInputLayout.setErrorEnabled(true);
    textInputLayout.setError("This field is required");
    return false;
}else {
    textInputLayout.setErrorEnabled(true);

    return true;
}
}
public   static  boolean validateSpinner(AppCompatSpinner spinner, TextInputLayout textInputLayout){

  spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
          spinnerValidationtext = adapterView.getItemAtPosition(i).toString();

      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
  });
    Log.i("not s",spinnerValidationtext);

    if(spinnerValidationtext.equals("Select Reason")){
     textInputLayout.setErrorEnabled(true);
      textInputLayout.setError("Please Choose a reason");
        Log.i("not s","d");

        return  false;

    }else{
        Log.i("not s","3");

        textInputLayout.setErrorEnabled(false);

      return  true;
  }
}


public  static boolean isEmail(TextInputLayout layout,TextInputEditText editText){
    String value = editText.getText().toString();
    if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()){
        layout.setErrorEnabled(true);
        layout.setError("This Field is Required");

        return true;
    }else {
        return false;
    }
}
}
