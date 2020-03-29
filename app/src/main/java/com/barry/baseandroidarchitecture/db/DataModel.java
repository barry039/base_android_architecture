package com.barry.baseandroidarchitecture.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data_table")
public class DataModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "col1")
    private String col1;
    @ColumnInfo(name = "col2")
    private String col2;

    public DataModel(String col1, String col2) {
        this.col1 = col1;
        this.col2 = col2;
    }

    public int getId() {
        return id;
    }

    public String getCol1() {
        return col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }
}
