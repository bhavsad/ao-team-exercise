package com.apporchid.solution.training.loader;

import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.apporchid.solution.training.ui.builder.TrainingSolutionBuilder;
import com.apporchid.vulcanux.config.loader.BaseSolutionLoader;

@Profile("!solution-dev")
@Component
@DependsOn(value = { "VuxCacheLoader" })
public class TrainingSolutionLoader extends BaseSolutionLoader<TrainingSolutionBuilder> {
	//TODO:uncomment if you want to provide access to additional roles, by default access is given to admin user (administrator role)
	//private static final String[] DEFAULT_ACCESS_ROLES = new String[] { "administrator", "demo_user" };

	@Override
	protected Class<TrainingSolutionBuilder> getSolutionBuilderType() {
		return TrainingSolutionBuilder.class;
	}

	/**
	 * @return true if you want the solution to be deployed every time you start the server and 
	 * 		   false if the solution needs to be deployed only for the first time when the solution is created.
	 * 		   The common practice is to return true in development mode and false for production
	 */
	@Override
	protected boolean isReloadOnServerStartup() {
		return true;
	}

	/*
	 * uncomment if you want to provide access to additional roles, by default access is given to admin user
	@Override
	protected String[] getAccessRoles() {
		return DEFAULT_ACCESS_ROLES;
	}
	*/
}
