package com.mountain.po.mitaotv;

import com.mountain.po.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/12:00
 * @Description:
 */
@Entity
@Table(name = "mitaotv_manage_musics")
@Data
@ApiModel
public class Music extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //歌曲名字
    @NotEmpty(message = "歌曲名字不能为空")
    @ApiModelProperty(value = "歌曲名字")
    private String name;
    //歌手
    @NotEmpty(message = "歌手名字不能为空")
    @ApiModelProperty(value = "歌手名字")
    private String singer;
    //CD封面
    @NotEmpty(message = "封面图不能为空")
    @ApiModelProperty(value = "CD封面图片链接")
    private String cdCoverUrl;
    //歌曲链接
    @NotEmpty(message = "歌曲链接不能为空")
    @ApiModelProperty(value = "歌曲链接")
    private String musicUrl;
    //排序字段，权重
    @ApiModelProperty(value = "排序字段，正序")
    private Integer priority;
}
