package org.springframework.cloud.dataflow.metrics.collector.endpoint;


import org.springframework.cloud.dataflow.metrics.collector.model.RootResource;
import org.springframework.cloud.dataflow.metrics.collector.model.StreamMetrics;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vinicius Carvalho
 */
@RestController
@ExposesResourceFor(RootEndpoint.class)
public class RootEndpoint {

	private final EntityLinks entityLinks;

	public RootEndpoint(EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping("/")
	public RootResource info(){
		String streamTemplated = entityLinks.linkToCollectionResource(StreamMetrics.class).getHref() + "?{name}";
		RootResource rootResource = new RootResource();
		rootResource.add(new Link(streamTemplated).withRel("/collector/metrics/streams"));
		return rootResource;
	}
}
