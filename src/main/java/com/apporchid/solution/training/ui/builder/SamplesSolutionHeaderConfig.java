package com.apporchid.solution.training.ui.builder;

import com.apporchid.vulcanux.ui.config.solution.BaseSolutionHeaderConfig;

public class SamplesSolutionHeaderConfig extends BaseSolutionHeaderConfig {

	/**
	 * TODO Temp header class to test custom header
	 */
	private static final long serialVersionUID = 1L;

	public SamplesSolutionHeaderConfig() {
		super();
	}
	
	public static final class Builder extends BaseSolutionHeaderConfig.Builder<SamplesSolutionHeaderConfig, Builder> {

		@Override
		protected SamplesSolutionHeaderConfig create() {
			return new SamplesSolutionHeaderConfig();
		}

		@Override
		protected Builder getBuilder() {
			return this;
		}

	}
}
