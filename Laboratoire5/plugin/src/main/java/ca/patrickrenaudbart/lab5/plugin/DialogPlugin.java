package ca.patrickrenaudbart.lab5.plugin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.util.Log;

import com.unity3d.player.UnityPlayer;

public class DialogPlugin {

    //public static final String TAG = "DialogPlugin";


    public static void ShowDialog(final String titleText,
                                  final String messageText,
                                  final String positiveText,
                                  final String neutralText,
                                  final String negativeText,
                                  final String positiveGameobject, final String positiveCallback,
                                  final String neutralGameobject, final String neutralCallback,
                                  final String negativeGameobject, final String negativeCallback){

        //Log.d(TAG, "ShowDialog(" + titleText + ")");

        UnityPlayer.currentActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                AlertDialog.Builder builder = new AlertDialog.Builder(UnityPlayer.currentActivity);


                builder.setMessage(messageText)
                        .setTitle(titleText);

                if(positiveText != null)
                {

                        builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(positiveCallback != null && positiveGameobject != null) {
                                    UnityPlayer.UnitySendMessage(positiveGameobject, positiveCallback, positiveText);
                                }
                            }
                        });
                }

                if(neutralText != null)
                {

                        builder.setNeutralButton(neutralText, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(neutralCallback != null && neutralGameobject != null) {
                                UnityPlayer.UnitySendMessage(neutralGameobject, neutralCallback, neutralText);
                                }
                            }
                        });
                }

                if(negativeText != null)
                {

                        builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(negativeCallback != null && neutralGameobject != null) {
                                UnityPlayer.UnitySendMessage(negativeGameobject, negativeCallback, negativeText);
                                }
                            }
                        });
                }
                builder.setCancelable(false);
                builder.show();
            }
        });
    }
}
