package com.apporchid.solution.training.ui;

import com.apporchid.solution.training.constants.ITrainingPipelineConstants;
import com.apporchid.solution.training.constants.ITrainingSolutionConstants;
import com.apporchid.vulcanux.config.builder.BaseAppConfigurationBuilder;

public abstract class WQBaseAppConfigurationBuilder extends BaseAppConfigurationBuilder implements ITrainingSolutionConstants, ITrainingPipelineConstants {

	public WQBaseAppConfigurationBuilder() {
		super(DEFAULT_DOMAIN_ID, DEFAULT_SUB_DOMAIN_ID);
	}

	public abstract String getMicroAppId();

	public abstract String getMicroAppTitle();
}
