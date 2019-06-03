package com.apporchid.solution.training.ui.microapp;

import com.apporchid.foundation.ui.config.microapp.IMicroApplicationConfig;
import com.apporchid.solution.training.constants.ITrainingPipelineConstants;
import com.apporchid.solution.training.ui.builder.TrainingAppsBuilder;
import com.apporchid.vulcanux.ui.config.table.DataTableColumnConfig;
import com.apporchid.vulcanux.ui.config.table.SimpleDataTableConfig;
import com.apporchid.vulcanux.ui.config.table.data.DataTableDataConfig;

public class Exercise2MicroApp {

	// Exercise2 Related Starting
	public static IMicroApplicationConfig<?> getExercise2App(TrainingAppsBuilder appsConfigBuilder) {
		DataTableDataConfig dataConfig = new DataTableDataConfig.Builder()
				.pipelineId(appsConfigBuilder.getId(ITrainingPipelineConstants.PIPELINE_ID_EXERCISE2))
				.build();
		
		DataTableColumnConfig billsColumn = new DataTableColumnConfig.Builder()
				.id("bills")
				.displayName("Last 12 months bills")
				.withTemplate("{common.sparklines()}")
				.build();
		
		SimpleDataTableConfig dataTable = new SimpleDataTableConfig.Builder()
				.withDataConfig(dataConfig)
				.columns(new String[] { "businessPartnerNumber", "customerName", "customerStatus" })
				.column(billsColumn)
				.build();
		
		return dataTable;
	}
}
