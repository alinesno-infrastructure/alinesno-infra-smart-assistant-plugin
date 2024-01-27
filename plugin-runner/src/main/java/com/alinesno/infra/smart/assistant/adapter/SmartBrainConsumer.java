//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.alinesno.infra.smart.assistant.adapter;

import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.smart.assistant.api.adapter.BrainTaskDto;
import com.alinesno.infra.smart.assistant.api.adapter.TaskContentDto;
import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;

@BaseRequest(
    baseURL = "#{alinesno.infra.gateway.host}",
    connectTimeout = 30000
)
public interface SmartBrainConsumer {
    @Post(
        url = "/api/llm/chatTask"
    )
    AjaxResult chatTask(@JSONBody BrainTaskDto dto);

    @Post(
        url = "/api/llm/chatContent"
    )
    AjaxResult chatContent(@Query("businessId") String businessId);

    @Post(
        url = "/api/llm/modifyContent"
    )
    AjaxResult modifyContent(@JSONBody TaskContentDto dto);
}
