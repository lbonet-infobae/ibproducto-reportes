package com.infobae.ibproducto.reports.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel(value="user")
public class UsersReportWrapper {

	@XmlElement
	@ApiModelProperty()
	List<UserReportDto> usersReport;
	
	@XmlAttribute
	@ApiModelProperty()
	int totalResults;
	
	public UsersReportWrapper() {}
	
	public UsersReportWrapper(List<UserReportDto> usersReport, int totalResults) {
		this.usersReport = usersReport;
		this.totalResults = totalResults;
	}

	public List<UserReportDto> getUsersReport() {
		return usersReport;
	}

	public void setUsersReport(List<UserReportDto> usersReport) {
		this.usersReport = usersReport;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

}
