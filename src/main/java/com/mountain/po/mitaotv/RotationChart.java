package com.mountain.po.mitaotv;

import com.mountain.po.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/11:26
 * @Description:
 */
@Entity
@Table(name = "mitaotv_manage_rotation_chart")
@Data
@ApiModel
public class RotationChart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //图片地址
    @NotEmpty(message = "图片地址不能为空")
    @ApiModelProperty(value = "图片链接")
    private String imageUrl;
    //跳转链接
    @ApiModelProperty(value = "点击图片后跳转的链接")
    private String redirectUrl;
    //排序字段，权重
    @ApiModelProperty(value = "排序字段，正序")
    private Integer priority;
}
