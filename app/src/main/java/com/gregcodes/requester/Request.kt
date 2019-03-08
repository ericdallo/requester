package com.gregcodes.requester

import com.gregcodes.requester.protocol.Protocol
import com.gregcodes.requester.verb.HTTPVerb

data class Request(
    val name: String,
    val protocol: Protocol,
    val address: String,
    val verb: HTTPVerb,
    val body: String
)