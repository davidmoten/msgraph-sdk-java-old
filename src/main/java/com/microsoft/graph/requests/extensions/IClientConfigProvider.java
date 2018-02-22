package com.microsoft.graph.requests.extensions;

public interface IClientConfigProvider<T> {

    T get(IClientConfigFactory factory);
}