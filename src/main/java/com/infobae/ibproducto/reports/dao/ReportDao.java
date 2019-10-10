package com.infobae.ibproducto.reports.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infobae.ibproducto.reports.dto.StoryCountReport;
import com.infobae.ibproducto.reports.dto.UserReportDto;
import com.infobae.ibproducto.reports.model.ReportDb;
import com.infobae.ibproducto.reports.model.UserDb;

public interface ReportDao extends JpaRepository<ReportDb, Long>{

	Optional<ReportDb> findByUserAndMonthAndYear(UserDb user, int month, int year);
	
	@Query("select new com.infobae.ibproducto.reports.dto.UserReportDto(u.name, u.username, r.storyCount, r.medianDeadlineMiss) "
			+ "from ReportDb r inner join r.user u where r.year = :year and r.month = :month order by r.storyCount desc")
	List<UserReportDto> findUsersReportsByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);
	
	@Query("select new com.infobae.ibproducto.reports.dto.StoryCountReport(SUM(r.storyCount), r.year, r.month) "
			+ "from ReportDb r where r.year = :year and r.month = :month order by r.storyCount desc")
	StoryCountReport findStoryCountByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);
	
}
