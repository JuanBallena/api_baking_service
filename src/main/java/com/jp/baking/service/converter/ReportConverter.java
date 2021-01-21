package com.jp.baking.service.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jp.baking.service.dto.report.ReportDto;
import com.jp.baking.service.model.Report;

@Component
public class ReportConverter {

	public ReportDto toReportDto(Report report) {
		
		return ReportDto.builder()
				.description(report.getDescription())
				.quantity(report.getQuantity())
				.build();
	}
	
	public List<ReportDto> toReportDtoList(List<Report> reportList) {
		
		List<ReportDto> reportDtoList = new ArrayList<ReportDto>();
		
		reportList.forEach(report -> {
			reportDtoList.add(toReportDto(report));
		});
		
		return reportDtoList;
		
	}
}
