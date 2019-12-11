package com.mountain.po.mitaotv;

import com.mountain.po.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/07/18:07
 * @Description:
 */
@Data
@Entity
@Table(name = "mitaotv_manage_about_image_list")
@ApiModel
public class AboutImagelist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //关于蜜淘的id
    private Long aboutId;
    //图片链接
    private String imageUrl;
}
