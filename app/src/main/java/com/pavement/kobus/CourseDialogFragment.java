package com.pavement.kobus;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.Barcode.Phone;

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
        switch (tag.hashCode()) {
            case -1354571749:
                if (tag.equals("course")) {
                    z = false;
                    break;
                }
                break;
            case -1181248900:
                if (tag.equals("terminal")) {
                    z = true;
                    break;
                }
                break;
            case -518507662:
                if (tag.equals("shinbang")) {
                    z = true;
                    break;
                }
                break;
            case 96673:
                if (tag.equals("all")) {
                    z = true;
                    break;
                }
                break;
            case 106543:
                if (tag.equals("ktx")) {
                    z = true;
                    break;
                }
                break;
            case 742674156:
                if (tag.equals("cheonan")) {
                    z = true;
                    break;
                }
                break;
            case 2006677852:
                if (tag.equals("dujeong")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case Phone.UNKNOWN /*0*/:
                array = C0177R.array.array_course;
                titleMessage = "\ucf54\uc2a4\ub97c \uc120\ud0dd\ud558\uc138\uc694";
                this.course = true;
                break;
            case CompletionEvent.STATUS_FAILURE /*1*/:
                array = C0177R.array.array_all_stops;
                break;
            case CompletionEvent.STATUS_CONFLICT /*2*/:
                array = C0177R.array.array_cheonan_stops;
                break;
            case CompletionEvent.STATUS_CANCELED /*3*/:
                array = C0177R.array.array_terminal_stops;
                break;
            case Barcode.PHONE /*4*/:
                array = C0177R.array.array_dujeong_stops;
                break;
            case Barcode.PRODUCT /*5*/:
                array = C0177R.array.array_ktx_stops;
                break;
            case Barcode.SMS /*6*/:
                array = C0177R.array.array_shinbang_stops;
                break;
            default:
                Log.i(MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "dialog tag error");
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
