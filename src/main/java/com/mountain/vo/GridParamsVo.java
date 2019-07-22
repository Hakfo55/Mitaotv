package com.mountain.vo;

import io.swagger.annotations.ApiParam;

public class GridParamsVo {
	@ApiParam(required = true, value = "页码 从 1 开始", defaultValue = "1")
	private Integer page;
	@ApiParam(required = false, value = "搜索关键字")
	private String search;
	@ApiParam(required = false, hidden = true)
	private String orderBy;		// 字段名 根据哪个字段进行排序
	@ApiParam(required = false, hidden = true)
	private String sort;		// DESC 倒序  ASC 正序
	@ApiParam(required = false)
	private Integer draw;
	@ApiParam(required = false)
	private Integer begin;
	@ApiParam(required = false)
	private Integer end;

	public GridParamsVo() {
		page = 1;
		orderBy="id";
		sort = "asc";
		begin = 0;
		end = 20;
	}

	public Integer getPage() {
		if(page == null) {
            page = 1;
        }
		if(page <= 0) {
            page = 1;
        }
		return page - 1;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getBegin() {
		return begin == null ? getPage() * 10 : begin;
	}

	public Integer getEnd() {
		return end == null ? getPage() * 10 + 10 : end;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
}
