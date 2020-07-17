
package com.app.softxperttask.Model;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class ParentResponse<T> {

    @SerializedName("data")
    private T mData;
    @SerializedName("status")
    private Long mStatus;

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
