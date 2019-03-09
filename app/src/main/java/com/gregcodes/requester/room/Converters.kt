package com.gregcodes.requester.room

import androidx.room.TypeConverter
import com.gregcodes.requester.protocol.Protocol
import com.gregcodes.requester.verb.HTTPVerb

class Converters {

    @TypeConverter
    fun fromProtocol(value: String?): Protocol? {
        return if (value == null) null else Protocol.valueOf(value)
    }

    @TypeConverter
    fun toProtocol(value: Protocol?): String? {
        return value?.name
    }

    @TypeConverter
    fun fromHTTPVerb(value: String?): HTTPVerb? {
        return if (value == null) null else HTTPVerb.valueOf(value)
    }

    @TypeConverter
    fun toHTTPVerb(value: HTTPVerb?): String? {
        return value?.name
    }

}