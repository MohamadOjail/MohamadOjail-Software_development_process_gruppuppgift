package se.ya.videobutik.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class NewAddress {

    private short addressId;
    private String address;
    private String address2;
    private String district;
    private short cityId;
    private String postalCode;
    private String phone;
    private Blob location;
    private Timestamp lastUpdate;
    private int cityCityId;
}
