package com.apporchid.solution.training.test;

import org.testng.annotations.Test;

import com.apporchid.solution.common.BaseSolutionTestApplication;
import com.apporchid.solution.training.constants.ITrainingPipelineConstants;
import com.apporchid.solution.training.constants.ITrainingSolutionConstants;
import com.apporchid.solution.training.pipeline.builder.ReadXLSFileAndWriteInDBPipeline;
import com.apporchid.solution.training.pipeline.builder.XLSToDBPipeline;
import com.apporchid.solution.training.ui.builder.TrainingSolutionBuilder;

public class TrainingDeploySolutionTest  extends BaseSolutionTestApplication implements ITrainingPipelineConstants, ITrainingSolutionConstants {

	@Test(groups = { "solutionMain" })
	@Override
	public void setupSolution() {
		//deploy everything
		deploy(TrainingSolutionBuilder.class);
		
		//deploy only pipelines
		//deploy(TrainingPipelineBuilder.class);

		//deploy only applications
		//deploy(TrainingAppsBuilder.class);

		//run a pipeline 
		//runPipeline(PIPELINE_ID_CUSTOMERS_MSO_CREATOR);
		//runPipeline(PIPELINE_ID_LOAD_CUSTOMERS_DATA_TO_DB);
		//runPipeline(PIPELINE_ID_LOAD_CUSTOMERS_DATA_TO_ES);
		
		//deploy(TrainingSaveDataInDBPipelineBuider.class);
		//deploy(RestToCSVPipeline.class);
		//runPipeline(RestToCSVPipeline.PIPELINE_NAME);
		
		//deploy(ReadXLSFileAndWriteInDB.class);
		//runPipeline(ReadXLSFileAndWriteInDB.PIPELINE_NAME);
		
		//deploy(XLSToDB.class);
		//runPipeline(XLSToDB.PIPELINE_NAME);
	}
	
	@Override
	protected String getSolutionPackage() {
		//TODO:Please change the package to your respective solution package name, for ex, com.companyname.solutionname
		return "com.apporchid.solution.training";
	}

	@Override
	protected String getDomainId() {
		return DEFAULT_DOMAIN_ID;
	}

	@Override
	protected String getSubDomainId() {
		return DEFAULT_SUB_DOMAIN_ID;
	}
}
