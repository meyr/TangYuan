package net.mshome.fyon_linux.tangyuan;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dean Guo on 10/20/14.
 */
public class Actor implements Parcelable
{
    String name;
    String picName;
    boolean inout;
    int num_big_sian, num_small_sian;

    public Actor(){};

    public void setInout(boolean inout){this.inout = inout;}

    protected Actor(Parcel in) {
        name = in.readString();
        picName = in.readString();
    }

    public void setSian(int big, int small)
    {
        this.num_big_sian = big;
        this.num_small_sian = small;
    }

    public static final Creator<Actor> CREATOR = new Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel in) {
            return new Actor(in);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };

    public int getImageResourceId(Context context )
    {
        try
        {
            return context.getResources().getIdentifier(this.picName, "drawable", context.getPackageName());

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(picName);
    }
}
