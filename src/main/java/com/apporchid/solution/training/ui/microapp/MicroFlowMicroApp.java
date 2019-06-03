package com.apporchid.solution.training.ui.microapp;

import com.apporchid.foundation.common.IJSFunction;
import com.apporchid.foundation.ui.config.microapp.IMicroApplicationConfig;
import com.apporchid.solution.training.pipeline.builder.ContainerMicroappPipeline;
import com.apporchid.solution.training.ui.builder.TrainingAppsBuilder;
import com.apporchid.vulcanux.common.ui.config.UIContainerConfig;
import com.apporchid.vulcanux.ui.config.chart.XYChartConfig;
import com.apporchid.vulcanux.ui.config.chart.data.LineSeriesConfig;
import com.apporchid.vulcanux.ui.config.table.SimpleDataTableConfig;
import com.apporchid.vulcanux.ui.config.table.data.DataTableDataConfig;
import com.apporchid.vulcanux.ui.config.uicontainer.ContainerMicroappConfig;
import com.apporchid.vulcanux.ui.config.uicontainer.ContainerMicroappDataConfig;

public class MicroFlowMicroApp {

	public static IMicroApplicationConfig<?> getMicroFlow(TrainingAppsBuilder appsConfigBuilder) {
		IJSFunction convertDataFn = appsConfigBuilder.getJSFunction("TrainingApiUtils", "convertDataFn");

		ContainerMicroappDataConfig dataConfig = new ContainerMicroappDataConfig.Builder()
				.pipelineId(appsConfigBuilder.getId(ContainerMicroappPipeline.PIPELINE_NAME))
				.convertData(true)
				.withConvertDataJs(convertDataFn)
				.build();

		IMicroApplicationConfig<?> dataTableMicroApp = getDataTableMicroApp("clientsidetableMicroApp");
		IMicroApplicationConfig<?> lineChartMicroApp = getLineChartMicroApp("clientsidechartMicroApp");

		UIContainerConfig uiContainerWithColumns = appsConfigBuilder.uiContainerConfigHelper()
				.getUIContainerWithRows(dataTableMicroApp, lineChartMicroApp);

		ContainerMicroappConfig microappConfig = new ContainerMicroappConfig.Builder()
				.withContainer(uiContainerWithColumns)
				.withDataConfig(dataConfig)
				.build();

		return microappConfig;
	}

	protected static IMicroApplicationConfig<?> getDataTableMicroApp(String id) {
		DataTableDataConfig dataConfig = new DataTableDataConfig.Builder().build();
		SimpleDataTableConfig dataTable = new SimpleDataTableConfig.Builder()
				.withId(id)
				.withDataConfig(dataConfig)
				.build();
		return dataTable;
	}

	protected static IMicroApplicationConfig<?> getLineChartMicroApp(String id) {
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

}
