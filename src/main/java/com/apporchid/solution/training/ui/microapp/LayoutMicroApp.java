package com.apporchid.solution.training.ui.microapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import com.apporchid.foundation.ui.config.IUIComponentReferenceConfig;
import com.apporchid.foundation.ui.config.application.IApplicationConfig;
import com.apporchid.foundation.ui.config.microapp.IMicroApplicationConfig;
import com.apporchid.solution.training.constants.ITrainingPipelineConstants;
import com.apporchid.solution.training.constants.ITrainingSolutionConstants;
import com.apporchid.solution.training.pipeline.builder.LayoutMicroappPipeline;
import com.apporchid.vulcanux.common.ui.config.UIContainerConfig;
import com.apporchid.vulcanux.common.ui.config.layout.RowsColsLayoutConfig;
import com.apporchid.vulcanux.config.builder.BaseAppConfigurationBuilder;
import com.apporchid.vulcanux.ui.config.chart.XYChartConfig;
import com.apporchid.vulcanux.ui.config.chart.data.LineSeriesConfig;
import com.apporchid.vulcanux.ui.config.microapp.controls.SelectConfig;
import com.apporchid.vulcanux.ui.config.table.SimpleDataTableConfig;
import com.apporchid.vulcanux.ui.config.table.data.DataTableDataConfig;

@Component
public class LayoutMicroApp extends BaseAppConfigurationBuilder implements ITrainingSolutionConstants, ITrainingPipelineConstants {
	
	
	public static final String APP_ID_ROWCOLLAYOUT="RowcolumnLayoutApp";

	public LayoutMicroApp() {
		//call super with the default domain id and sub domain id so that all application that are created in this class
		//are created by default with the default domain id and sub domain id
		super(DEFAULT_DOMAIN_ID, DEFAULT_SUB_DOMAIN_ID);
	}
	
	


	 protected UIContainerConfig getRowsColumnLayoutContainer() {

	 		List<IUIComponentReferenceConfig> componentsList = new ArrayList<>();
	                
	 		        //first row
	 				String[] optionArr = new String[] { "option 1", "option 2", "option 3" };
	 				
	 				SelectConfig selectWithNoLabel1Config = new SelectConfig.Builder().withId("staticSelectNoLabelId").withOptions(optionArr).build();
	 				componentsList.add(selectWithNoLabel1Config);
	 				
	 				RowsColsLayoutConfig.Builder rowsColsLayout = new RowsColsLayoutConfig.Builder().useRows(false);
	 				rowsColsLayout.addComponentLayoutProperties(componentsList.get(0), 150, 50, 1);
	 				
	 				UIContainerConfig containerConfig = new UIContainerConfig.Builder().setComponents(componentsList).withLayout(rowsColsLayout.build()).build();
	 				
	 				//second row
	 				List<IUIComponentReferenceConfig> tablemicroAppList = new ArrayList<>();
	 				
	 				RowsColsLayoutConfig.Builder rowsColsTablesLayout = new RowsColsLayoutConfig.Builder();
	 				
	 				IMicroApplicationConfig<?> dataTableMicroApp = getDataTableApp();
	 				IMicroApplicationConfig<?> chartMicroapp = getXYChartApp();
	 				tablemicroAppList.add(dataTableMicroApp);
	 				tablemicroAppList.add(chartMicroapp);
	 				rowsColsTablesLayout.addComponentLayoutProperties(dataTableMicroApp);
	 				rowsColsTablesLayout.addComponentLayoutProperties(chartMicroapp);

	 				UIContainerConfig tableContainerConfig = new UIContainerConfig.Builder().setComponents(tablemicroAppList).withId("tableContainer")
	 								.withLayout(rowsColsTablesLayout.build()).build();
	 				
	 				// parent container
	 				RowsColsLayoutConfig.Builder layoutContainer = new RowsColsLayoutConfig.Builder().useRows(true);

	 				layoutContainer.addComponentLayoutProperties(containerConfig, 0, 50, 1);
	                 layoutContainer.addComponentLayoutProperties(tableContainerConfig, 0, 500, 1);
	                 
	 				UIContainerConfig uicontainerControls = new UIContainerConfig.Builder().withId("uicontainer").addComponent(containerConfig).enableScroll(true)
	 				.addComponent(tableContainerConfig) .withLayout(layoutContainer.build()).build();
	 				
	 				return uicontainerControls;
	 				}

	 	
	 	//table data
	 	protected IMicroApplicationConfig<?> getDataTableApp() {

	 		DataTableDataConfig dataConfig = new DataTableDataConfig.Builder().pipelineId(getId(LayoutMicroappPipeline.PIPELINE_ID_TABLEANDCHART_CONTAINER)).build();
	 				SimpleDataTableConfig dataTable = new SimpleDataTableConfig.Builder().withTitle("Simple Table").withId("tableId").withDataConfig(dataConfig)
	 						.build();

	 				return dataTable;
	 		}
	 	protected IMicroApplicationConfig<?> getXYChartApp() {
	 		return getXYChartApp(LayoutMicroappPipeline.PIPELINE_ID_TABLEANDCHART_CONTAINER);
	 	}
	 	
	    //XY chart
	    protected IMicroApplicationConfig<?> getXYChartApp(String pipelineId) {

	    LineSeriesConfig seriesConfig = new LineSeriesConfig.Builder().pipelineId(getId(pipelineId))
	 				.xAxisField("customerId")
	 				.seriesField("value")
	 				.build();
	 		XYChartConfig chart = new XYChartConfig.Builder().withDataConfig(seriesConfig).showTitle(false).withTitle("XY Chart")
	 				.build();
	 		return chart;
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
	protected List<IApplicationConfig> getApplications() {
		IApplicationConfig[] selectControlsApplications = new IApplicationConfig[] {
				getApplication(APP_ID_ROWCOLLAYOUT, "Rows Cols Layout",
				getRowsColumnLayoutContainer())
			};
			return Arrays.asList(selectControlsApplications);
	}

}
