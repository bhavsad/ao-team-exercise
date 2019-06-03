package com.apporchid.solution.training.pipeline.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.cloudseer.common.datasource.properties.BaseStructuredDatasourceProperties;
import com.apporchid.cloudseer.common.pipeline.tasktype.DatasinkTaskType;
import com.apporchid.cloudseer.common.pipeline.tasktype.DatasourceTaskType;
import com.apporchid.cloudseer.datasink.output.OutputDatasink;
import com.apporchid.cloudseer.datasink.output.OutputDatasinkProperties;
import com.apporchid.cloudseer.datasource.xls.ExcelDatasource;
import com.apporchid.cloudseer.datasource.xls.ExcelDatasourceProperties;
import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.foundation.pipeline.tasktype.ITaskType;
import com.apporchid.solution.training.config.builder.AppBasePipelineConfigurationBuilder;
import com.apporchid.solution.training.transformer.AddBillsTransformer;

@Component
public class TrainingPipelineBuilder extends AppBasePipelineConfigurationBuilder {

	// pipeline ids
	public static final String PIPELINE_ID_EXERCISE1 = "Exercise1DataPipeline";
	public static final String PIPELINE_ID_EXERCISE2 = "Exercise2DataPipeline";
	public static final String PIPELINE_ID_EXERCISE3 = "Exercise3DataPipeline";

	// Get All Pipelines Here
	@Override
	protected List<IPipeline> getPipelines() {
		List<IPipeline> pipelinesList = new ArrayList<>();

		pipelinesList.add(createPipeline(PIPELINE_ID_EXERCISE1, getExcercise1Tasks()));
		pipelinesList.add(createPipeline(PIPELINE_ID_EXERCISE2, getExcercise2Tasks()));
		pipelinesList.add(createPipeline(PIPELINE_ID_EXERCISE3, getExcercise3Tasks()));

		return pipelinesList;
	}

	// Here EXERCISE1 Pipeline Methods
	private ITaskType<?, ?>[] getExcercise1Tasks() {
		DatasourceTaskType task1 = new DatasourceTaskType.Builder().name("Read data")
				.datasourceType(ExcelDatasource.class).datasourcePropertiesType(ExcelDatasourceProperties.class)
				.property(ExcelDatasourceProperties.EProperty.urlPath.name(), EXCEL_PATH_CUSTOMERS)
				.property(ExcelDatasourceProperties.EProperty.headerRowIndex.name(), 0).build();
		DatasinkTaskType task2 = new DatasinkTaskType.Builder().name("mso data").datasinkType(OutputDatasink.class)
				.datasinkPropertiesType(OutputDatasinkProperties.class).build();
		return new ITaskType<?, ?>[] { task1, task2 };
	}

	// Exercise 2 Method
	private List<ITaskType<?, ?>> getExcercise2Tasks() {
		List<ITaskType<?, ?>> exerciseTasks = new ArrayList<>();
		exerciseTasks.add(fileTaskBuilderHelper().getExcelTaskWithCriteria(EXCEL_PATH_CUSTOMERS));
		// transformer that adds customer bills
		exerciseTasks.add(taskConfigBuilderHelper.getTransformerTask("AddBills", AddBillsTransformer.class));
		// add ui datasink
		exerciseTasks.add(getUIDataSinkTask());
		return exerciseTasks;
	}

	// Exercise 3
	private List<ITaskType<?, ?>> getExcercise3Tasks() {
		List<ITaskType<?, ?>> exerciseTasks = new ArrayList<>();
		DatasourceTaskType datasourceTask = new DatasourceTaskType.Builder()
				.name("Read data").datasourceType(ExcelDatasource.class)
				.datasourcePropertiesType(ExcelDatasourceProperties.class)
				.property(ExcelDatasourceProperties.EProperty.urlPath.name(), EXCEL_PATH_PREMISES)
				.property(ExcelDatasourceProperties.EProperty.headerRowIndex.name(), 0)
				.property(BaseStructuredDatasourceProperties.EProperty.latitudeField.name(), "latitude")
				.property(BaseStructuredDatasourceProperties.EProperty.longitudeField.name(), "longitude").build();
		exerciseTasks.add(datasourceTask);
		// add ui datasink
		exerciseTasks.add(getUIDataSinkTask());
		return exerciseTasks;
	}

}
