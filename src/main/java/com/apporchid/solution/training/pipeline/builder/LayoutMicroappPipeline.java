package com.apporchid.solution.training.pipeline.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.foundation.pipeline.tasktype.ITaskType;
import com.apporchid.solution.training.config.builder.AppBasePipelineConfigurationBuilder;

@Component
public class LayoutMicroappPipeline extends AppBasePipelineConfigurationBuilder {

	public static final String EXCEL_PATH_TESTDATA = "trainingdata/excel/testdata-small.xlsx";
	public static final String PIPELINE_ID_TABLEANDCHART_CONTAINER="TrainingTableChartPipeline";
	public static final String PIPELINE_NAME = "LayoutMicroappPipeline";

	@Override
	protected List<IPipeline> getPipelines() {
		List<IPipeline> pipelinesList = new ArrayList<>();
		pipelinesList.add(createPipeline(PIPELINE_NAME, fileTaskBuilderHelper().getExcelTasks(EXCEL_PATH_TEST_DATA_SMALL)));
		return pipelinesList;
	}
	private ITaskType<?, ?>[] getTrainingContainerTask() {
		return fileTaskBuilderHelper().getExcelTasks(EXCEL_PATH_TESTDATA);
	}
}
