package com.infobae.ibproducto.reports.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value="user")
public class SearchFilter {
	
	@XmlAttribute
	@ApiModelProperty()
	int pageSize;
	
	@XmlAttribute
	@ApiModelProperty()
	int pageNumber;

	@XmlAttribute
	@ApiModelProperty()
	Date from;
	
	@XmlAttribute
	@ApiModelProperty()
	Date to;
	
	@XmlAttribute
	@ApiModelProperty()
	String name;

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
}
