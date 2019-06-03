package com.apporchid.solution.training.constants;

/**
 * Pipeline specific common constants, for ex: datasource names, queries etc..
 */
public interface ITrainingPipelineConstants extends ITrainingCommonConstants {
	/*
	 * TODO:Please change the datasource name to your respective datasource name
	 * that is part of the training_datasources.csv You may not need this if you
	 * don't use database for the data
	 */
	public static final String DEFAULT_SQL_DATASOURCE_NAME = "aoTeamExercise";

	/*
	 * TODO: default elastic search server constants
	 */
	public static final String DEFAULT_ES_CLUSTER_NAME = "cloudseer-analytics";
	public static final String DEFAULT_ES_INDEX_NAME = "training-indexes";
	public static final String DEFAULT_ES_TYPE_NAME = "customers";

	public static final String EXCEL_DATA_PATH = DATA_PATH + "excel/";

	public static final String EXCEL_PATH_CUSTOMERS = EXCEL_DATA_PATH + "customers.xlsx";
	public static final String EXCEL_PATH_TEST_DATA_SMALL = EXCEL_DATA_PATH + "testdata-small.xlsx";
	public static final String EXCEL_PATH_PREMISES = EXCEL_DATA_PATH + "premises.xlsx";
	// db constants
	public static final String TABLE_NAME_CUSTOMERS = "customers";
	public static final String QRY_CUSTOMERS = "select * from " + TABLE_NAME_CUSTOMERS;

	// pipeline ids
	public static final String PIPELINE_ID_EXERCISE1 = "Exercise1DataPipeline";
	public static final String PIPELINE_ID_EXERCISE2 = "Exercise2DataPipeline";
	public static final String PIPELINE_ID_EXERCISE3 = "Exercise3DataPipeline";
	public static final String PIPELINE_ID_EXERCISE4 = "Exercise4DataPipeline";

	public static final String PIPELINE_ID_LOAD_CUSTOMERS_DATA_TO_DB = "LoadCustomersData2DBPipeline";
	public static final String PIPELINE_ID_LOAD_CUSTOMERS_DATA_TO_ES = "LoadCustomersData2ESPipeline";

	public static final String PIPELINE_ID_CUSTOMERS_MSO_CREATOR = "CustomersMSOCreatorPipeline";

	public static final String PIPELINE_ID_DATAVIEW = "ESCustomersDataviewPipeline";
	public static final String PIPELINE_ID_AUTOCOMPLETE = "ESCustomersAutoCompletePipeline";

	public static String APP_ID_SIMPLETABLE = "tableApp";

}
