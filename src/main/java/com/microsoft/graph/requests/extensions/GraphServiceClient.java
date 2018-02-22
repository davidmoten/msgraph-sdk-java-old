// ------------------------------------------------------------------------------
// Copyright (c) Microsoft Corporation.  All Rights Reserved.  Licensed under the MIT License.  See License in the project root for license information.
// ------------------------------------------------------------------------------

package com.microsoft.graph.requests.extensions;

import com.microsoft.graph.concurrency.*;
import com.microsoft.graph.core.*;
import com.microsoft.graph.models.extensions.*;
import com.microsoft.graph.http.*;
import com.microsoft.graph.requests.generated.*;
import com.microsoft.graph.serializer.*;


import com.google.gson.JsonObject;
import com.microsoft.graph.authentication.*;
import com.microsoft.graph.logger.*;

// This file is available for extending, afterwards please submit a pull request.

/**
 * The class for the Graph Service Client.
 */
public class GraphServiceClient extends BaseGraphServiceClient implements IGraphServiceClient {

    /**
     * Restricted constructor
     */
    protected GraphServiceClient() {
    }

    /**
     * Send a custom request to Graph
     * 
     * @param url
     *            The full URL to make a request with
     * @param responseType
     *            The response class to deserialize the response into
     * @return the instance of this builder
     */
    public <T> CustomRequestBuilder<T> customRequest(final String url, final Class<T> responseType) {
        return new CustomRequestBuilder<T>(getServiceRoot() + url, (IGraphServiceClient) this, null, responseType);
    }

    /**
     * end a custom request to Graph
     * 
     * @param url
     *            THe full URL to make a request with
     * @return the instance of this builder
     */
    public CustomRequestBuilder<JsonObject> customRequest(final String url) {
        return new CustomRequestBuilder<JsonObject>(getServiceRoot() + url, (IGraphServiceClient) this, null,
                JsonObject.class);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The builder for this GraphServiceClient
     */
    public static final class Builder {

        ILogger logger;
        ISerializer serializer;
        IHttpProvider httpProvider;
        IAuthenticationProvider authenticationProvider;
        IExecutors executors;
        IClientConfigFactory configFactory;

        Builder() {
            // ensure instantiation only from static factory method
        }

        /**
         * Sets the serializer
         * 
         * @param serializer
         *            The serializer
         * @return the instance of this builder
         */
        public Builder2 serializer(final ISerializer serializer) {
            this.serializer = serializer;
            return new Builder2(this);
        }

        /**
         * Sets the httpProvider
         * 
         * @param httpProvider
         *            The httpProvider
         * @return the instance of this builder
         */
        public Builder2 httpProvider(final IHttpProvider httpProvider) {
            this.httpProvider = httpProvider;
            return new Builder2(this);
        }

        /**
         * Sets the authentication provider
         * 
         * @param authenticationProvider
         *            The authentication provider
         * @return the instance of this builder
         */
        public Builder2 authenticationProvider(final IAuthenticationProvider authenticationProvider) {
            this.authenticationProvider = authenticationProvider;
            return new Builder2(this);
        }

        /**
         * Sets the executors
         * 
         * @param executors
         *            The executors
         * @return the instance of this builder
         */
        public Builder2 executors(final IExecutors executors) {
            this.executors = executors;
            return new Builder2(this);
        }

        /**
         * Sets the logger
         * 
         * @param logger
         *            The logger
         * @return the instance of this builder
         */
        public Builder2 logger(final ILogger logger) {
            this.logger = logger;
            return new Builder2(this);
        }

        /**
         * Set this builder based on the client configuration
         * 
         * @param clientConfig
         *            The client configuration
         * @return the instance of this builder
         */
        public Builder3 fromConfig(final IClientConfig clientConfig) {
            GraphServiceClient client = new GraphServiceClient();
            client.setAuthenticationProvider(clientConfig.getAuthenticationProvider());
            client.setExecutors(clientConfig.getExecutors());
            client.setHttpProvider(clientConfig.getHttpProvider());
            client.setLogger(clientConfig.getLogger());
            client.setSerializer(clientConfig.getSerializer());
            client.validate();
            return new Builder3(client);
        }

        public Builder2 fromConfigFactory(final IClientConfigFactory configFactory) {
            this.configFactory = configFactory;
            return new Builder2(this);
        }

        /**
         * Builds and returns the GraphServiceClient
         * 
         * @throws ClientException
         *             if there was an exception creating the client
         */
        public IGraphServiceClient buildClient() throws ClientException {

            IClientConfigFactory cf = new IClientConfigFactory() {

                @Override
                public IAuthenticationProvider getAuthenticationProvider(IClientConfigFactory factory) {
                    if (Builder.this.authenticationProvider != null) {
                        return Builder.this.authenticationProvider;
                    } else if (factory != null){
                        return factory.getAuthenticationProvider(this);
                    } else {
                        return null;
                    }
                }

                @Override
                public IExecutors getExecutors(IClientConfigFactory factory) {
                    if (Builder.this.executors != null) {
                        return Builder.this.executors;
                    } else if (factory!=null) {
                        return factory.getExecutors(this);
                    } else {
                        return new DefaultExecutors(getLogger(factory));
                    }
                }

                @Override
                public IHttpProvider getHttpProvider(IClientConfigFactory factory) {
                    if (Builder.this.httpProvider != null) {
                        return Builder.this.httpProvider;
                    } else if (factory != null){
                        return factory.getHttpProvider(this);
                    } else {
                        return new DefaultHttpProvider(getSerializer(factory),
                                getAuthenticationProvider(factory),
                                getExecutors(factory),
                                getLogger(factory)); 
                    }
                }

                @Override
                public ILogger getLogger(IClientConfigFactory factory) {
                    if (Builder.this.logger != null) {
                        return Builder.this.logger;
                    } else if (factory != null){
                        return factory.getLogger(this);
                    } else {
                        return new DefaultLogger();
                    }
                }

                @Override
                public ISerializer getSerializer(IClientConfigFactory factory) {
                    if (Builder.this.serializer != null) {
                        return Builder.this.serializer;
                    } else if (factory != null ){
                        return factory.getSerializer(this);
                    } else {
                        return new DefaultSerializer(getLogger(factory));
                    }
                }

            };
            GraphServiceClient client = new GraphServiceClient();
            client.setAuthenticationProvider(cf.getAuthenticationProvider(configFactory));
            client.setExecutors(cf.getExecutors(configFactory));
            client.setHttpProvider(cf.getHttpProvider(configFactory));
            client.setLogger(cf.getLogger(configFactory));
            client.setSerializer(cf.getSerializer(configFactory));
            client.validate();
            return new Builder3(client).buildClient();
        }
    }

    public static final class Builder2 {

        private final Builder b;

        Builder2(Builder b) {
            this.b = b;
        }

        /**
         * Sets the serializer
         * 
         * @param serializer
         *            The serializer
         * @return the instance of this builder
         */
        public Builder2 serializer(final ISerializer serializer) {
            b.serializer = serializer;
            return this;
        }

        /**
         * Sets the httpProvider
         * 
         * @param httpProvider
         *            The httpProvider
         * @return the instance of this builder
         */
        public Builder2 httpProvider(final IHttpProvider httpProvider) {
            b.httpProvider = httpProvider;
            return this;
        }

        /**
         * Sets the authentication provider
         * 
         * @param authenticationProvider
         *            The authentication provider
         * @return the instance of this builder
         */
        public Builder2 authenticationProvider(final IAuthenticationProvider authenticationProvider) {
            b.authenticationProvider = authenticationProvider;
            return this;
        }

        /**
         * Sets the executors
         * 
         * @param executors
         *            The executors
         * @return the instance of this builder
         */
        public Builder2 executors(final IExecutors executors) {
            b.executors = executors;
            return this;
        }

        /**
         * Sets the logger
         * 
         * @param logger
         *            The logger
         * @return the instance of this builder
         */
        public Builder2 logger(final ILogger logger) {
            b.logger = logger;
            return this;
        }

        /**
         * Builds and returns the GraphServiceClient
         * 
         * @throws ClientException
         *             if there was an exception creating the client
         */
        public IGraphServiceClient buildClient() throws ClientException {
            return b.buildClient();
        }
    }

    public static final class Builder3 {

        private final GraphServiceClient client;

        Builder3(GraphServiceClient client) {
            this.client = client;
        }

        /**
         * Builds and returns the GraphServiceClient
         * 
         * @throws ClientException
         *             if there was an exception creating the client
         */
        public IGraphServiceClient buildClient() throws ClientException {
            return client;
        }
    }
}
