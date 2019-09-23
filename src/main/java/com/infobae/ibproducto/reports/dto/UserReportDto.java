package com.infobae.ibproducto.reports.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name="userReport")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value="userReport")
public class UserReportDto {

	@XmlAttribute
	@ApiModelProperty()
	String name;
	
	@XmlAttribute
	@ApiModelProperty()
	String username;
	
	@XmlAttribute
	@ApiModelProperty()
	long storyCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getStoryCount() {
		return storyCount;
	}

	public void setStoryCount(long storyCount) {
		this.storyCount = storyCount;
	}

	
}
