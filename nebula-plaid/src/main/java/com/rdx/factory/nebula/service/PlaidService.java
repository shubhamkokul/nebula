package com.rdx.factory.nebula.service;

import com.plaid.client.model.Institution;
import com.plaid.client.model.LinkTokenGetResponse;

import java.util.List;

public interface PlaidService {
    List<Institution> getInstitutionList();

    String createLinkToken(String clientUserId);

    LinkTokenGetResponse getLinkToken(String linkToken);

    String createAccessToken(String linkToken);
}
