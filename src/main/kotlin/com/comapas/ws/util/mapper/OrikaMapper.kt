package com.comapas.ws.util.mapper

import ma.glasnost.orika.MapperFacade
import ma.glasnost.orika.impl.DefaultMapperFactory

class OrikaMapper {

    companion object {
        val mapperFactory = DefaultMapperFactory.Builder().build()

        fun <T> transform(data: Any, clazz: Class<T>): T {
            return mapperFactory.mapperFacade.map(data, clazz)
        }
    }

}
