package com.apporchid.solution.training.ui.microapp;

import com.apporchid.foundation.ui.config.microapp.IMicroApplicationConfig;
import com.apporchid.solution.training.constants.ITrainingPipelineConstants;
import com.apporchid.solution.training.ui.builder.TrainingAppsBuilder;
import com.apporchid.vulcanux.ui.config.table.SimpleDataTableConfig;
import com.apporchid.vulcanux.ui.config.table.data.DataTableDataConfig;

public class Exercise1MicroApp {

	// Exercise1 Related Starting
	public static IMicroApplicationConfig<?> getExercise1App(TrainingAppsBuilder appsConfigBuilder) {
		DataTableDataConfig dataConfig = new DataTableDataConfig.Builder()
				.pipelineId(appsConfigBuilder.getId(ITrainingPipelineConstants.PIPELINE_ID_EXERCISE1))
				.build();

		SimpleDataTableConfig dataTable = new SimpleDataTableConfig.Builder()
				.withDataConfig(dataConfig)
				.build();

		return dataTable;
	}
	// Exercise1 End
}
