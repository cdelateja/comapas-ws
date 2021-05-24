package com.comapas.ws.util.model

import com.comapas.ws.dto.request.ConfigReq
import com.comapas.ws.model.Config
import com.comapas.ws.util.mapper.OrikaMapper

class ConfigUtil {

    companion object {

        fun toModel(req: ConfigReq): Config {
            configMapperFieldReqModel()
            return OrikaMapper.transform(req, Config::class.java)
        }

        private fun configMapperFieldReqModel() {
            OrikaMapper.mapperFactory.classMap(Config::class.java, ConfigReq::class.java)
                .byDefault()
                .register()
        }
    }
}
