package com.example.manjunath.farmerhelpapp.others;

public class Upload {

    private String mname;
    private String contactno;
    private String district;
    private String pincode;
    private String mimageurl;

    public Upload(){

    }

    public Upload(String mname, String contactno, String district, String pincode, String mimageurl) {
        this.mname = mname;
        this.contactno = contactno;
        this.district = district;
        this.pincode = pincode;
        this.mimageurl = mimageurl;
    }

    public String getMname() {
        return mname;
    }

    public String getMimageurl() {
        return mimageurl;
    }

    public String getContactno() {
        return contactno;
    }

    public String getDistrict() {
        return district;
    }

    public String getPincode() {
        return pincode;
    }
}
