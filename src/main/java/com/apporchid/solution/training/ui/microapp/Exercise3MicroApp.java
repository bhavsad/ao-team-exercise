package com.apporchid.solution.training.ui.microapp;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.foundation.ui.config.application.IApplicationConfig;
import com.apporchid.foundation.ui.config.map.IMapLayerConfig;
import com.apporchid.solution.training.pipeline.builder.TrainingPipelineBuilder;
import com.apporchid.solution.training.ui.WQBaseAppConfigurationBuilder;
import com.apporchid.vulcanux.ui.config.googlemaps.PointLayerConfig;
import com.apporchid.vulcanux.ui.config.map.data.GeoJsonMapLayerDataConfig;

@Component
public class Exercise3MicroApp extends WQBaseAppConfigurationBuilder {

	public static final String MICROFLOW_ID = "Exercise3MicroApp";

	private IMapLayerConfig<?> getAppConfig() {
		GeoJsonMapLayerDataConfig mapDataConfig = new GeoJsonMapLayerDataConfig.Builder()
				.pipelineId(getId(TrainingPipelineBuilder.PIPELINE_ID_EXERCISE3))
				.build();

		// PointLayerConfig mapLayerConfig = new
		// PointLayerConfig.Builder().withDataConfig(mapDataConfig).build();
		PointLayerConfig mapLayerConfig = new PointLayerConfig.Builder()
				.withDataConfig(mapDataConfig)
				.enableClustering(true)
				.build();

		return mapLayerConfig;
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
				getGoogleMapsApplication(getMicroAppId(), getMicroAppTitle(), getAppConfig()) };
		return Arrays.asList(microApp);
	}
}
