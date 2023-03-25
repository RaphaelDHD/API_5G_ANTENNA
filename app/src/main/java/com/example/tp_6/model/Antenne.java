package com.example.tp_6.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Antenne implements  Parcelable{
    private Fields fields;

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    protected Antenne(Parcel in) {
        fields = in.readParcelable(Fields.class.getClassLoader());
    }

    public static final Creator<Antenne> CREATOR = new Creator<Antenne>() {
        @Override
        public Antenne createFromParcel(Parcel in) {
            return new Antenne(in);
        }

        @Override
        public Antenne[] newArray(int size) {
            return new Antenne[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(fields, flags);
    }


    public static class Fields implements Parcelable{

        public String op_site_id;
        public String op_name;
        public String release_date_5g;
        public String dep_name;
        public String reg_code;
        public String reg_name;
        public List<Double> geo_point_2d;
        public String com_name;
        public String op_code;
        public String frequency;
        public String com_code;
        public String dep_code;
        public String anfr_station_id;
        public String epci_code;
        public String epci_name;

        protected Fields(Parcel in) {
            op_site_id = in.readString();
            op_name = in.readString();
            release_date_5g = in.readString();
            dep_name = in.readString();
            reg_code = in.readString();
            reg_name = in.readString();
            com_name = in.readString();
            op_code = in.readString();
            frequency = in.readString();
            com_code = in.readString();
            dep_code = in.readString();
            anfr_station_id = in.readString();
            epci_code = in.readString();
            epci_name = in.readString();
            geo_point_2d = new ArrayList<>();
            in.readList(geo_point_2d, Double.class.getClassLoader());
        }

        public static final Creator<Fields> CREATOR = new Creator<Fields>() {
            @Override
            public Fields createFromParcel(Parcel in) {
                return new Fields(in);
            }

            @Override
            public Fields[] newArray(int size) {
                return new Fields[size];
            }
        };

        public String getOp_site_id() {
            return op_site_id;
        }

        public void setOp_site_id(String op_site_id) {
            this.op_site_id = op_site_id;
        }

        public String getOp_name() {
            return op_name;
        }

        public void setOp_name(String op_name) {
            this.op_name = op_name;
        }

        public String getRelease_date_5g() {
            return release_date_5g;
        }

        public void setRelease_date_5g(String release_date_5g) {
            this.release_date_5g = release_date_5g;
        }

        public String getDep_name() {
            return dep_name;
        }

        public void setDep_name(String dep_name) {
            this.dep_name = dep_name;
        }

        public String getReg_code() {
            return reg_code;
        }

        public void setReg_code(String reg_code) {
            this.reg_code = reg_code;
        }

        public String getReg_name() {
            return reg_name;
        }

        public void setReg_name(String reg_name) {
            this.reg_name = reg_name;
        }

        public List<Double> getGeo_point_2d() {
            return geo_point_2d;
        }

        public void setGeo_point_2d(ArrayList<Double> geo_point_2d) {
            this.geo_point_2d = geo_point_2d;
        }

        public String getCom_name() {
            return com_name;
        }

        public void setCom_name(String com_name) {
            this.com_name = com_name;
        }

        public String getOp_code() {
            return op_code;
        }

        public void setOp_code(String op_code) {
            this.op_code = op_code;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

        public String getCom_code() {
            return com_code;
        }

        public void setCom_code(String com_code) {
            this.com_code = com_code;
        }

        public String getDep_code() {
            return dep_code;
        }

        public void setDep_code(String dep_code) {
            this.dep_code = dep_code;
        }

        public String getAnfr_station_id() {
            return anfr_station_id;
        }

        public void setAnfr_station_id(String anfr_station_id) {
            this.anfr_station_id = anfr_station_id;
        }

        public String getEpci_code() {
            return epci_code;
        }

        public void setEpci_code(String epci_code) {
            this.epci_code = epci_code;
        }

        public String getEpci_name() {
            return epci_name;
        }

        public void setEpci_name(String epci_name) {
            this.epci_name = epci_name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeString(op_site_id);
            dest.writeString(op_name);
            dest.writeString(release_date_5g);
            dest.writeString(dep_name);
            dest.writeString(reg_code);
            dest.writeString(reg_name);
            dest.writeString(com_name);
            dest.writeString(op_code);
            dest.writeString(frequency);
            dest.writeString(com_code);
            dest.writeString(dep_code);
            dest.writeString(anfr_station_id);
            dest.writeString(epci_code);
            dest.writeString(epci_name);
            dest.writeList(geo_point_2d);
        }
    }
}