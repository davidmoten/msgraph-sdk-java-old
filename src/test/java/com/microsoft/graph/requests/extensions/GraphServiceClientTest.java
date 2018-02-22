package com.microsoft.graph.requests.extensions;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.http.IHttpRequest;
import com.microsoft.graph.logger.ILogger;
import com.microsoft.graph.logger.LoggerLevel;
import com.microsoft.graph.models.extensions.IGraphServiceClient;

public class GraphServiceClientTest {

    @Test
    public void testThatAuthenticatProviderUsesCustomLogger() {
        final List<String> logs = new CopyOnWriteArrayList<String>();
        IGraphServiceClient client = GraphServiceClient //
                .builder() //
                .authenticationProvider(createAuthProviderProvider()) //
                .logger(createLoggerProvider(logs)) //
                .buildClient();
        client.getLogger().logDebug("hello");
        client.getAuthenticationProvider().authenticateRequest(null);
        assertEquals(Arrays.asList("created logger", "created authProvider", "hello", "authenticateRequest called"),
                logs);
    }

    private static IClientConfigProvider<IAuthenticationProvider> createAuthProviderProvider() {
        return new IClientConfigProvider<IAuthenticationProvider>() {
            ILogger log;
            IAuthenticationProvider auth;

            @Override
            public IAuthenticationProvider get(IClientConfigFactory factory) {
                if (log == null) {
                    log = factory.getLogger(factory);
                    auth = new IAuthenticationProvider() {
                        @Override
                        public void authenticateRequest(IHttpRequest request) {
                            log.logDebug("authenticateRequest called");
                        }
                    };
                    log.logDebug("created authProvider");
                }
                return auth;
            }
        };
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
                        }
                    };
                    log.logDebug("created logger");
                }
                return log;
            }
        };
    }
    
}
