package com.microsoft.graph.requests.extensions;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.http.HttpMethod;
import com.microsoft.graph.http.IHttpRequest;
import com.microsoft.graph.logger.ILogger;
import com.microsoft.graph.logger.LoggerLevel;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.options.HeaderOption;
import com.microsoft.graph.options.Option;

public class GraphServiceClientTest {

    @Test
    public void testThatAuthenticatProviderUsesCustomLogger() {
        final List<String> logs = new CopyOnWriteArrayList<String>();
        IGraphServiceClient client = GraphServiceClient //
                .builder() //
                .authenticationProvider(createAuthProviderProvider())
                .logger(createLoggerProvider(logs)) //
                .buildClient();
        client.getLogger().logDebug("hello");
        client.getAuthenticationProvider().authenticateRequest(createRequest());
        assertEquals(Arrays.asList("creating authProvider", "hello", "authenticateRequest called"), logs);
    }

    private static IClientConfigProvider<IAuthenticationProvider> createAuthProviderProvider() {
        return new IClientConfigProvider<IAuthenticationProvider>() {
                ILogger log;
                IAuthenticationProvider auth;
            @Override
            public IAuthenticationProvider get(IClientConfigFactory factory) {
                if (log == null) {
                    log = factory.getLogger(factory);
                    log.logDebug("creating authProvider");
                    auth = new IAuthenticationProvider() {
                        @Override
                        public void authenticateRequest(IHttpRequest request) {
                            log.logDebug("authenticateRequest called");
                        }};
                }
                return auth;
                
            }};
    }

    private static IClientConfigProvider<ILogger> createLoggerProvider(final List<String> logs) {
        return new IClientConfigProvider<ILogger>() {
            ILogger log;
            @Override
            public ILogger get(IClientConfigFactory factory) {
                if (log == null) {
                    log = new ILogger() {

                        @Override
                        public void setLoggingLevel(LoggerLevel level) {
                            
                        }

                        @Override
                        public LoggerLevel getLoggingLevel() {
                            return LoggerLevel.DEBUG;
                        }

                        @Override
                        public void logDebug(String message) {
                           logs.add(message);
                        }

                        @Override
                        public void logError(String message, Throwable throwable) {
                            throwable.printStackTrace();
                        }};
                }
                return log;
            }
        };
    }

    private static IHttpRequest createRequest() {
        return new IHttpRequest() {

            @Override
            public URL getRequestUrl() {
                return null;
            }

            @Override
            public HttpMethod getHttpMethod() {
                return null;
            }

            @Override
            public List<HeaderOption> getHeaders() {
                return null;
            }

            @Override
            public List<Option> getOptions() {
                return null;
            }

            @Override
            public void addHeader(String header, String value) {
            }

            @Override
            public void setUseCaches(boolean useCaches) {
            }

            @Override
            public boolean getUseCaches() {
                return false;
            }};
    }

}
