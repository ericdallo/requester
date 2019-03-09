package com.gregcodes.requester

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gregcodes.requester.protocol.Protocol
import com.gregcodes.requester.verb.HTTPVerb

@Entity(tableName = "request")
data class Request (
    var name: String,


    var protocol: Protocol,
    var address: String,
    var verb: HTTPVerb,
    var body: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}