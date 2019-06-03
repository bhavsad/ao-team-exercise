package com.apporchid.solution.training.ui.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.config.builder.BaseConfigurationBuilder;
import com.apporchid.core.common.UIDUtils;
import com.apporchid.foundation.ui.config.solution.ISolutionConfig;
import com.apporchid.foundation.ui.config.solution.ISolutionHeaderConfig;
import com.apporchid.foundation.ui.config.solution.ISolutionPageConfig;
import com.apporchid.solution.training.constants.ITrainingPipelineConstants;
import com.apporchid.solution.training.constants.ITrainingSolutionConstants;
import com.apporchid.solution.training.pipeline.builder.ContainerMicroappPipeline;
import com.apporchid.solution.training.pipeline.builder.TrainingPipelineBuilder;
import com.apporchid.vulcanux.config.builder.BaseSolutionConfigurationBuilder;

@Component
public class TrainingSolutionBuilder extends BaseSolutionConfigurationBuilder implements ITrainingSolutionConstants, ITrainingPipelineConstants {

	public static final String APP_ID_FORM = "forms";
	public static final String APP_ID_MICRO_FLOW = "microFlow";

	public TrainingSolutionBuilder() {
		//call super with the default domain id and sub domain id so that all application that are created in this class are created by default with the default domain id and sub domain id
		super(DEFAULT_DOMAIN_ID, DEFAULT_SUB_DOMAIN_ID);
		setJsNamespace(JS_NAME_SPACE_SEARCH_UTILS);
	}

	@Override
	protected List<Class<? extends BaseConfigurationBuilder<?>>> getDependentConfigBuilders() {
		List<Class<? extends BaseConfigurationBuilder<?>>> builders = new ArrayList<>();

		builders.add(TrainingPipelineBuilder.class);
		builders.add(TrainingAppsBuilder.class);
		builders.add(ContainerMicroappPipeline.class);

		return builders;
	}

	@Override
	protected ISolutionConfig getSolution() {
		List<ISolutionPageConfig> solutionPages = new ArrayList<>();

		ISolutionPageConfig trainingPage = getSolutionPageConfig("trainingPage", "Training", true, "vuxicon-table",
				toApplicationReferences(new String[] {APP_ID_EXCERCISE1,APP_ID_EXCERCISE2,APP_ID_EXCERCISE3}));
		solutionPages.add(trainingPage);
		
		
		ISolutionPageConfig forms = getSolutionPageConfig("formPage", "Forms", true, "vuxicon-controls",
				toApplicationReferences(new String[] {APP_ID_FORM}));
		solutionPages.add(forms);
		
		ISolutionPageConfig microApps = getSolutionPageConfig("microflowPage", "Micro Flow", true, "vuxicon-configuration",
				toApplicationReferences(new String[] {APP_ID_MICRO_FLOW}));
		solutionPages.add(microApps);

		ISolutionHeaderConfig solutionHeaderConfig = getSolutionHeader(SOLUTION_LOGO);

		return createSolution(SOLUTION_ID, SOLUTION_NAME, true, true, solutionPages, solutionHeaderConfig, SOLUTION_ICON);

	}
	
	@Override
	protected String[] getSidebarChildIds(String parentId) {
		if (parentId != null) {
			switch (parentId) {
			case "":
				return new String[] { "Training", "Forms", "Micro Flow"};
			case "Training":
				return new String[] {  "trainingPage" };
			case "Forms":
				return new String[] { "formPage"};
			case "Micro Flow":
				return new String[] { "microflowPage"};
			default:
				break;
			}
		}
		return super.getSidebarChildIds(parentId);
	}
	
	@Override
	protected String getSidebarIcon(String id) {
		if (id != null) {
			switch (id) {
			case "Training":
				return "vuxicon-table";
			case "Forms":
				return "vuxicon-bar-chart";
			case "Micro Flow":
				return "vuxicon-area-chart";
			default:
				break;
			}

		}
		return super.getSidebarIcon(id);
	}
	
	@Override
	public String getSolutionId() {
		return UIDUtils.getUID(domainId, subDomainId, SOLUTION_ID);
	}

	@Override
	 protected String[] getJsFiles() {
        return new String[] {"js/TrainingApiUtils.js"};
    }
	
	@Override
	protected String[] getCssFiles() {
		return null;
	}

	@Override
	protected String getSolutionPageCssClass(String solutionPageId) {
		if (solutionPageId.equals("customerSearchresultsPage")) {
			return "ao-vux-search-container";
		}
		return null;
	}

}
