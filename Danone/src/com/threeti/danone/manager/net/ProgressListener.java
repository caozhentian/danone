package com.threeti.danone.manager.net;

public interface ProgressListener {
    void onProgress(long progress, long totalLen, boolean hasDone);
}
