package com.gedehari.pubpix.database

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun epochToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToEpoch(date: Date?): Long? {
        return date?.time
    }
}