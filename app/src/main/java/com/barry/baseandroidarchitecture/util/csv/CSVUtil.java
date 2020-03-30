package com.barry.baseandroidarchitecture.util.csv;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    public static final String CSV_SEPARATOR = ",";

    // write list data to csv fil & save to the local storage
    public static void writeToCSV(List<CsvModel> csvModels, Context mContext, String directoryPath, String fileName)
    {
        // set dir of csv file
        File directory = new File(directoryPath);
        // check dir exist
        if(!directory.exists())
        {
            directory.mkdirs();
        }

        if(directory.exists())
        {
            try
            {
                // create buffer writer & set csv file path
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(directoryPath+ File.separator + fileName), "UTF-8"));
                // foreach to write data for every col
                for (CsvModel model : csvModels)
                {
                    StringBuffer oneLine = new StringBuffer();
                    oneLine.append(model.getColumn1());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(model.getColumn2());
                    bw.write(oneLine.toString());
                    bw.newLine();
                }
                bw.flush();
                bw.close();
            }
            catch (UnsupportedEncodingException e) {
                Log.e("File","UNSupport");}
            catch (FileNotFoundException e){Log.e("File",e.getMessage());}
            catch (IOException e){Log.e("File","IOException");}
        }

    }

    // Read Csv File To Object Arrays
    public static ArrayList<CsvModel> readCsv(String filePath)
    {
        // create data list instance
        ArrayList<CsvModel> models =new ArrayList<>();
        // get csv file instance
        File file = new File(filePath);

        try {
            // create buffer reader & set csv file
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String receiveString = "";
            while((receiveString = reader.readLine()) != null)
            {
                String[] row = receiveString.split(",");
                String column1 = "";
                String column2 = "";
                for(int i = 0 ;i <row.length;i++)
                {

                    switch (i)
                    {
                        case 0:
                            column1 = row[i];
                            break;
                        case 1:
                            column2 = row[i];
                            break;
                    }
                    if (column1 != "" && column2 != "")
                    {
                        models.add(new CsvModel(column1,column2));
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }
}
