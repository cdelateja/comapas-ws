package com.comapas.ws.util.model

import com.comapas.ws.dto.request.CategoryReq
import com.comapas.ws.model.Category
import com.comapas.ws.util.mapper.OrikaMapper

class CategoryUtil {

    companion object {

        fun toModel(req: CategoryReq): Category {
            configMapperFieldReqModel()
            return OrikaMapper.transform(req, Category::class.java)
        }

        private fun configMapperFieldReqModel() {
            OrikaMapper.mapperFactory.classMap(Category::class.java, CategoryReq::class.java)
                .byDefault()
                .register()
        }
    }
}
