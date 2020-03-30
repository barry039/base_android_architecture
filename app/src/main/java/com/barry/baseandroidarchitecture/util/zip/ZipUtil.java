package com.barry.baseandroidarchitecture.util.zip;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    private static final int BUFFER = 2048;

    public static void zip(List<File> files, File zipFile, ZipInterface zipInterface) throws IOException {
        BufferedInputStream origin = null;
        FileOutputStream dest = new FileOutputStream(zipFile);
        List<String> ziped_file_name = new ArrayList<>();
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
        try {
            byte data[] = new byte[BUFFER];

            for (int i = 0; i < files.size(); i++) {
                boolean is_repeat = true;
                while (is_repeat)
                {
                    if(ziped_file_name.contains(files.get(i).getName()))
                    {
                        i++;
                    }else
                    {
                        is_repeat = false;
                    }
                }
                ziped_file_name.add(files.get(i).getName());
                FileInputStream fi = new FileInputStream(files.get(i));
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(files.get(i).getAbsolutePath().substring(files.get(i).getAbsolutePath().lastIndexOf("/") + 1));
                out.putNextEntry(entry);
                int count;
                try {
                    while ((count = origin.read(data, 0, BUFFER)) != -1) {
                        out.write(data, 0, count);
                    }
                }finally
                {
                    origin.close();
                }
            }
            out.finish();
            out.close();
            if(zipInterface != null)
            {
                zipInterface.onSuc();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(zipInterface != null)
            {
                zipInterface.onError();
            }
        }
        finally {
            out.close();
        }
    }

    public static void unzip(File zipFile, File targetDirectory,UnZipInterface unZipInterface) throws IOException {
        ZipInputStream zis = new ZipInputStream(
                new BufferedInputStream(new FileInputStream(zipFile)));
        try {
            ZipEntry ze;
            int count;
            byte[] buffer = new byte[BUFFER];
            while ((ze = zis.getNextEntry()) != null) {
                File file = new File(targetDirectory, ze.getName());
                File dir = ze.isDirectory() ? file : file.getParentFile();
                if (!dir.isDirectory() && !dir.mkdirs())
                    throw new FileNotFoundException("Failed to ensure directory: " +
                            dir.getAbsolutePath());
                if (ze.isDirectory())
                    continue;
                FileOutputStream fout = new FileOutputStream(file);
                try {
                    while ((count = zis.read(buffer)) != -1)
                        fout.write(buffer, 0, count);
                } finally {
                    fout.close();
                }
            }
            if (unZipInterface != null)
            {
                unZipInterface.onSuc();
            }
        } catch (Exception e)
        {
            if (unZipInterface != null)
            {
                unZipInterface.onError();
            }
        } finally {
            zis.close();
        }
    }

    public interface  ZipInterface
    {
        void onSuc();
        void onError();
    }

    public interface UnZipInterface
    {
        void onSuc();
        void onError();
    }
}
