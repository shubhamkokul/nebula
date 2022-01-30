package com.rdx.factory.nebula.service;

@FunctionalInterface
public interface NebulaAPIConnector {

    public String getRequest(NebulaAPIParameter... apiParameters);
}
