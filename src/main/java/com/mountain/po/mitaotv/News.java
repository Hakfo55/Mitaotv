package com.mountain.po.mitaotv;

import com.mountain.po.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/11:54
 * @Description:
 */
@Entity
@Table(name = "mitaotv_manage_news")
@Data
@ApiModel
public class News extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //标题
    @NotEmpty(message = "标题不能为空")
    @ApiModelProperty(value = "标题")
    private String title;
    //简介
    @ApiModelProperty(value = "简介")
    private String synopsis;
    //内容
    @NotEmpty(message = "内容不能为空")
    @ApiModelProperty(value = "新闻内容")
    private String content;
    //封面图
    @ApiModelProperty(value = "封面图片链接")
    private String coverImageUrl;
    //大图
    @ApiModelProperty(value = "大图链接")
    private String bigImageUrl;
    //排序字段，权重
    @ApiModelProperty(value = "排序字段，正序")
    private Integer priority;
}
