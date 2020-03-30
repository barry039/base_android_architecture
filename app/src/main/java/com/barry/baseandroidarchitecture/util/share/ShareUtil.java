package com.barry.baseandroidarchitecture.util.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;

import static com.barry.baseandroidarchitecture.util.file.FileUtil.getFilePath;

public class ShareUtil {
    //can not with file of empty content
    public void shareFile(Context context, Uri uri)
    {
        // get share file instance
        File file = new File(getFilePath(context,uri));
        //manifests fileprovider authority name
        Uri path = FileProvider.getUriForFile(context,"\"com.barry.baseandroidarchitecture",file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        //type of share
        intent.setType("vnd.android.cursor.dir/email");
        // set permission
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // set file uri
        intent .putExtra(Intent.EXTRA_STREAM, path);
        // share file
        context.startActivity(Intent.createChooser(intent, "Send mail..."));
    }
}
