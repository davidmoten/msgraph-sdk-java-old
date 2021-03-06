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

import com.google.gson.JsonObject;
import com.google.gson.annotations.*;

// **NOTE** This file was generated by a tool and any changes will be overwritten.

/**
 * The interface for the Base Workbook Range View Item At Request.
 */
public interface IBaseWorkbookRangeViewItemAtRequest {

    /**
     * Patches the WorkbookRangeViewItemAt
     *
     * @param srcWorkbookRangeView the WorkbookRangeView with which to PATCH
     * @param callback the callback to be called after success or failure
     */
    void patch(WorkbookRangeView srcWorkbookRangeView, final ICallback<WorkbookRangeView> callback);

    /**
     * Patches the WorkbookRangeViewItemAt
     *
     * @param srcWorkbookRangeView the WorkbookRangeView with which to PATCH
     * @return the WorkbookRangeView
     * @throws ClientException an exception occurs if there was an error while the request was sent
     */
    WorkbookRangeView patch(WorkbookRangeView srcWorkbookRangeView) throws ClientException;

    /**
     * Puts the WorkbookRangeViewItemAt
     *
     * @param srcWorkbookRangeView the WorkbookRangeView to PUT
     * @param callback the callback to be called after success or failure
     */
    void put(WorkbookRangeView srcWorkbookRangeView, final ICallback<WorkbookRangeView> callback);

    /**
     * Puts the WorkbookRangeViewItemAt
     *
     * @param srcWorkbookRangeView the WorkbookRangeView to PUT
     * @return the WorkbookRangeView
     * @throws ClientException an exception occurs if there was an error while the request was sent
     */
     WorkbookRangeView put(WorkbookRangeView srcWorkbookRangeView) throws ClientException;
    /**
     * Gets the WorkbookRangeView
     *
     * @param callback the callback to be called after success or failure
     */
    void get(final ICallback<WorkbookRangeView> callback);

    /**
     * Gets the WorkbookRangeView
     *
     * @return the WorkbookRangeView
     * @throws ClientException an exception occurs if there was an error while the request was sent
     */
    WorkbookRangeView get() throws ClientException;

    /**
     * Sets the select clause for the request
     *
     * @param value the select clause
     * @return the updated request
     */
    IWorkbookRangeViewItemAtRequest select(final String value);

    /**
     * Sets the expand clause for the request
     *
     * @param value the expand clause
     * @return the updated request
     */
    IWorkbookRangeViewItemAtRequest expand(final String value);

}
