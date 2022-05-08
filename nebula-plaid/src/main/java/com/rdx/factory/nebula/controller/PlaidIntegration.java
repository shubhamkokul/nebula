package com.rdx.factory.nebula.controller;

import com.plaid.client.model.Institution;
import com.plaid.client.model.LinkTokenGetResponse;
import com.rdx.factory.nebula.service.PlaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaidIntegration {

    private final PlaidService nebulaPlaidService;

    @Autowired
    public PlaidIntegration(PlaidService nebulaPlaidService) {
        this.nebulaPlaidService = nebulaPlaidService;
    }

    @GetMapping(value = "/plaid/institutions/")
    public List<Institution> getInstitutions() {
        return nebulaPlaidService.getInstitutionList();
    }

    //link-development-f7b41e75-527e-46bd-80f0-4b52eed59f51
    @GetMapping(value = "/plaid/create/link/token/{clientUserId}")
    public String getCreateLinkToken(@PathVariable String clientUserId) {
        return nebulaPlaidService.createLinkToken(clientUserId);
    }

    @GetMapping(value = "/plaid/link/token/{linkToken}")
    public LinkTokenGetResponse getLinkToken(@PathVariable String linkToken) {
        return nebulaPlaidService.getLinkToken(linkToken);
    }

    @GetMapping(value = "/plaid/create/access/token/{linkToken}")
    public String getCreateAccessToken(@PathVariable String linkToken) {
        return nebulaPlaidService.createAccessToken(linkToken);
    }
}
