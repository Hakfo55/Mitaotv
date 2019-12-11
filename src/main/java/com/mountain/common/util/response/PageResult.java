package com.mountain.common.util.response;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: 陈宇健
 * @Date: 2019/12/03/16:27
 * @Description:
 */
@Data
public class PageResult {
    private int page;			// 当前页数
    private int total;			// 总页数
    private long records;		// 总记录数
    private List<?> rows;

    public PageResult(Integer page, Page pageObject){
        this.page = page;
        this.total = pageObject.getTotalPages();
        this.records = pageObject.getTotalElements();
        this.rows = pageObject.getContent();
    }
}
