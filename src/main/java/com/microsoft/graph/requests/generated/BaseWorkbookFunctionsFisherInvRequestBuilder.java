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
 * The class for the Base Workbook Functions Fisher Inv Request Builder.
 */
public class BaseWorkbookFunctionsFisherInvRequestBuilder extends BaseActionRequestBuilder {

    /**
     * The request builder for this WorkbookFunctionsFisherInv
     *
     * @param requestUrl     the request URL
     * @param client         the service client
     * @param requestOptions the options for this request
     * @param y the y
     */
    public BaseWorkbookFunctionsFisherInvRequestBuilder(final String requestUrl, final IBaseClient client, final java.util.List<? extends Option> requestOptions, final com.google.gson.JsonElement y) {
        super(requestUrl, client, requestOptions);
        bodyParams.put("y", y);
    }

    /**
     * Creates the IWorkbookFunctionsFisherInvRequest
     *
     * @return the IWorkbookFunctionsFisherInvRequest instance
     */
    public IWorkbookFunctionsFisherInvRequest buildRequest() {
        return buildRequest(getOptions());
    }

    /**
     * Creates the IWorkbookFunctionsFisherInvRequest with specific requestOptions instead of the existing requestOptions
     *
     * @param requestOptions the options for the request
     * @return the IWorkbookFunctionsFisherInvRequest instance
     */
    public IWorkbookFunctionsFisherInvRequest buildRequest(final java.util.List<? extends Option> requestOptions) {
        WorkbookFunctionsFisherInvRequest request = new WorkbookFunctionsFisherInvRequest(
                getRequestUrl(),
                getClient(),
                requestOptions
        );

        if (hasParameter("y")) {
            request.body.y = getParameter("y");
        }

        return request;
    }
}
