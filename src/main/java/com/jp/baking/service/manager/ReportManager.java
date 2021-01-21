package com.jp.baking.service.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.baking.service.converter.ReportConverter;
import com.jp.baking.service.definition.ParameterDefinition;
import com.jp.baking.service.dto.activity.ActivityDto;
import com.jp.baking.service.dto.report.ReportDto;
import com.jp.baking.service.model.Report;
import com.jp.baking.service.response.ListResponse;

@Service
public class ReportManager {
	
	private static final String DESCRIPCION_FOR_BAKING = "Por hornear";
	private static final String DESCRIPCION_READY_TO_DELIVER = "Listos para entregar";
	private static final String DESCRIPCION_DELIVERED = "Entregados";
	private static final String DESCRIPCION_TOTAL_BAKE_TICKETS = "Total boletas";
	private static final String DESCRIPCION_QUANTITY_BAKED = "Total fuentes hornado";
	
	@Autowired
	private BakeTicketManager bakeTicketManager;
	
	@Autowired
	private ActivityManager activityManager;
	
	@Autowired
	private SettingManager settingManager;
	
	@Autowired
	private ReportConverter reporConverter;

	public ListResponse findAll() {
		
		ActivityDto activity = activityManager.findById(settingManager.currentActivityValue());
		List<Report> reportList = new ArrayList<Report>();
		
		reportList.add(Report.builder()
				.description(DESCRIPCION_FOR_BAKING)
				.quantity(bakeTicketManager.totalBakeTicktesAccordingToActivityAndBakingStatus(activity.getId(), ParameterDefinition.PARAMETER_FOR_BAKING)).build());
		
		reportList.add(Report.builder()
				.description(DESCRIPCION_READY_TO_DELIVER)
				.quantity(bakeTicketManager.totalBakeTicktesAccordingToActivityAndBakingStatus(activity.getId(), ParameterDefinition.PARAMETER_READY_TO_DELIVER)).build());
		
		reportList.add(Report.builder()
				.description(DESCRIPCION_DELIVERED)
				.quantity(bakeTicketManager.totalBakeTicktesAccordingToActivityAndBakingStatus(activity.getId(), ParameterDefinition.PARAMETER_DELIVERED)).build());
		
		reportList.add(Report.builder()
				.description(DESCRIPCION_TOTAL_BAKE_TICKETS)
				.quantity(bakeTicketManager.totalBakeTicketsAccordingToActivity(activity.getId())).build());
		
		reportList.add(Report.builder()
				.description(DESCRIPCION_QUANTITY_BAKED)
				.quantity(bakeTicketManager.totalBakesAccordingToActivity(activity.getId())).build());
		
		return toListResponse(reportList);
	}
	
	private ListResponse toListResponse(List<Report> list) {
		
		List<ReportDto> reportDtoList = reporConverter.toReportDtoList(list);
		return ListResponse.builder()
				.list(reportDtoList)
				.totalPages(reportDtoList.size() == 0 ? 0 : 1)
				.build();
	}
}
