package com.danone.comfit.net;

public interface ProgressListener {
    void onProgress(long progress, long totalLen, boolean hasDone);
}
