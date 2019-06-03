package com.apporchid.solution.training.pipeline.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.cloudseer.common.pipeline.tasktype.DatasinkTaskType;
import com.apporchid.cloudseer.common.pipeline.tasktype.DatasourceTaskType;
import com.apporchid.cloudseer.datasink.log.LogDatasink;
import com.apporchid.cloudseer.datasink.log.LogDatasinkProperties;
import com.apporchid.cloudseer.datasource.rest.RestDatasource;
import com.apporchid.cloudseer.datasource.rest.RestDatasourceProperties;
import com.apporchid.common.enums.EContentType;
import com.apporchid.common.enums.EHttpRequestMethod;
import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.foundation.pipeline.tasktype.ITaskType;
import com.apporchid.solution.training.config.builder.AppBasePipelineConfigurationBuilder;

@Component
public class ReadJSONAndLogPipeline extends AppBasePipelineConfigurationBuilder {
	
	public static final String PIPELINE_NAME = "ReadJSONAndLogPipeline";
	
	protected List<IPipeline> getPipelines() {
		List<IPipeline> pipelinesList = new ArrayList<>();
		pipelinesList.add(createPipeline(PIPELINE_NAME, taskReadWriteJSON()));
		return pipelinesList;
	}
	
	private List<ITaskType<?, ?>> taskReadWriteJSON() {
		List<ITaskType<?, ?>> tasks = new ArrayList<>();
		
		DatasourceTaskType readJsonRest = new DatasourceTaskType.Builder().name("Read data")
                .datasourceType(RestDatasource.class).datasourcePropertiesType(RestDatasourceProperties.class)
                .property(RestDatasourceProperties.EProperty.urlPath.name(), "http://localhost:8090/v2/api-docs")
                //.property(RestDatasourceProperties.EProperty.tokenConfigName.name(), "cloudseerapp")
                .property(RestDatasourceProperties.EProperty.method.name(), EHttpRequestMethod.GET)
               .property(RestDatasourceProperties.EProperty.responseType.name(), EContentType.JSON)
                .build();
		
		DatasinkTaskType logJsonData = new DatasinkTaskType.Builder().name("Write data").datasinkType(LogDatasink.class)
                .datasinkPropertiesType(LogDatasinkProperties.class)
                .property(LogDatasinkProperties.EProperty.logLevel.name(), "WARN").build();
		
		tasks.add(readJsonRest);
		tasks.add(logJsonData);
		
		return tasks;
	}
}