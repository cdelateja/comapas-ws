package com.comapas.ws.util

import com.ct.rest.backend.util.json.JsonUtil
import com.fasterxml.jackson.databind.JsonNode
import org.apache.logging.log4j.LogManager
import java.util.*


class TokenUtil {

    companion object {
        private val log = LogManager.getLogger(TokenUtil::class)

        private fun getNode(token: String): JsonNode {
            val chunks: List<String> = token.split(".")
            val decoder: Base64.Decoder = Base64.getDecoder()
            return JsonUtil.getJsonNode(String(decoder.decode(chunks[1])))
        }

        fun getUsername(token: String): String {
            val node = getNode(token)
            return node.get("user_name").textValue()
        }
    }


}
