// ------------------------------------------------------------------------------
// Copyright (c) Microsoft Corporation.  All Rights Reserved.  Licensed under the MIT License.  See License in the project root for license information.
// ------------------------------------------------------------------------------

package com.microsoft.graph.requests.generated;

import com.microsoft.graph.concurrency.*;
import com.microsoft.graph.core.*;
import com.microsoft.graph.models.extensions.*;
import com.microsoft.graph.models.generated.*;
import com.microsoft.graph.http.*;
import com.microsoft.graph.requests.extensions.*;
import com.microsoft.graph.requests.generated.*;
import com.microsoft.graph.options.*;
import com.microsoft.graph.serializer.*;

import java.util.Arrays;
import java.util.EnumSet;
// **NOTE** This file was generated by a tool and any changes will be overwritten.

/**
 * The class for the Base Drive Item Create Link Request Builder.
 */
public class BaseDriveItemCreateLinkRequestBuilder extends BaseActionRequestBuilder {

    /**
     * The request builder for this DriveItemCreateLink
     *
     * @param requestUrl     the request URL
     * @param client         the service client
     * @param requestOptions the options for this request
     * @param type the type
     * @param scope the scope
     */
    public BaseDriveItemCreateLinkRequestBuilder(final String requestUrl, final IBaseClient client, final java.util.List<? extends Option> requestOptions, final String type, final String scope) {
        super(requestUrl, client, requestOptions);
        bodyParams.put("type", type);
        bodyParams.put("scope", scope);
    }

    /**
     * Creates the IDriveItemCreateLinkRequest
     *
     * @return the IDriveItemCreateLinkRequest instance
     */
    public IDriveItemCreateLinkRequest buildRequest() {
        return buildRequest(getOptions());
    }

    /**
     * Creates the IDriveItemCreateLinkRequest with specific requestOptions instead of the existing requestOptions
     *
     * @param requestOptions the options for the request
     * @return the IDriveItemCreateLinkRequest instance
     */
    public IDriveItemCreateLinkRequest buildRequest(final java.util.List<? extends Option> requestOptions) {
        DriveItemCreateLinkRequest request = new DriveItemCreateLinkRequest(
                getRequestUrl(),
                getClient(),
                requestOptions
        );

        if (hasParameter("type")) {
            request.body.type = getParameter("type");
        }

        if (hasParameter("scope")) {
            request.body.scope = getParameter("scope");
        }

        return request;
    }
}
