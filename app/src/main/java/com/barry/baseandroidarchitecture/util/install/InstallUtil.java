package com.barry.baseandroidarchitecture.util.install;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.content.FileProvider;

import java.io.File;

public class InstallUtil {
    public static void installApplication(Context context, String filePath) {
        File file = new File(filePath);
        //check file exist or not.
        if(file.exists())
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //after android 7, get file uri by file provider.
                Uri uri = FileProvider.getUriForFile(context, "com.barry.baseandroidarchitecture", file);
                //add install type
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //add temporary permission
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                Log.e("TAG", "Error in opening the file!");
            }
        }
        else
        {
            //file not exist.
        }
    }
}
