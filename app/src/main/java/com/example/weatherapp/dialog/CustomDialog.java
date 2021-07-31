package com.example.weatherapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weatherapp.R;

public class CustomDialog {

    private TextView cancel, done;
    private EditText city;
    private OnSaveListener listener;

    public  CustomDialog(OnSaveListener listener) {
        this.listener = listener;
    }

    public void showDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog);
        cancel = dialog.findViewById(R.id.cancel);
        done = dialog.findViewById(R.id.done);
        city = dialog.findViewById(R.id.city_name);
        addListener(dialog);
        dialog.show();
    }

    private void addListener(final Dialog dialog) {
        cancel.setOnClickListener(v -> dialog.dismiss());

        done.setOnClickListener(v -> {
            listener.saved(city.getText().toString());
            dialog.dismiss();
        });
    }


    public interface OnSaveListener {
        void saved(String cityName);
    }
}
