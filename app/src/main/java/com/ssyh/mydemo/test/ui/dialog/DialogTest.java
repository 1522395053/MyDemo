package com.ssyh.mydemo.test.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;
import android.widget.Toast;
public class DialogTest {

    public static void show(final Context context){

//        showGeneralDialog(context);
        showAlertDialog(context);
    }

    public static void showGeneralDialog(final Context context){
        Dialog dialog = new Dialog(context);
        TextView textView = new TextView(context);
        textView.setText("content");
        dialog.setContentView(textView);


        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setDimAmount(0f);
        dialog.show();
    }

    public static void showAlertDialog(final Context context){
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"好的",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"取消",Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.getWindow().setDimAmount(0f);


        alertDialog.show();
    }
}
