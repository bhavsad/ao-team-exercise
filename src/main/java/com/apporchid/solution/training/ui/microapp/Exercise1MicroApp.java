package com.apporchid.solution.training.ui.microapp;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.foundation.ui.config.application.IApplicationConfig;
import com.apporchid.foundation.ui.config.microapp.IMicroApplicationConfig;
import com.apporchid.solution.training.pipeline.builder.TrainingPipelineBuilder;
import com.apporchid.solution.training.ui.WQBaseAppConfigurationBuilder;
import com.apporchid.vulcanux.ui.config.table.SimpleDataTableConfig;
import com.apporchid.vulcanux.ui.config.table.data.DataTableDataConfig;

@Component
public class Exercise1MicroApp extends WQBaseAppConfigurationBuilder {

	public static final String MICROFLOW_ID = "Exercise1MicroApp";

	private IMicroApplicationConfig<?> getAppConfig() {
		DataTableDataConfig dataConfig = new DataTableDataConfig.Builder()
				.pipelineId(getId(TrainingPipelineBuilder.PIPELINE_ID_EXERCISE1))
				.build();

		SimpleDataTableConfig dataTable = new SimpleDataTableConfig.Builder()
				.withDataConfig(dataConfig)
				.build();

		return dataTable;
	}

	@Override
	public String getMicroAppId() {
		return MICROFLOW_ID;
	}

	@Override
	public String getMicroAppTitle() {
		return "Exercise 1 MicroApp";
	}

	@Override
	protected List<IApplicationConfig> getApplications() {
		IApplicationConfig[] microApp = new IApplicationConfig[] {
				getApplication(getMicroAppId(), getMicroAppTitle(), getAppConfig()) };
		return Arrays.asList(microApp);
	}
}
