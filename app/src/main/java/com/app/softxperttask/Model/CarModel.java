
package com.app.softxperttask.Model;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class CarModel {

    @SerializedName("brand")
    private String mBrand;
    @SerializedName("constructionYear")
    private String mConstructionYear;
    @SerializedName("id")
    private Long mId;
    @SerializedName("imageUrl")
    private String mImageUrl;
    @SerializedName("isUsed")
    private Boolean mIsUsed;

    public String getBrand() {
        return mBrand;
    }

    public void setBrand(String brand) {
        mBrand = brand;
    }

    public String getConstructionYear() {
        return mConstructionYear;
    }

    public void setConstructionYear(String constructionYear) {
        mConstructionYear = constructionYear;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public Boolean getIsUsed() {
        return mIsUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        mIsUsed = isUsed;
    }

}
