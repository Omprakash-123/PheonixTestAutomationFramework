package com.api.pojo;

public class Problems {
	private int id;
	private String remark;

	public Problems(int string, String remark) {
		super();
		this.id = string;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Problems [id=" + id + ", remark=" + remark + "]";
	}
	
}
