package com.apporchid.solution.training.config.builder;

import com.apporchid.solution.training.constants.ITrainingPipelineConstants;

public abstract class AppBasePipelineConfigurationBuilder extends com.apporchid.cloudseer.config.builder.BasePipelineConfigurationBuilder implements ITrainingPipelineConstants {

	public AppBasePipelineConfigurationBuilder() {
		//call super with the default domain id and sub domain id so that all pipelines that are created in this class are created by default with the default domain id and sub domain id
		super(DEFAULT_DOMAIN_ID, DEFAULT_SUB_DOMAIN_ID);
		//set the default sql datasource name to the db task builder so that you don't have to pass to the individual tasks
		dbTaskBuilderHelper().setDefaultDatasourceName(DEFAULT_SQL_DATASOURCE_NAME);

		//TODO:set the default elastic search constants to the es task builder so that you don't have to pass to the individual tasks
		esTaskBuilderHelper().setDefaultClusterName(DEFAULT_ES_CLUSTER_NAME);
		esTaskBuilderHelper().setDefaultIndexName(DEFAULT_ES_INDEX_NAME);
		esTaskBuilderHelper().setDefaultTypeName(DEFAULT_ES_TYPE_NAME);
	}
}
