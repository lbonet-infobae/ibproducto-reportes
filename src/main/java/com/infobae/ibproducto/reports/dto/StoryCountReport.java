package com.infobae.ibproducto.reports.dto;

import java.util.Date;

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
	long storyCount;
	
	@XmlAttribute
	@ApiModelProperty()
	Date from;
	
	@XmlAttribute
	@ApiModelProperty()
	Date to;
	
	public StoryCountReport() {	}
	
	public StoryCountReport(long storyCount, Date from, Date to) {
		super();
		this.storyCount = storyCount;
		this.from = from;
		this.to = to;
	}

	public long getStoryCount() {
		return storyCount;
	}

	public void setStoryCount(long storyCount) {
		this.storyCount = storyCount;
	}

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

	
}
