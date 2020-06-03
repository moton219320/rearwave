package com.rearwave.platform.baidu.client;

import com.rearwave.platform.baidu.dto.request.*;
import com.rearwave.platform.baidu.dto.response.Dict;
import com.rearwave.platform.baidu.dto.response.ReplyDto;
import com.rearwave.platform.baidu.dto.response.RespDTO;
import com.rearwave.platform.baidu.dto.response.StatsInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 百家号接口调用客户端
 * @author sunyi
 */
@FeignClient(url = "http://baijiahao.baidu.com/builderinner/open/resource")
public interface BaiduFeignClient {

    /**
     * 发布图文信息
     * @param articleDTO 请求参数
     * @return
     */
    @PostMapping("/article/publish")
    RespDTO<Dict> articlePublish(@RequestBody ArticleDTO articleDTO);


    /**
     * 修改图文信息
     * @param articleDTO 请求参数
     * @return
     */
    @PostMapping("/article/republish")
    RespDTO<Dict> articleRepublish(@RequestBody ArticleDTO articleDTO);
    /**
     * 发布图集
     * @param galleryDTO 图集参数
     * @return
     */
    @PostMapping("/article/gallery")
    RespDTO<Dict> galleryPublish(@RequestBody GalleryDTO galleryDTO);

    /**
     * 发布视频内容
     * @param videoDTO 视频参数
     * @return
     */
    @PostMapping("/video/publish")
    RespDTO<Dict> videoPublish(@RequestBody VideoDTO videoDTO);

    /**
     * 文章实时数据查询
     * @param baseDTO 查询参数
     * @return
     */
    @PostMapping("/query/articleStatistics")
    RespDTO<StatsInfoDTO> getStatistics(@RequestBody BaiduBaseDTO baseDTO);

    /**
     * 查询文章 的评论记录
     * @param pageDTO 查询参数
     * @return
     */
    @PostMapping("/query/articleCommentList")
    RespDTO<ReplyDto> articleCommentList(@RequestBody PageDTO pageDTO);
}
