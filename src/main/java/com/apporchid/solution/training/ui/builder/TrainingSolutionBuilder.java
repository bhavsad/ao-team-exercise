package com.apporchid.solution.training.ui.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apporchid.config.builder.BaseConfigurationBuilder;
import com.apporchid.core.common.UIDUtils;
import com.apporchid.foundation.ui.config.application.IApplicationReferenceConfig;
import com.apporchid.foundation.ui.config.solution.ISolutionConfig;
import com.apporchid.foundation.ui.config.solution.ISolutionHeaderConfig;
import com.apporchid.foundation.ui.config.solution.ISolutionPageConfig;
import com.apporchid.solution.training.constants.ITrainingPipelineConstants;
import com.apporchid.solution.training.constants.ITrainingSolutionConstants;
import com.apporchid.solution.training.pipeline.builder.ContainerMicroappPipeline;
import com.apporchid.solution.training.pipeline.builder.TrainingPipelineBuilder;
import com.apporchid.solution.training.ui.menu.WQMenu;
import com.apporchid.solution.training.ui.microapp.BasicFormMicroApp;
import com.apporchid.solution.training.ui.microapp.CustomCSSMicroApp;
import com.apporchid.solution.training.ui.microapp.Exercise1MicroApp;
import com.apporchid.solution.training.ui.microapp.Exercise2MicroApp;
import com.apporchid.solution.training.ui.microapp.Exercise3MicroApp;
import com.apporchid.solution.training.ui.microapp.MicroFlowMicroApp;
import com.apporchid.vulcanux.config.builder.BaseSolutionConfigurationBuilder;

@Component
public class TrainingSolutionBuilder extends BaseSolutionConfigurationBuilder implements ITrainingSolutionConstants, ITrainingPipelineConstants {

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
		builders.add(ContainerMicroappPipeline.class);
		
		
		builders.add(CustomCSSMicroApp.class);
		builders.add(BasicFormMicroApp.class);
		builders.add(Exercise1MicroApp.class);
		builders.add(Exercise2MicroApp.class);
		builders.add(Exercise3MicroApp.class);
		builders.add(MicroFlowMicroApp.class);
		
		return builders;
	}

	@Override
	protected ISolutionConfig getSolution() {
		List<ISolutionPageConfig> solutionPages = new ArrayList<>();

		solutionPages.add(trainingPage());
		solutionPages.add(formsPage());
		solutionPages.add(microFlowPage());
		solutionPages.add(customCssPage());
		
		ISolutionHeaderConfig solutionHeaderConfig = getSolutionHeader(SOLUTION_LOGO);

		return createSolution(SOLUTION_ID, SOLUTION_NAME, true, true, solutionPages, solutionHeaderConfig, SOLUTION_ICON);

	}
	
	private ISolutionPageConfig microFlowPage() {
		return getSolutionPageConfig("MicroflowPage", WQMenu.MICRO_FLOW, true, "vuxicon-configuration",
				toApplicationReferences(new String[] {APP_ID_MICRO_FLOW}));
	}

	private ISolutionPageConfig customCssPage() {
		return getSolutionPageConfig("CustomCssPage", "Custom Css Table", true, "vuxicon-controls",
				toApplicationReferences(new String[] {CustomCSSMicroApp.MICROFLOW_ID}));
	}

	private ISolutionPageConfig formsPage() {
		return getSolutionPageConfig("FormPage", WQMenu.FORMS, true, "vuxicon-controls", toApplicationReferences(new String[] {BasicFormMicroApp.MICROFLOW_ID}));
	}

	private ISolutionPageConfig trainingPage() {
		IApplicationReferenceConfig[] iApplicationReferenceConfigs = toApplicationReferences(new String[] { Exercise1MicroApp.MICROFLOW_ID, Exercise2MicroApp.MICROFLOW_ID, Exercise3MicroApp.MICROFLOW_ID });
		return getSolutionPageConfig("TrainingPage", WQMenu.TRAINING, true, "vuxicon-table", iApplicationReferenceConfigs);
	}

	@Override
	protected String[] getSidebarChildIds(String parentId) {
		if (parentId != null) {
			return WQMenu.getMenu(parentId);
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
		return new String[] { "css/appstyles.css" };
	}
	

	@Override
	protected String getSolutionPageCssClass(String solutionPageId) {
		if (solutionPageId.equals("customerSearchresultsPage")) {
			return "ao-vux-search-container";
		}
		return null;
	}
	
	public ISolutionPageConfig getSolutionPageConfig(String id, String displayName, boolean isDeault, boolean visible, String iconUrl, IApplicationReferenceConfig... applicationReferences) {
		return super.getSolutionPageConfig(id, displayName, isDeault, visible, iconUrl, applicationReferences);
	}

}
