package com.microsoft.graph.requests.extensions;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.concurrency.IExecutors;
import com.microsoft.graph.http.IHttpProvider;
import com.microsoft.graph.logger.ILogger;
import com.microsoft.graph.serializer.ISerializer;

public interface IClientConfigFactory {

    /**
     * Gets the authentication provider.
     * 
     * @param factory
     *            gives access to the other client config aspects.
     * @return The authentication provider.
     */
    IAuthenticationProvider getAuthenticationProvider(IClientConfigFactory factory);

    /**
     * Gets the executors.
     * 
     * @param factory
     *            gives access to the other client config aspects.
     * @return The executors.
     */
    IExecutors getExecutors(IClientConfigFactory factory);

    /**
     * Gets the HTTP provider.
     * 
     * @param factory
     *            gives access to the other client config aspects.
     * @return The HTTP provider.
     */
    IHttpProvider getHttpProvider(IClientConfigFactory factory);

    /**
     * Gets the logger.
     * 
     * @param factory
     *            gives access to the other client config aspects.
     * @return The logger.
     */
    ILogger getLogger(IClientConfigFactory factory);

    /**
     * Gets the serializer.
     * 
     * @param factory
     *            gives access to the other client config aspects.
     * @return The serializer.
     */
    ISerializer getSerializer(IClientConfigFactory factory);

}
