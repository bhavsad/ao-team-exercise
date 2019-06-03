package com.apporchid.solution.training.pipeline.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.foundation.pipeline.IPipeline;
import com.apporchid.solution.training.config.builder.AppBasePipelineConfigurationBuilder;

@Component
public class ContainerMicroappPipeline extends AppBasePipelineConfigurationBuilder {

	public static final String PIPELINE_NAME = "ContainerMicroappPipeline";

	protected List<IPipeline> getPipelines() {
		List<IPipeline> pipelinesList = new ArrayList<>();
		pipelinesList.add(createPipeline(PIPELINE_NAME, fileTaskBuilderHelper().getExcelTasks(EXCEL_PATH_TEST_DATA_SMALL)));
		return pipelinesList;
	}
}
