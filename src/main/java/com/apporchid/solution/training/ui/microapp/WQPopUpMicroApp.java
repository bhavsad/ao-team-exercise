package com.apporchid.solution.training.ui.microapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.foundation.ui.config.IUIComponentReferenceConfig;
import com.apporchid.foundation.ui.config.IUIContainerConfig;
import com.apporchid.foundation.ui.config.application.IApplicationConfig;
import com.apporchid.foundation.ui.config.microapp.IMicroApplicationConfig;
import com.apporchid.solution.training.pipeline.builder.TrainingPipelineBuilder;
import com.apporchid.solution.training.ui.WQBaseAppConfigurationBuilder;
import com.apporchid.vulcanux.common.ui.config.UIContainerConfig;
import com.apporchid.vulcanux.common.ui.config.layout.GridLayoutConfig;
import com.apporchid.vulcanux.ui.config.table.SimpleDataTableConfig;
import com.apporchid.vulcanux.ui.config.table.data.DataTableDataConfig;
import com.apporchid.vulcanux.ui.config.tree.TreeConfig;
import com.apporchid.vulcanux.ui.config.tree.TreeDataConfig;
import com.apporchid.vulcanux.ui.config.uicontrol.ButtonConfig;

@Component
public class WQPopUpMicroApp extends WQBaseAppConfigurationBuilder{

	public static final String MICROFLOW_ID = "WQPopUpMicroApp";
	
	@Override
	public String getMicroAppId() {
		// TODO Auto-generated method stub
		return MICROFLOW_ID;
	}

	@Override
	public String getMicroAppTitle() {
		// TODO Auto-generated method stub
		return "PopUpExample";
	}

	@Override
	protected List<IApplicationConfig> getApplications() {
		IApplicationConfig[] microApp = new IApplicationConfig[] {
				getApplication(getMicroAppId(), getMicroAppTitle(), geControlButtons()) };
		return Arrays.asList(microApp);
	}
	

	private IUIContainerConfig<?> geControlButtons() {
		List<IUIComponentReferenceConfig> componentsList = new ArrayList<>();

		ButtonConfig openAppButton = new ButtonConfig.Builder().withId("openWindowAppId").property("type", "iconButton")
				.property("label", "Open Window").property("tooltip", "Open Window").property("css", "small")
				.property("click", "TrainingApiUtils.openWindowApp").build();
		componentsList.add(openAppButton);
		int noOfColumns = 5;
		int noOfRows = (int) Math.floor(componentsList.size() / noOfColumns);

		GridLayoutConfig gridLayout = getGridLayout(noOfRows + 1, noOfColumns, componentsList.toArray(new IUIComponentReferenceConfig[0]));
		gridLayout.addProperty(GridLayoutConfig.EProperty.cellHeight.name(), 60);

		// container containing the popup buttons
		UIContainerConfig containerConfig = new UIContainerConfig.Builder().setComponents(componentsList).withLayout(gridLayout).build();

		return containerConfig;
	}
	
	/*@Override
	protected List<IApplicationConfig> getApplications() {
		// TODO Auto-generated method stub
		
		
		IApplicationConfig[] microApp = new IApplicationConfig[] {
				getApplication(getMicroAppId(), getMicroAppTitle(), getAppConfig()) };
		return Arrays.asList(microApp);
	}*/
	
	/*private IMicroApplicationConfig<?> getAppConfig() {
		DataTableDataConfig dataConfig = new DataTableDataConfig.Builder()
				.pipelineId(getId(TrainingPipelineBuilder.PIPELINE_ID_EXERCISE1))
				.build();

		SimpleDataTableConfig dataTable = new SimpleDataTableConfig.Builder()
				.withDataConfig(dataConfig)
				.build();

		return dataTable;
		
			ButtonDataConfig openPopupButton = new ButtonDataConfig.Builder().withId("openPopupAppId").property("label", "Open Popup")
					.property("tooltip", "Open Popup").property("css", "small").property("click", "trainingApiUtils.openPopupApp").build();

			TreeConfig treePopup = new TreeConfig.Builder().withId("treePopupFilterId").withDataConfig(treeDataPopupWithFilter).enableFilter(true)
					.onEvent("onItemClick", "samplesPopupUtils.treeItemSelect").hasIcon(true).build();
			return treePopup;
			
	}*/

}
