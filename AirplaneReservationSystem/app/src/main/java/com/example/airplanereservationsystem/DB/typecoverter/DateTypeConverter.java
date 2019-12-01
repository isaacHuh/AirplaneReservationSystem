package com.example.airplanereservationsystem.DB.typecoverter;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateTypeConverter {

    @TypeConverter
    public long convertDateToLong(Date date){
        return date.getTime();
    }

    @TypeConverter
    public Date covertLongToDate(long time){
        return new Date(time);
    }
}
