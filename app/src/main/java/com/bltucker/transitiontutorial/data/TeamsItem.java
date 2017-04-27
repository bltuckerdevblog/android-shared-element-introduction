package com.bltucker.transitiontutorial.data;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TeamsItem implements Parcelable {

    @SerializedName("squadMarketValue")
    private String squadMarketValue;

    @SerializedName("crestUrl")
    private String crestUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("shortName")
    private String shortName;

    protected TeamsItem(Parcel in) {
        squadMarketValue = in.readString();
        crestUrl = in.readString();
        name = in.readString();
        id = in.readInt();
        shortName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(squadMarketValue);
        dest.writeString(crestUrl);
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeString(shortName);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public void setSquadMarketValue(String squadMarketValue) {
        this.squadMarketValue = squadMarketValue;
    }

    public String getSquadMarketValue() {
        return squadMarketValue;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public static final Creator<TeamsItem> CREATOR = new Creator<TeamsItem>() {
        @Override
        public TeamsItem createFromParcel(Parcel in) {
            return new TeamsItem(in);
        }

        @Override
        public TeamsItem[] newArray(int size) {
            return new TeamsItem[size];
        }
    };
}
