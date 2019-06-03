package com.apporchid.solution.training.ui.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.foundation.common.IJSFunction;
import com.apporchid.foundation.ui.config.IUIComponentReferenceConfig;
import com.apporchid.foundation.ui.config.application.IApplicationConfig;
import com.apporchid.solution.training.constants.ITrainingPipelineConstants;
import com.apporchid.solution.training.constants.ITrainingSolutionConstants;
import com.apporchid.solution.training.ui.microapp.BasicFormMicroApp;
import com.apporchid.solution.training.ui.microapp.Exercise1MicroApp;
import com.apporchid.solution.training.ui.microapp.Exercise2MicroApp;
import com.apporchid.solution.training.ui.microapp.Exercise3MicroApp;
import com.apporchid.solution.training.ui.microapp.MicroFlowMicroApp;
import com.apporchid.vulcanux.config.builder.BaseAppConfigurationBuilder;
import com.apporchid.vulcanux.config.builder.helper.UIControlConfigurationBuilderHelper;
import com.apporchid.vulcanux.ui.config.uicontrol.SwitchButtonConfig;

@Component
public class TrainingAppsBuilder extends BaseAppConfigurationBuilder
		implements ITrainingSolutionConstants, ITrainingPipelineConstants {
	
	private UIControlConfigurationBuilderHelper uiControlConfigHelper;
	
	public TrainingAppsBuilder() {
		// call super with the default domain id and sub domain id so that all
		// application that are created in this class are created by default with the
		// default domain id and sub domain id
		super(DEFAULT_DOMAIN_ID, DEFAULT_SUB_DOMAIN_ID);
		setJsNamespace(JS_NAME_SPACE_TRAINING_SOLUTION);
		
		uiControlConfigHelper = new UIControlConfigurationBuilderHelper();
	}

	@Override
	protected List<IApplicationConfig> getApplications() {
		
		IApplicationConfig[] selectControlsApplications = new IApplicationConfig[] {
				getApplication(APP_ID_EXCERCISE1, "Exercise 1: Customers", Exercise1MicroApp.getExercise1App(this)),
				getApplication(APP_ID_EXCERCISE2, "Exercise 2: Customers with Billing Details", Exercise2MicroApp.getExercise2App(this)),
				getGoogleMapsApplication(APP_ID_EXCERCISE3, "Exercise 3: Premises",Exercise3MicroApp.getExercise3App(this)),
				getApplication(TrainingSolutionBuilder.APP_ID_FORM, "Example Form",BasicFormMicroApp.getBasicFormControls(this)),
				getApplication(TrainingSolutionBuilder.APP_ID_MICRO_FLOW, "Example Microflow",MicroFlowMicroApp.getMicroFlow(this))

		};
		return Arrays.asList(selectControlsApplications);
	}

	// adding Active or Inactive Button
	private List<IUIComponentReferenceConfig> getExcercise2AppHeaderButtons() {
		List<IUIComponentReferenceConfig> appHeaderButtons = new ArrayList<>();
		SwitchButtonConfig switchConfig = new SwitchButtonConfig.Builder().withId("switchBoxOnOffId").withWidth(100)
				.withOnLabel("Active").withOffLabel("Inactive").check(true).build();
		appHeaderButtons.add(switchConfig);
		appHeaderButtons.add(getSpacer(30));
		return appHeaderButtons;
	}
	
	@Override
	protected List<IUIComponentReferenceConfig> getAppHeaderAdditionalButtons(String appId) {
		switch (appId) {
		case APP_ID_EXCERCISE2:
			return getExcercise2AppHeaderButtons();
		default:
			return null;
		}
	}
	
	public UIControlConfigurationBuilderHelper getUiControlConfigHelper() {
		return uiControlConfigHelper;
	}
	
	public IJSFunction getJSFunction(String jsNamespace, String jsMethodName) {
		return getJSFunction(jsNamespace, jsMethodName, null);
	}
}
