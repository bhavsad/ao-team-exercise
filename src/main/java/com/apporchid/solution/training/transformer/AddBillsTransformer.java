package com.apporchid.solution.training.transformer;

import org.apache.commons.lang3.RandomUtils;

import com.apporchid.cloudseer.common.transformer.BaseTransformer;
import com.apporchid.cloudseer.common.transformer.properties.TransformerProperties;
import com.apporchid.foundation.mso.IMSODataObject;

public class AddBillsTransformer extends BaseTransformer<TransformerProperties> {

	private static final long serialVersionUID = -1652251987740484349L;

	public AddBillsTransformer(TransformerProperties transformerProperties) {
		super(transformerProperties);
	}

	@Override
	protected Object transform(IMSODataObject msoDataObject) {
		double[] bills = getLast12MonthsBills(msoDataObject.getFieldValue("businessPartnerNumber", String.class));
		msoDataObject.setRuntimeFieldValue("bills", bills);
		return msoDataObject;
	}

	private double[] getLast12MonthsBills(String businessPartnerNumber) {
		// Adding 12 random values to bills, you can actually get the bills for the
		// customers for last 6 months and set here
		double[] bills = new double[12];
		for (int i = 0; i < bills.length; i++) {
			bills[i] = RandomUtils.nextDouble(30, 500);
		}
		return bills;
	}
}
