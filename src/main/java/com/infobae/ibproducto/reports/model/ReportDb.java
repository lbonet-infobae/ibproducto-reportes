package com.infobae.ibproducto.reports.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "report")
public class ReportDb {

	Long id;
	
	Integer storyCount;
	
	Integer medianDeadlineMiss;
	
	Integer year;
	
	Integer month;
	
	UserDb user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "story_count")
	public Integer getStoryCount() {
		return storyCount;
	}

	public void setStoryCount(Integer storyCount) {
		this.storyCount = storyCount;
	}

	@Column(name = "year", nullable = false)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "month", nullable = false)
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public UserDb getUser() {
		return user;
	}

	public void setUser(UserDb user) {
		this.user = user;
	}

	@Column(name = "median_deadline_miss")
	public Integer getMedianDeadlineMiss() {
		return medianDeadlineMiss;
	}

	public void setMedianDeadlineMiss(Integer medianDeadlineMiss) {
		this.medianDeadlineMiss = medianDeadlineMiss;
	}
	
	
}
