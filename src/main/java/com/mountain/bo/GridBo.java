package com.mountain.bo;

import java.io.Serializable;


/**
 * 
 * 
* @Title: GridBo.java
* @Package com.tensei.injapan.bo 
* @Description: TODO表格生成对象
* @author porridge  
* @date  2016年5月5日 下午5:49:37 
* @version V1.0
 */
public class GridBo implements Serializable  {
	private static final long serialVersionUID = 8766296094652310714L;
	private Integer sEcho;//随机数，禁止缓存用
	private Long iTotalRecords;//总行数
	private Long iTotalDisplayRecords;//过滤总行数
	private Object aaData;


	public GridBo(Integer sEcho, Long iTotalRecords, Long iTotalDisplayRecords, Object aaData) {
		this.sEcho = sEcho;
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.aaData = aaData;
	}

	public GridBo(Long iTotalRecords, Object aaData) {
		this.iTotalRecords = iTotalRecords;
		this.aaData = aaData;
	}

	public GridBo(Long iTotalRecords, Long iTotalDisplayRecords, Object aaData) {
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.aaData = aaData;
	}

	public GridBo() {
		super();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getsEcho() {
		return sEcho;
	}

	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	public Long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public Object getAaData() {
		return aaData;
	}

	public void setAaData(Object aaData) {
		this.aaData = aaData;
	}
}
