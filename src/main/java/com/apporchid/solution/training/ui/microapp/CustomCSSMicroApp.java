package com.apporchid.solution.training.ui.microapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.foundation.ui.config.IUIComponentReferenceConfig;
import com.apporchid.foundation.ui.config.IUIContainerConfig;
import com.apporchid.foundation.ui.config.application.IApplicationConfig;
import com.apporchid.solution.training.pipeline.builder.TrainingPipelineBuilder;
import com.apporchid.solution.training.ui.WQBaseAppConfigurationBuilder;
import com.apporchid.vulcanux.common.ui.config.UIContainerConfig;
import com.apporchid.vulcanux.common.ui.config.layout.RowsColsLayoutConfig;
import com.apporchid.vulcanux.ui.config.table.SimpleDataTableConfig;
import com.apporchid.vulcanux.ui.config.table.data.DataTableDataConfig;

@Component
public class CustomCSSMicroApp extends WQBaseAppConfigurationBuilder {

	public static final String MICROFLOW_ID = "CustomCSSMicroApp";

	private IUIContainerConfig<?> getAppConfig() {
		RowsColsLayoutConfig.Builder rowsColsTablesLayout = new RowsColsLayoutConfig.Builder().useRows(false);
		DataTableDataConfig dataConfig = new DataTableDataConfig.Builder().pipelineId(getId(TrainingPipelineBuilder.PIPELINE_ID_EXERCISE1)).build();
		SimpleDataTableConfig dataTableConfig = new SimpleDataTableConfig.Builder()
				.withTitle("Simple Table")
				.showHeaderMenu(true).showTitle(true)
				.withId("container-table1")
				.enableFilters(true)
				.withDataConfig(dataConfig)
				.build();
		rowsColsTablesLayout.addComponentLayoutProperties(dataTableConfig);
		List<IUIComponentReferenceConfig> componentsList = new ArrayList<>();
		componentsList.add(dataTableConfig);

		UIContainerConfig containerConfig = new UIContainerConfig.Builder()
				.setComponents(componentsList)
				.withLayout(rowsColsTablesLayout.build())
				.enableScroll(Boolean.TRUE).build();

		return containerConfig;
	}
	
	@Override
	protected String getApplicationCss(String appId) {
		if (appId.equals(MICROFLOW_ID))
			return "samples-appstyle-sampletable";
		return super.getApplicationCss(appId);
	}

	@Override
	protected List<IApplicationConfig> getApplications() {
		IApplicationConfig[] microApp = new IApplicationConfig[] {
				getApplication(getMicroAppId(), getMicroAppTitle(), getAppConfig()) };
		return Arrays.asList(microApp);
	}

	@Override
	protected Integer getHeight() {
		return -1;
	}

	@Override
	protected Number getPadding(String appId) {
		return 0;
	}

	@Override
	protected Number getMargin(String appId) {
		return 0;
	}

	@Override
	public String getMicroAppId() {
		return MICROFLOW_ID;
	}

	@Override
	public String getMicroAppTitle() {
		return "Custom CSS Example";
	}

}
