package org.apache.flume.interceptor.service;

import org.apache.flume.Event;

import java.util.Map;

public class SimpleEvent implements Event {
    private int i;

    public SimpleEvent(int i) {
        this.i = i;
    }

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public void setHeaders(Map<String, String> map) {
    }

    @Override
    public byte[] getBody() {
        return new byte[0];
    }

    @Override
    public void setBody(byte[] bytes) {
    }

    @Override
    public String toString() {
        return Integer.toString( i );
    }

    @Override
    public int hashCode() {
        return Integer.valueOf( i * 256 );
    }
}
