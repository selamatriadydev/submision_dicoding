package com.amperadev.wisatabaturaja;

import android.os.Parcel;
import android.os.Parcelable;

public class Wisata implements Parcelable {
    private String name, remarks, tentang, fasilitas, kategori, photo;

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.name);
        dest.writeString(this.remarks);
        dest.writeString(this.tentang);
        dest.writeString(this.fasilitas);
        dest.writeString(this.kategori);
        dest.writeString(this.photo);
    }

    public Wisata() {

    }
    protected Wisata(Parcel in) {
        this.name=in.readString();
        this.remarks=in.readString();
        this.tentang=in.readString();
        this.fasilitas=in.readString();
        this.kategori=in.readString();
        this.photo=in.readString();
    }

    public static final Parcelable.Creator<Wisata> CREATOR = new Parcelable.Creator<Wisata>() {
        @Override
        public Wisata createFromParcel(Parcel source){
            return new Wisata(source);
        }
        @Override
        public Wisata[] newArray(int size) {
            return new Wisata[size];
        }
    };
}
