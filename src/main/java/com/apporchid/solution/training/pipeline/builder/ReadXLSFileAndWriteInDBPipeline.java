package com.apporchid.solution.training.pipeline.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.cloudseer.common.pipeline.tasktype.DatasinkTaskType;
import com.apporchid.cloudseer.common.pipeline.tasktype.DatasourceTaskType;
import com.apporchid.cloudseer.datasink.db.RelationalDBDatasink;
import com.apporchid.cloudseer.datasink.db.RelationalDBDatasinkProperties;
import com.apporchid.cloudseer.datasink.log.LogDatasink;
import com.apporchid.cloudseer.datasink.log.LogDatasinkProperties;
import com.apporchid.cloudseer.datasink.output.OutputDatasink;
import com.apporchid.cloudseer.datasink.output.OutputDatasinkProperties;
import com.apporchid.cloudseer.datasource.xls.ExcelDatasource;
import com.apporchid.cloudseer.datasource.xls.ExcelDatasourceProperties;
import com.apporchid.foundation.mso.common.EDataUpdateType;
import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.foundation.pipeline.tasktype.ITaskType;
import com.apporchid.solution.training.config.builder.AppBasePipelineConfigurationBuilder;

@Component
public class ReadXLSFileAndWriteInDBPipeline extends AppBasePipelineConfigurationBuilder {
	
	public static final String PIPELINE_NAME = "ReadXLSFileAndWriteInDBPipeline";
	
	protected List<IPipeline> getPipelines() {
		List<IPipeline> pipelinesList = new ArrayList<>();
		pipelinesList.add(createPipeline(PIPELINE_NAME, taskXSLToDB()));
		
		return pipelinesList;
	}
	
	private ITaskType<?, ?>[] taskXSLToDB() {
		List<ITaskType<?, ?>> tasks = new ArrayList<>();
		
		DatasourceTaskType task1 = new DatasourceTaskType.Builder().name("Read data")
				.datasourceType(ExcelDatasource.class)
				.datasourcePropertiesType(ExcelDatasourceProperties.class)
				.property(ExcelDatasourceProperties.EProperty.urlPath.name(), EXCEL_PATH_CUSTOMERS)
				.property(ExcelDatasourceProperties.EProperty.headerRowIndex.name(), 0).build();
		
		// Another way of reading data from xls
		//DatasourceTaskType task1 = fileTaskBuilderHelper().getExcelTask("trainingdata/excel/dataview.xlsx");
		
		// This line is not doing anything just writting it to MSO object & we can see Task [mso data] has processed [0] records successfully and [0] records resulted in error.
		 DatasinkTaskType task2 = new DatasinkTaskType.Builder().name("mso data")
		  .datasinkType(OutputDatasink.class)
		  .datasinkPropertiesType(OutputDatasinkProperties.class).build();
		
		// Datasin to write data into database
		DatasinkTaskType task3 = new DatasinkTaskType.Builder().name("Write data")
				.datasinkType(RelationalDBDatasink.class).datasinkPropertiesType(RelationalDBDatasinkProperties.class)
				.property(RelationalDBDatasinkProperties.EProperty.sqlDatasourceName.name(), DEFAULT_SQL_DATASOURCE_NAME)
				.property(RelationalDBDatasinkProperties.EProperty.tableName.name(), "public.customers")
				.property(RelationalDBDatasinkProperties.EProperty.dataUpdateType.name(), EDataUpdateType.OVERWRITE)
				.build();
		
		DatasinkTaskType logData = new DatasinkTaskType.Builder().name("Write data").datasinkType(LogDatasink.class)
                .datasinkPropertiesType(LogDatasinkProperties.class)
                .property(LogDatasinkProperties.EProperty.logLevel.name(), "WARN").build();
		
		tasks.add(task1);
		//tasks.add(task2);
		tasks.add(task3);
		
		return tasks.toArray(new ITaskType<?, ?>[0]);
	}
}