package com.sapldemo.restdemo;

import java.io.IOException;

import org.openconjurer.authz.api.interpreter.PolicyEvaluationException;
import org.openconjurer.authz.api.pdp.PolicyDecisionPoint;
import org.openconjurer.authz.api.pdp.Request;
import org.openconjurer.authz.api.pdp.Response;
import org.openconjurer.authz.pdp.embedded.CombiningAlgorithm;
import org.openconjurer.authz.pdp.embedded.EmbeddedPolicyDecisionPoint;

public class PDPFacade {
	PolicyDecisionPoint pdp;
	CombiningAlgorithm combiningAlgorithm;
	String path;

	public PDPFacade(
			String path,
			CombiningAlgorithm combiningAlgorithm
	) throws IOException, PolicyEvaluationException {
		this.path = path;
		this.combiningAlgorithm = combiningAlgorithm;
		reload();
	}

	public void reload(
	) throws IOException, PolicyEvaluationException {
		pdp = new EmbeddedPolicyDecisionPoint(path, combiningAlgorithm);
	}

	public void setCombiningAlgorithm(
			CombiningAlgorithm combiningAlgorithm
	) {
		this.combiningAlgorithm = combiningAlgorithm;
	}

	public Response decide(
			Request request
	) {
		return pdp.decide(request);
	}
}
