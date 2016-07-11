package com.pavement.kobus;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;

public class CourseDialogFragment extends DialogFragment {
    private boolean course;
    DialogListener mListener;

    /* renamed from: com.example.busclient.CourseDialogFragment.1 */
    class C01681 implements OnClickListener {
        C01681() {
        }

        public void onClick(DialogInterface dialog, int which) {
            if (CourseDialogFragment.this.course) {
                CourseDialogFragment.this.mListener.onCourseSelected(CourseDialogFragment.this, which);
            } else {
                CourseDialogFragment.this.mListener.onStopSelected(which);
            }
        }
    }

    public interface DialogListener {
        void onCourseSelected(DialogFragment dialogFragment, int i);

        void onSelectCanceled(DialogFragment dialogFragment);

        void onStopSelected(int i);
    }

    public CourseDialogFragment() {
        this.course = false;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String titleMessage = "\uc815\ub958\uc7a5\uc744 \uc120\ud0dd\ud558\uc138\uc694";
        int array = 0;
        String tag = getTag();
        boolean z = true;

        switch (array) {
            case 0:
                array = R.array.array_course;
                titleMessage = "\ucf54\uc2a4\ub97c \uc120\ud0dd\ud558\uc138\uc694";
                this.course = true;
                break;
            case 1:
                array = R.array.array_all_stops;
                break;
            case 2:
                array = R.array.array_cheonan_stops;
                break;
            case 3:
                array = R.array.array_terminal_stops;
                break;
            case 4:
                array = R.array.array_dujeong_stops;
                break;
            case 5:
                array = R.array.array_ktx_stops;
                break;
            case 6:
                array = R.array.array_shinbang_stops;
                break;
            default:
                Log.i("TEST", "dialog tag error");
                break;
        }
        Builder builder = new Builder(getActivity());
        builder.setTitle(titleMessage).setItems(array, new C01681());
        return builder.create();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        this.mListener.onSelectCanceled(this);
    }
}
