package com.microsoft.graph.requests.extensions;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.concurrency.IExecutors;
import com.microsoft.graph.http.IHttpProvider;
import com.microsoft.graph.logger.ILogger;
import com.microsoft.graph.serializer.ISerializer;

public interface IClientConfigFactory {
    
    /**
     * Gets the authentication provider.
     * @return The authentication provider.
     */
    IAuthenticationProvider getAuthenticationProvider(IClientConfigFactory factory);

    /**
     * Gets the executors.
     * @return The executors.
     */
    IExecutors getExecutors(IClientConfigFactory factory);

    /**
     * Gets the HTTP provider.
     * @return The HTTP provider.
     */
    IHttpProvider getHttpProvider(IClientConfigFactory factory);

    /**
     * Gets the logger.
     * @return The logger.
     */
    ILogger getLogger(IClientConfigFactory factory);

    /**
     * Gets the serializer.
     * @return The serializer.
     */
    ISerializer getSerializer(IClientConfigFactory factory);

}
