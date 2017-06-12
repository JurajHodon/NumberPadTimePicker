package com.philliphsu.numberpadtimepickersample;

import android.app.Dialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import com.philliphsu.numberpadtimepicker.BottomSheetNumberPadTimePickerDialog;
import com.philliphsu.numberpadtimepicker.NumberPadTimePickerDialog;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NumberPadTimePickerDialogFragment extends DialogFragment {
    private static final String KEY_THEME_RES_ID = "theme_res_id";
    private static final String KEY_DIALOG_MODE = "dialog_mode";

    @StyleRes
    public static final int ALERT_THEME_LIGHT = R.style.Theme_AppCompat_Light_Dialog_Alert;
    @StyleRes
    public static final int ALERT_THEME_DARK = R.style.Theme_AppCompat_Dialog_Alert;

    public static final int MODE_ALERT = 1;
    public static final int MODE_BOTTOM_SHEET = 2;

    @IntDef({MODE_ALERT, MODE_BOTTOM_SHEET})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DialogMode {}

    @DialogMode
    private int dialogMode;

    @StyleRes
    private int themeResId;

    private OnTimeSetListener listener;

    public static NumberPadTimePickerDialogFragment newInstance(OnTimeSetListener listener,
            @DialogMode int dialogMode, @StyleRes int themeResId) {
        NumberPadTimePickerDialogFragment f = new NumberPadTimePickerDialogFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_THEME_RES_ID, themeResId);
        args.putInt(KEY_DIALOG_MODE, dialogMode);
        f.setArguments(args);
        f.listener = listener;
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            themeResId = args.getInt(KEY_THEME_RES_ID, 0);
            dialogMode = args.getInt(KEY_DIALOG_MODE, MODE_ALERT);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        switch (dialogMode) {
            case MODE_BOTTOM_SHEET:
                return new BottomSheetNumberPadTimePickerDialog(getContext(), themeResId, listener,
                        DateFormat.is24HourFormat(getContext()));
            case MODE_ALERT:
            default:
                return new NumberPadTimePickerDialog(getContext(), themeResId, listener,
                        DateFormat.is24HourFormat(getContext()));
        }
    }
}
