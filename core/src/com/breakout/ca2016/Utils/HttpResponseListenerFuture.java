package com.breakout.ca2016.Utils;

import com.badlogic.gdx.Net;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class HttpResponseListenerFuture implements Net.HttpResponseListener, Future<Boolean> {

    private final Net.HttpResponseListener listener;
    private volatile Boolean result;

    public HttpResponseListenerFuture(Net.HttpResponseListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDone() {
        return result != null;
    }

    @Override
    public synchronized Boolean get() throws InterruptedException, ExecutionException {
        try {
            wait();
        } catch (InterruptedException e) {}
        return result;
    }

    @Override
    public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized void handleHttpResponse(Net.HttpResponse httpResponse) {
        if (listener != null) {
            listener.handleHttpResponse(httpResponse);
        }
        result = true;
        notifyAll();
    }

    @Override
    public synchronized void failed(Throwable t) {
        if (listener != null) {
            listener.failed(t);
        }
        result = false;
        notifyAll();
    }

    @Override
    public synchronized void cancelled() {
        if (listener != null) {
            listener.cancelled();
        }
        result = false;
        notifyAll();
    }
}