package com.apporchid.solution.training.constants;

/**
 * Solution specific solution constants, for ex: image paths etc... 
 */
public interface ITrainingSolutionConstants extends ITrainingCommonConstants {
	//TODO:please change the solution id to your respective solution id
	public static final String SOLUTION_ID = "aoteamexercise";
	//TODO:please change the solution name to your respective solution name
	public static final String SOLUTION_NAME = "Team Exercise";
	//solution specific images parent folder 
	public static final String SOLUTION_PATH = "/main-ui/solutions/" + SOLUTION_ID;

	//solution specific images parent folder 
	public static final String IMAGES_ROOT_PATH = SOLUTION_PATH + "/images/";

	//solution logo
	public static final String SOLUTION_LOGO = getImageUrl(SOLUTION_ID + "-logo.png");
	//solution icon
	public static final String SOLUTION_ICON = getImageUrl(SOLUTION_ID + "-icon.png");

	//solution specific solution js file (trainingSolutionUtils.js)
	public static final String JS_NAME_SPACE_TRAINING_SOLUTION = "trainingSolutionUtils";
	public static final String JS_NAME_SPACE_SEARCH_UTILS = "searchUtils";

	public static String getImageUrl(String url) {
		return IMAGES_ROOT_PATH + url;
	}

	public static String getWebResourcePath(String url) {
		return SOLUTION_PATH + url;
	}
}
