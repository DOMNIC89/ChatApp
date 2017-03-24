package com.parth.chatapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.parth.chatapp.R;

public class InputDialog extends DialogFragment {

    public static InputDialog getInstance() {
        return new InputDialog();
    }

    private OnInputDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnInputDialogListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_input_text, null);
        final TextInputEditText tiet_inputEditText = (TextInputEditText) view.findViewById(R.id.input_et);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext())
            .setView(view);
        dialogBuilder.setPositiveButton("Let's Chat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onPositiveButtonClicked(tiet_inputEditText.getText().toString());
                }
                if (getDialog() != null && !getActivity().isFinishing()) {
                    dialog.dismiss();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onCancelButtonClicked();
                }
                if (getDialog() != null && !getActivity().isFinishing()) {
                    dialog.dismiss();
                }
            }
        });
        return dialogBuilder.create();
    }

    public interface OnInputDialogListener {
        void onPositiveButtonClicked(String name);
        void onCancelButtonClicked();
    }
}
