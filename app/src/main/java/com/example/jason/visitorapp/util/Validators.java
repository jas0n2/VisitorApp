package com.example.jason.visitorapp.util;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;

public class Validators {
private Context context;

public Validators(Context context){
    this.context = context;
}

public  static boolean notEmpty(TextInputEditText textInputEditText, TextInputLayout layout, String message){
    String value = textInputEditText.getText().toString();

    if(value.isEmpty()){
        layout.setErrorEnabled(true);
        layout.setError(message);
        return true;
    }else {
        return false;
    }
}


public  boolean isEmail(TextInputLayout layout,TextInputEditText editText, String message){
    String value = editText.getText().toString();
    if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()){
        layout.setErrorEnabled(true);
        layout.setError(message);

        return true;
    }else {
        return false;
    }
}
}
