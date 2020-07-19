package com.gacrnd.gcs.serialzabletest.serializable;


import android.os.Parcel;
import android.os.Parcelable;

public class Course2 implements Parcelable {
    private String name;
    private int score;

    protected Course2(Parcel in) {
        name = in.readString();
        score = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(score);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Course2> CREATOR = new Creator<Course2>() {
        @Override
        public Course2 createFromParcel(Parcel in) {
            return new Course2(in);
        }

        @Override
        public Course2[] newArray(int size) {
            return new Course2[size];
        }
    };
}
