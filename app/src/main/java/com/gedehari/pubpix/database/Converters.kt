package com.gedehari.pubpix.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.gedehari.pubpix.model.user.User
import java.util.Date

@ProvidedTypeConverter
class Converters {
    @TypeConverter
    fun fromUser(userModel: User?): Int? {
        return userModel?.userId
    }

    @TypeConverter
    fun userIdToUser(userId: Int?): User? {
        return null
    }

    @TypeConverter
    fun fromDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToEpoch(date: Date?): Long? {
        return date?.time
    }
}