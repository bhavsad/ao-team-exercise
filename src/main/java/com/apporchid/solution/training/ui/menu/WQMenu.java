package com.apporchid.solution.training.ui.menu;

import java.util.HashMap;
import java.util.Map;

public class WQMenu {

	public static final String MICRO_FLOW = "Micro Flow";
	public static final String FORMS = "Forms";
	public static final String TRAINING = "Training";
	
	static Map<String, String[]> menus = new HashMap<>();
	static {
		menus.put("", new String[] { TRAINING, FORMS, MICRO_FLOW });

		menus.put(TRAINING, new String[] { "TrainingPage" });

		menus.put(FORMS, new String[] { "CustomCssPage", "FormPage" });

		menus.put(MICRO_FLOW, new String[] { "MicroflowPage" });
	}

	public static String[] getMenu(String parentId) {
		return menus.get(parentId);
	}
}
