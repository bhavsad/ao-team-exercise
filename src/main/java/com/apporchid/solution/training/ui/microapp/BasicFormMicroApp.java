package com.apporchid.solution.training.ui.microapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.foundation.ui.ELayoutDirection;
import com.apporchid.foundation.ui.config.IUIComponentReferenceConfig;
import com.apporchid.foundation.ui.config.IUIContainerConfig;
import com.apporchid.foundation.ui.config.application.IApplicationConfig;
import com.apporchid.solution.training.ui.WQBaseAppConfigurationBuilder;
import com.apporchid.vulcanux.common.ui.config.UIContainerConfig;
import com.apporchid.vulcanux.common.ui.config.layout.RowsColsLayoutConfig;
import com.apporchid.vulcanux.common.ui.data.OptionsData;
import com.apporchid.vulcanux.config.builder.helper.UIControlConfigurationBuilderHelper;
import com.apporchid.vulcanux.ui.config.microapp.controls.RadioConfig;
import com.apporchid.vulcanux.ui.config.microapp.controls.RichSelectConfig;
import com.apporchid.vulcanux.ui.config.segmented.SegmentedButtonConfig;
import com.apporchid.vulcanux.ui.config.slider.SliderConfig;
import com.apporchid.vulcanux.ui.config.slider.SliderDataConfig;
import com.apporchid.vulcanux.ui.config.uicontrol.ButtonConfig;
import com.apporchid.vulcanux.ui.config.uicontrol.CheckboxConfig;
import com.apporchid.vulcanux.ui.config.uicontrol.SwitchButtonConfig;
import com.apporchid.vulcanux.ui.config.uicontrol.TextAreaConfig;
import com.apporchid.vulcanux.ui.config.uicontrol.TextConfig;

@Component
public class BasicFormMicroApp extends WQBaseAppConfigurationBuilder  {
	
	public static final String MICROFLOW_ID = "forms";

	private static String[] optionMF = new String[] { "Male", "Female" };
	private static String[] optionVegNonVeg = new String[] { "Veg", "Nonveg" };

	private UIControlConfigurationBuilderHelper uiControlConfigHelper;
	
	public BasicFormMicroApp() {
		super();
		uiControlConfigHelper = new UIControlConfigurationBuilderHelper();
	}
	
	private IUIContainerConfig<?> getAppConfig() {
		RowsColsLayoutConfig.Builder layoutConfig = new RowsColsLayoutConfig.Builder().useRows(true);
		List<IUIComponentReferenceConfig> componentsList = new ArrayList<>();
		
		String ghostText = "Enter Text";

		int labelWidth = 100;
		TextConfig firstName = new TextConfig.Builder().withLabel("First Name").property("labelWidth", labelWidth).withGhostText(ghostText).build();
		componentsList.add(firstName);
		
		TextConfig lastName = new TextConfig.Builder().withLabel("Last Name").property("labelWidth", labelWidth).withGhostText(ghostText).build();
		componentsList.add(lastName);

		RichSelectConfig vegNonVeg = new RichSelectConfig.Builder().withId("staticRichSelectId").withLabel("Meal").withOptions(optionVegNonVeg)
				.withPopupCssClass("select-popup-css").withValue("Male").property("labelWidth", labelWidth).build();
		componentsList.add(vegNonVeg);
		
		RadioConfig staticDataRadioConfig = new RadioConfig.Builder().withLabel("Gender").withOptions(optionMF).withDirection(ELayoutDirection.HORIZONTAL)
				.withValue(optionMF[0]).build();
		componentsList.add(staticDataRadioConfig);
		
		TextAreaConfig about = new TextAreaConfig.Builder()/* .property("value",textArea) */.property("labelWidth", labelWidth).withLabel("About").withGhostText(ghostText)
				.build();
		componentsList.add(about);

		List<IUIComponentReferenceConfig> switchButtonComponentsList = new ArrayList<>();

		SwitchButtonConfig switchConfig = new SwitchButtonConfig.Builder().withId("switchBoxOnOffId").withLabel("Toggle Button ON/OFF Label")
				.property("labelWidth", 250).withOnLabel("ON").withOffLabel("OFF").check(true).build();
		switchButtonComponentsList.add(switchConfig);

		SwitchButtonConfig switchBasicConfig = new SwitchButtonConfig.Builder().withId("switchBoxId").withLabel("Toggle Button")
				.property("labelWidth", 120).check(true).build();
		switchButtonComponentsList.add(switchBasicConfig);

		RowsColsLayoutConfig.Builder rowsColsSwitchButtonComponentLayout = new RowsColsLayoutConfig.Builder().useRows(false);
		rowsColsSwitchButtonComponentLayout.addComponentLayoutProperties(switchConfig);
		rowsColsSwitchButtonComponentLayout.addComponentLayoutProperties(switchBasicConfig);

		UIContainerConfig switchButtonContainerConfig = new UIContainerConfig.Builder().setComponents(switchButtonComponentsList)
				.property("padding", 5).withLayout(rowsColsSwitchButtonComponentLayout.build()).build();
		componentsList.add(switchButtonContainerConfig);

		SegmentedButtonConfig segmentedConfig1 = uiControlConfigHelper.getSegmentedButton("segmentedId", "Segmented:", 180, 40, "no",
				new OptionsData("yes", "Yes"), new OptionsData("no", "No"));
		componentsList.add(segmentedConfig1);

		SegmentedButtonConfig segmentedConfig2 = uiControlConfigHelper.getSegmentedButton("segmentedId2", "Segmented Options:", 180, 80, "option 1",
				optionMF);
		componentsList.add(segmentedConfig2);

		List<IUIComponentReferenceConfig> checkboxComponentsList = new ArrayList<>();

		CheckboxConfig leftCheckboxConfig = uiControlConfigHelper.getCheckbox("leftCheckboxId", "Left Label", 80);
		checkboxComponentsList.add(leftCheckboxConfig);

		CheckboxConfig rightCheckboxConfig = uiControlConfigHelper.getCheckbox("rightCheckboxId", "Right Label", 90);
		checkboxComponentsList.add(rightCheckboxConfig);

		RowsColsLayoutConfig.Builder rowsColsCheckboxComponentLayout = new RowsColsLayoutConfig.Builder().useRows(false);
		rowsColsCheckboxComponentLayout.addComponentLayoutProperties(leftCheckboxConfig);
		rowsColsCheckboxComponentLayout.addComponentLayoutProperties(rightCheckboxConfig);

		UIContainerConfig checkboxContainerConfig = new UIContainerConfig.Builder().setComponents(checkboxComponentsList)
				.withLayout(rowsColsCheckboxComponentLayout.build()).build();

		componentsList.add(checkboxContainerConfig);

		SliderDataConfig sliderData = new SliderDataConfig.Builder().build();
		SliderConfig sliderConfig = new SliderConfig.Builder()
				.withId("slidercomponentmicro")
				.withDataConfig(sliderData).withStep(20)
				.withLabel("Slider").build();
		componentsList.add(sliderConfig);
		
		List<IUIComponentReferenceConfig> buttonList = new ArrayList<>();

		ButtonConfig saveButton = uiControlConfigHelper.getButton("sampleButton", "Save");
		componentsList.add(saveButton);

		/*
		 * ButtonConfig resetButton = uiControlConfigHelper.getButton("sampleButton",
		 * "Sample Button"); buttonList.add(resetButton);
		 */

		RowsColsLayoutConfig.Builder rowsColsSwitchButtonComponentLayoutForButton = new RowsColsLayoutConfig.Builder().useRows(false);
		//rowsColsSwitchButtonComponentLayout.addComponentLayoutProperties(saveButton);
		//rowsColsSwitchButtonComponentLayout.addComponentLayoutProperties(resetButton);

		UIContainerConfig buttonContainerConfig = new UIContainerConfig.Builder().setComponents(buttonList)
				.property("padding", 5).withLayout(rowsColsSwitchButtonComponentLayoutForButton.build()).build();
		componentsList.add(buttonContainerConfig);
		
		layoutConfig.addComponentLayoutProperties(firstName, 0, 38);
		layoutConfig.addComponentLayoutProperties(lastName, 0, 38);
		layoutConfig.addComponentLayoutProperties(vegNonVeg, 0, 38);
		layoutConfig.addComponentLayoutProperties(about, 0, 38);
		
		layoutConfig.addComponentLayoutProperties(segmentedConfig1);
		layoutConfig.addComponentLayoutProperties(segmentedConfig2);
		layoutConfig.addComponentLayoutProperties(checkboxContainerConfig, 0, 38);
		layoutConfig.addComponentLayoutProperties(switchButtonContainerConfig, 0, 38);
		layoutConfig.addComponentLayoutProperties(staticDataRadioConfig);
		layoutConfig.addComponentLayoutProperties(sliderConfig);
		
		layoutConfig.addComponentLayoutProperties(buttonContainerConfig, 0, 38);
		layoutConfig.addComponentLayoutProperties(saveButton, labelWidth, 38);
		
		UIContainerConfig containerConfig = new UIContainerConfig.Builder()
				.setComponents(componentsList)
				.withLayout(layoutConfig.build())
				.enableScroll(Boolean.TRUE).build();

		return containerConfig;
	}

	@Override
	protected List<IApplicationConfig> getApplications() {
		IApplicationConfig[] microApp = new IApplicationConfig[] {
				getApplication(getMicroAppId(), getMicroAppTitle(), getAppConfig()) };
		return Arrays.asList(microApp);
	}

	@Override
	public String getMicroAppId() {
		return MICROFLOW_ID;
	}

	@Override
	public String getMicroAppTitle() {
		return "Form Example";
	}

}
