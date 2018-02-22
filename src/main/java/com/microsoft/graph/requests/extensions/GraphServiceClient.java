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

        IClientConfigProvider<? extends ILogger> loggerProvider;
        IClientConfigProvider<? extends ISerializer> serializerProvider;
        IClientConfigProvider<? extends IHttpProvider> httpProviderProvider;
        IClientConfigProvider<? extends IAuthenticationProvider> authenticationProviderProvider;
        IClientConfigProvider<? extends IExecutors> executorsProvider;
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
            return serializer(ConstantProvider.create(serializer));
        }
        
        /**
         * Sets the serializer provider
         * 
         * @param serializer
         *            The serializer
         * @return the instance of this builder
         */
        public Builder2 serializer(final IClientConfigProvider<? extends ISerializer> serializerProvider) {
            this.serializerProvider = serializerProvider;
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
            return httpProvider(ConstantProvider.create(httpProvider));
        }
        
        /**
         * Sets the httpProvider provider
         * 
         * @param httpProvider
         *            The httpProvider
         * @return the instance of this builder
         */
        public Builder2 httpProvider(final IClientConfigProvider<? extends IHttpProvider> httpProviderProvider) {
            this.httpProviderProvider =httpProviderProvider;
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
            return authenticationProvider(ConstantProvider.create(authenticationProvider));
        }
        
        /**
         * Sets the authentication provider
         * 
         * @param authenticationProvider
         *            The authentication provider
         * @return the instance of this builder
         */
        public Builder2 authenticationProvider(final IClientConfigProvider<IAuthenticationProvider> authenticationProviderProvider) {
            this.authenticationProviderProvider = authenticationProviderProvider;
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
            return executors(ConstantProvider.create(executors));
        }
        
        /**
         * Sets the executors
         * 
         * @param executors
         *            The executors
         * @return the instance of this builder
         */
        public Builder2 executors(final IClientConfigProvider<IExecutors> executorsProvider) {
            this.executorsProvider = executorsProvider;
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
            return logger(ConstantProvider.create(logger));
        }
        
        /**
         * Sets the logger provider
         * 
         * @param loggerProvider
         *            The logger provider
         * @return the instance of this builder
         */
        public Builder2 logger(final IClientConfigProvider<ILogger> loggerProvider) {
            this.loggerProvider = loggerProvider;
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
                    if (Builder.this.authenticationProviderProvider != null) {
                        return Builder.this.authenticationProviderProvider.get(this);
                    } else if (factory != null){
                        return factory.getAuthenticationProvider(this);
                    } else {
                        return null;
                    }
                }

                @Override
                public IExecutors getExecutors(IClientConfigFactory factory) {
                    if (Builder.this.executorsProvider != null) {
                        return Builder.this.executorsProvider.get(this);
                    } else if (factory!=null) {
                        return factory.getExecutors(this);
                    } else {
                        return new DefaultExecutors(getLogger(this));
                    }
                }

                @Override
                public IHttpProvider getHttpProvider(IClientConfigFactory factory) {
                    if (Builder.this.httpProviderProvider != null) {
                        return Builder.this.httpProviderProvider.get(this);
                    } else if (factory != null){
                        return factory.getHttpProvider(this);
                    } else {
                        return new DefaultHttpProvider(getSerializer(this),
                                getAuthenticationProvider(this),
                                getExecutors(this),
                                getLogger(this)); 
                    }
                }

                @Override
                public ILogger getLogger(IClientConfigFactory factory) {
                    if (Builder.this.loggerProvider != null) {
                        return Builder.this.loggerProvider.get(this);
                    } else if (factory != null){
                        return factory.getLogger(this);
                    } else {
                        return new DefaultLogger();
                    }
                }

                @Override
                public ISerializer getSerializer(IClientConfigFactory factory) {
                    if (Builder.this.serializerProvider != null) {
                        return Builder.this.serializerProvider.get(this);
                    } else if (factory != null ){
                        return factory.getSerializer(this);
                    } else {
                        return new DefaultSerializer(getLogger(this));
                    }
                }

            };
            GraphServiceClient client = new GraphServiceClient();
            //note that configFactory may be null at this point
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
            b.serializer(serializer);
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
            b.httpProvider(httpProvider);
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
            b.authenticationProvider(authenticationProvider);
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
            b.executors(executors);
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
            b.logger(logger);
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

    
    private static final class ConstantProvider<T> implements IClientConfigProvider<T> {

        private final T value;

        private ConstantProvider(T value) {
            this.value = value;
        }
        
        static <T> ConstantProvider<T> create(T value) {
            return new ConstantProvider<T>(value);
        }
        
        @Override
        public T get(IClientConfigFactory factory) {
            return value;
        }
        
    }
}
