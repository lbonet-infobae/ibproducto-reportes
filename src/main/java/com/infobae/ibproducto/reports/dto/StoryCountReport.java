package com.infobae.ibproducto.reports.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name="storyCountReport")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value="storyCountReport")
public class StoryCountReport {

	@XmlAttribute
	@ApiModelProperty()
	Long storyCount;
	
	@XmlAttribute
	@ApiModelProperty()
	Integer year;
	
	@XmlAttribute
	@ApiModelProperty()
	Integer month;
	
	public StoryCountReport() {	}
	
	public StoryCountReport(Long storyCount, Integer year, Integer month) {
		super();
		this.storyCount = storyCount;
		this.year = year;
		this.month = month;
		if(this.storyCount == null) {
			this.storyCount = 0l;
		}
	}
	
	public StoryCountReport(Long storyCount, Integer year) {
		super();
		this.storyCount = storyCount;
		this.year = year;
		if(this.storyCount == null) {
			this.storyCount = 0l;
		}
	}

	public long getStoryCount() {
		return storyCount;
	}

	public void setStoryCount(Long storyCount) {
		this.storyCount = storyCount;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	
}
