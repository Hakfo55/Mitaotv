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
@Table(name = "mitaotv_manage_videos")
@Data
@ApiModel
public class Video extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //标题
    @ApiModelProperty(value = "标题")
    private String title;
    //视频链接
    @NotEmpty(message = "视频链接不能为空")
    @ApiModelProperty(value = "视频链接")
    private String videoUrl;

    //视频封面
    @ApiModelProperty(value = "视频封面图片链接")
    private String coverUrl;

    //播放数量
    @ApiModelProperty(value = "播放数量")
    private Integer playNumber = 0;
    //排序字段，权重
    @ApiModelProperty(value = "排序字段，正序")
    private Integer priority;
}
