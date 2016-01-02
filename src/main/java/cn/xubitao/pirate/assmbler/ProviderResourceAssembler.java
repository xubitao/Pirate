package cn.xubitao.pirate.assmbler;

import cn.xubitao.dolphin.foundation.assmbler.DolphinAssembler;
import cn.xubitao.dolphin.foundation.resource.RestResource;
import cn.xubitao.pirate.controller.ProvidersController;
import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.resource.ProviderResource;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by xubitao on 12/30/15.
 */
public class ProviderResourceAssembler extends DolphinAssembler {

    public ProviderResourceAssembler() {
        super(ProvidersController.class, RestResource.class);
    }

    public RestResource toRestResource(Object domain) throws Exception {
        Provider provider = (Provider) domain;
        ProviderResource providerResource = new ProviderResource();
        Link providersLink = linkTo(ProvidersController.class).withRel("providers");
        if (domain == null) {
            return RestResource.link(providersLink);
        }
        providerResource.setName(provider.getName());
        providerResource.setVersion(provider.getVersion());
        providerResource.add(linkTo(methodOn(ProvidersController.class).findById(provider.getId())).withSelfRel());

        providerResource.add(providersLink);
        return providerResource;
    }
}
