package com.apporchid.solution.training.pipeline.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.cloudseer.common.pipeline.tasktype.DatasinkTaskType;
import com.apporchid.cloudseer.common.pipeline.tasktype.DatasourceTaskType;
import com.apporchid.cloudseer.datasink.xls.ExcelDatasink;
import com.apporchid.cloudseer.datasink.xls.ExcelDatasinkProperties;
import com.apporchid.cloudseer.datasource.rest.RestDatasource;
import com.apporchid.cloudseer.datasource.rest.RestDatasourceProperties;
import com.apporchid.common.enums.EContentType;
import com.apporchid.common.enums.EHttpRequestMethod;
import com.apporchid.foundation.pipeline.EDataType;
import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.foundation.pipeline.tasktype.ITaskType;
import com.apporchid.solution.training.config.builder.AppBasePipelineConfigurationBuilder;

@Component
public class RestToCSVPipeline extends AppBasePipelineConfigurationBuilder {
	
	public static final String PIPELINE_NAME = "RestToCSVPipeline";
	
	protected List<IPipeline> getPipelines() {
		List<IPipeline> pipelinesList = new ArrayList<>();

		pipelinesList.add(createPipeline(PIPELINE_NAME, getTrainingTableTasks()));
		return pipelinesList;
	}
	
	private ITaskType<?, ?>[] getTrainingTableTasks() {
		String headers = "{Authorization:Bearer 1c66513a-d8f7-4daf-9d84-9a5b03499b46}";
		
		DatasourceTaskType task1 = new DatasourceTaskType.Builder().name("Read data")
				.datasourceType(RestDatasource.class).datasourcePropertiesType(RestDatasourceProperties.class)
				.outputDataType(EDataType.MSO_DATA)
				.property(RestDatasourceProperties.EProperty.urlPath.name(),
						"https://iaspub.epa.gov/enviro/efservice/WATER_SYSTEM/state_abbr/CT/rows/1:1/json")
				.property(RestDatasourceProperties.EProperty.method.name(), EHttpRequestMethod.GET)
				.property(RestDatasourceProperties.EProperty.responseType.name(), EContentType.JSON).build();

		DatasinkTaskType task2 = new DatasinkTaskType.Builder().name("Write data").datasinkType(ExcelDatasink.class)
				.datasinkPropertiesType(ExcelDatasinkProperties.class)
				.property(ExcelDatasinkProperties.EProperty.outputDirectory.name(), "D:\\tmp\\Output")
				.property(ExcelDatasinkProperties.EProperty.outputFileName.name(),
						"ResttoExcel" + Math.random() + ".xlsx")
				.build();
		
//		DatasourceTaskType task1 = new DatasourceTaskType.Builder().name("Read from Rest")
//				.datasourceType(RestDatasource.class).datasourcePropertiesType(RestDatasourceProperties.class)
//				.property(RestDatasourceProperties.EProperty.headers.name(), headers)
//				.property(RestDatasourceProperties.EProperty.urlPath.name(), "http://localhost:8090/v2/api-docs")
//				.property(RestDatasourceProperties.EProperty.method.name(), EHttpRequestMethod.GET)
//				// passing queryParams as variable property
//				.property(RestDatasourceProperties.EProperty.responseType.name(), EContentType.JSON)
//				.build();
//		
//		DatasinkTaskType task2 = new DatasinkTaskType.Builder().name("Write to csv")
//				.datasinkType(TextDatasink.class).datasinkPropertiesType(TextDatasinkProperties.class)
//				.property(TextDatasinkProperties.EProperty.outputDirectory.name(), "d:\\tmp\\")
//				.property(TextDatasinkProperties.EProperty.outputFileName.name(), "test.csv")
//				.build();
		
		DatasinkTaskType task3 = fileTaskBuilderHelper().getLogDataSinkTask();
		
		List<ITaskType<?, ?>> pipelineTasks = new ArrayList<>();
		pipelineTasks.add(task1);
		pipelineTasks.add(task2);
		pipelineTasks.add(task3);

		return pipelineTasks.toArray(new ITaskType<?, ?>[0]);
	}
}
