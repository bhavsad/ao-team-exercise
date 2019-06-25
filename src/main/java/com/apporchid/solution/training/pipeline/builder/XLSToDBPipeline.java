package com.apporchid.solution.training.pipeline.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.foundation.pipeline.tasktype.ITaskType;
import com.apporchid.solution.training.config.builder.AppBasePipelineConfigurationBuilder;

@Component
public class XLSToDBPipeline  extends AppBasePipelineConfigurationBuilder {
	
	public static final String PIPELINE_NAME = "XLSToDBPipeline";
	
	protected List<IPipeline> getPipelines() {
		List<IPipeline> pipelinesList = new ArrayList<>();

		pipelinesList.add(createPipeline(PIPELINE_NAME, getExcel2DBSinkTasks(EXCEL_PATH_CUSTOMERS, "dinesh_test")));
		
		//natraj
		return pipelinesList;
	}
	
	private ITaskType<?, ?>[] getExcel2DBSinkTasks(String excelFilePath, String tableName) {
		return getExcel2DBSinkTasks(excelFilePath, tableName, null);
	}
	
	private ITaskType<?, ?>[] getExcel2DBSinkTasks(String excelFilePath, String tableName, Map<String, Object> addlProps) {
		List<ITaskType<?, ?>> pipelineTasks = new ArrayList<>();
		pipelineTasks.add(fileTaskBuilderHelper().getExcelTask(excelFilePath, false, addlProps));
		pipelineTasks.add(dbTaskBuilderHelper().getDBDatasink(tableName));

		return pipelineTasks.toArray(new ITaskType<?, ?>[0]);
	}
}
