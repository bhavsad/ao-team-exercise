package com.apporchid.solution.training.ui.microapp;

import com.apporchid.foundation.ui.config.map.IMapLayerConfig;
import com.apporchid.solution.training.constants.ITrainingPipelineConstants;
import com.apporchid.solution.training.ui.builder.TrainingAppsBuilder;
import com.apporchid.vulcanux.ui.config.googlemaps.PointLayerConfig;
import com.apporchid.vulcanux.ui.config.map.data.GeoJsonMapLayerDataConfig;

public class Exercise3MicroApp {

	public static IMapLayerConfig<?> getExercise3App(TrainingAppsBuilder appsConfigBuilder) {
		GeoJsonMapLayerDataConfig mapDataConfig = new GeoJsonMapLayerDataConfig.Builder()
				.pipelineId(appsConfigBuilder.getId(ITrainingPipelineConstants.PIPELINE_ID_EXERCISE3))
				.build();

		// PointLayerConfig mapLayerConfig = new
		// PointLayerConfig.Builder().withDataConfig(mapDataConfig).build();
		PointLayerConfig mapLayerConfig = new PointLayerConfig.Builder()
				.withDataConfig(mapDataConfig)
				.enableClustering(true)
				.build();

		return mapLayerConfig;
	}

}
