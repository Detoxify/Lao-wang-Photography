package com.photo.album.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by nathaniel on 16/8/17.
 */
public class CustomerRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {

        return CustomerContextHolder.getCustomerType();
    }
}
