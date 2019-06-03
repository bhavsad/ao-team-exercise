package com.apporchid.solution.training.constants;

import com.apporchid.core.mso.util.MSOUtils;

/**
 * Common constants 
 */
public interface ITrainingCommonConstants {
	//TODO:Please change the domain name to your domain name, usually its com.<companyname>
	public static final String DEFAULT_DOMAIN_ID = "com.apporchid";
	//TODO:Please change the sub domain name to your sub domain name, usually its the solution id
	public static final String DEFAULT_SUB_DOMAIN_ID = "aoTeamExercise";
	//TODO:Please change the data path, it refers to a folder under src/main/resources where all the static data resides
	//you may not always need to have data for your solution
	public static final String DATA_PATH = "C:/ao/sampledata/";

	//mso names
	public static final String MSO_NAME_CUSTOMER = "Customer";
	public static final String MSO_CUSTOMER = MSOUtils.getClassName(DEFAULT_DOMAIN_ID, DEFAULT_SUB_DOMAIN_ID, MSO_NAME_CUSTOMER);

}
