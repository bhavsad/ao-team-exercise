package com.apporchid.solution.training.ui.microapp;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.foundation.common.IJSFunction;
import com.apporchid.foundation.ui.config.application.IApplicationConfig;
import com.apporchid.foundation.ui.config.microapp.IMicroApplicationConfig;
import com.apporchid.solution.training.pipeline.builder.ContainerMicroappPipeline;
import com.apporchid.solution.training.ui.WQBaseAppConfigurationBuilder;
import com.apporchid.vulcanux.common.ui.config.UIContainerConfig;
import com.apporchid.vulcanux.ui.config.chart.XYChartConfig;
import com.apporchid.vulcanux.ui.config.chart.data.LineSeriesConfig;
import com.apporchid.vulcanux.ui.config.table.SimpleDataTableConfig;
import com.apporchid.vulcanux.ui.config.table.data.DataTableDataConfig;
import com.apporchid.vulcanux.ui.config.uicontainer.ContainerMicroappConfig;
import com.apporchid.vulcanux.ui.config.uicontainer.ContainerMicroappDataConfig;

@Component
public class MicroFlowMicroApp  extends WQBaseAppConfigurationBuilder {
	
	public static final String MICROFLOW_ID = "MicroFlowMicroApp";
	
	private IMicroApplicationConfig<?> getAppConfig() {
		IJSFunction convertDataFn = getJSFunction("TrainingApiUtils", "convertDataFn");

		ContainerMicroappDataConfig dataConfig = new ContainerMicroappDataConfig.Builder()
				.pipelineId(getId(ContainerMicroappPipeline.PIPELINE_NAME))
				.convertData(true)
				.withConvertDataJs(convertDataFn)
				.build();

		IMicroApplicationConfig<?> dataTableMicroApp = getDataTableMicroApp("clientsidetableMicroApp");
		IMicroApplicationConfig<?> lineChartMicroApp = getLineChartMicroApp("clientsidechartMicroApp");

		UIContainerConfig uiContainerWithColumns = uiContainerConfigHelper()
				.getUIContainerWithRows(dataTableMicroApp, lineChartMicroApp);

		ContainerMicroappConfig microappConfig = new ContainerMicroappConfig.Builder()
				.withContainer(uiContainerWithColumns)
				.withDataConfig(dataConfig)
				.build();

		return microappConfig;
	}

	protected IMicroApplicationConfig<?> getDataTableMicroApp(String id) {
		DataTableDataConfig dataConfig = new DataTableDataConfig.Builder().build();
		SimpleDataTableConfig dataTable = new SimpleDataTableConfig.Builder()
				.withId(id)
				.withDataConfig(dataConfig)
				.build();
		return dataTable;
	}

	protected IMicroApplicationConfig<?> getLineChartMicroApp(String id) {
		LineSeriesConfig seriesConfig = new LineSeriesConfig.Builder()
				.xAxisField("customerId")
				.seriesField("value")
				.build();

		XYChartConfig chart = new XYChartConfig.Builder()
				.withId(id)
				.withDataConfig(seriesConfig)
				.build();
		return chart;
	}
	
	@Override
	public String getMicroAppId() {
		return MICROFLOW_ID;
	}

	@Override
	public String getMicroAppTitle() {
		return "Exercise 3 MicroApp";
	}

	@Override
	protected List<IApplicationConfig> getApplications() {
		IApplicationConfig[] microApp = new IApplicationConfig[] {
				getApplication(getMicroAppId(), getMicroAppTitle(), getAppConfig()) };
		return Arrays.asList(microApp);
	}

}
