package com.mountain.po.mitaotv;

import com.mountain.po.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/11:57
 * @Description:
 */
@Entity
@Table(name = "mitaotv_manage_news_image_list")
@Data
public class NewsImageList extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //新闻id
    private Long newsId;
    //新闻图片地址
    private String newsImageUrl;
}
