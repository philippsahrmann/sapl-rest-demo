package com.sapldemo.restdemo;

import java.io.IOException;

import org.openconjurer.authz.api.interpreter.PolicyEvaluationException;
import org.openconjurer.authz.api.pdp.Request;
import org.openconjurer.authz.api.pdp.Response;
import org.openconjurer.authz.pdp.embedded.CombiningAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PDPRestController {

	@Autowired
	PDPFacade pdpFacade;

	@RequestMapping(value = "/decision", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response decide(
			@RequestBody Request request
	) {
		return pdpFacade.decide(request);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/reload")
	public void reload(
	) {
		try {
			pdpFacade.reload();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PolicyEvaluationException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/combining-algorithm/{algorithm}", method = { RequestMethod.POST })
	public String combiningAlgorithm(
			@PathVariable String algorithm
	) {
		String chosen;
		switch (algorithm) {
			case "deny-overrides":
				pdpFacade.setCombiningAlgorithm(CombiningAlgorithm.DENY_OVERRIDES);
				chosen = "deny-overrides";
				break;
			case "deny-unless-permit":
				pdpFacade.setCombiningAlgorithm(CombiningAlgorithm.DENY_UNLESS_PERMIT);
				chosen = "deny-unless-permit";
				break;
			case "only-one-applicable":
				pdpFacade.setCombiningAlgorithm(CombiningAlgorithm.ONLY_ONE_APPLICABLE);
				chosen = "only-one-applicable";
				break;
			case "permit-overrides":
				pdpFacade.setCombiningAlgorithm(CombiningAlgorithm.PERMIT_OVERRIDES);
				chosen = "permit-overrides";
				break;
			case "permit-unless-deny":
				pdpFacade.setCombiningAlgorithm(CombiningAlgorithm.PERMIT_UNLESS_DENY);
				chosen = "permit-unless-deny";
				break;
			default:
				chosen = "deny-overrides";
				break;
		}
		
		return chosen;
	}

}
