package com.rdx.factory.nebula.service.impl;

import com.plaid.client.model.*;
import com.plaid.client.request.PlaidApi;
import com.rdx.factory.nebula.exception.NebulaRequestException;
import com.rdx.factory.nebula.service.PlaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Service
public class PlaidServiceImpl implements PlaidService {

    private final PlaidApi plaidApi;

    @Autowired
    public PlaidServiceImpl(PlaidApi plaidApi) {
        this.plaidApi = plaidApi;
    }

    @Override
    public List<Institution> getInstitutionList() {
        InstitutionsGetRequest request = new InstitutionsGetRequest()
                .count(500)
                .offset(0)
                .countryCodes(List.of(CountryCode.US));
        try {
            Response<InstitutionsGetResponse> response = plaidApi
                    .institutionsGet(request)
                    .execute();
            assert response.body() != null;
            return response.body().getInstitutions();
        } catch (IOException e) {
            throw new NebulaRequestException("Error in GetInstitutional Request", e);
        }
    }

    @Override
    public String createLinkToken(String clientUserId) {
        try {
            LinkTokenCreateRequestUser user = new LinkTokenCreateRequestUser()
                    .clientUserId(clientUserId);
            DepositoryFilter types = new DepositoryFilter()
                    .accountSubtypes(List.of(AccountSubtype.CHECKING));
            LinkTokenAccountFilters accountFilters = new LinkTokenAccountFilters()
                    .depository(types);
            LinkTokenCreateRequest request = new LinkTokenCreateRequest()
                    .user(user)
                    .clientName("Castiel Personal App")
                    .products(List.of(Products.TRANSACTIONS))
                    .countryCodes(List.of(CountryCode.US))
                    .language("en")
                    .webhook("https://requestb.in")
                    .accountFilters(accountFilters);
            Response<LinkTokenCreateResponse> response = plaidApi
                    .linkTokenCreate(request)
                    .execute();
            assert response.body() != null;
            return response.body().getLinkToken();
        } catch (IOException e) {
            throw new NebulaRequestException("Error in CreateTempToken Request", e);
        }
    }

    @Override
    public LinkTokenGetResponse getLinkToken(String linkToken) {
        try {
            LinkTokenGetRequest request = new LinkTokenGetRequest()
                    .linkToken(linkToken);
            Response<LinkTokenGetResponse> response = plaidApi
                    .linkTokenGet(request)
                    .execute();
            return response.body();
        } catch (IOException e) {
            throw new NebulaRequestException("Error in GetTempToken Request", e);
        }
    }

    @Override
    public String createAccessToken(String publicToken) {
        try {
            ItemPublicTokenExchangeRequest request = new ItemPublicTokenExchangeRequest()
                    .publicToken(publicToken);
            Response<ItemPublicTokenExchangeResponse> response = plaidApi
                    .itemPublicTokenExchange(request)
                    .execute();
            assert response.body() != null;
            return response.body().getAccessToken();
        } catch (IOException e) {
            throw new NebulaRequestException("Error in CreateAccessToken Request", e);
        }
    }

}
