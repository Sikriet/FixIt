package com.example.fixit;

public class PymeClass {

    String id_pyme;
    String addressName;
    String pymeName;
    String comuna_pyme;

    public PymeClass() {
        this.id_pyme = id_pyme;
        this.addressName = addressName;
        this.pymeName = pymeName;
        this.comuna_pyme = comuna_pyme;
    }

    public PymeClass(String id_pyme, String addressName, String pymeName, String comuna_pyme) {
        this.id_pyme = id_pyme;
        this.addressName = addressName;
        this.pymeName = pymeName;
        this.comuna_pyme = comuna_pyme;
    }

    @Override
    public String toString() {
        return "PymeClass{" +
                "id_pyme='" + id_pyme + '\'' +
                ", addressName='" + addressName + '\'' +
                ", pymeName='" + pymeName + '\'' +
                ", comuna_pyme='" + comuna_pyme + '\'' +
                '}';
    }

    public String getId_pyme() {
        return id_pyme;
    }

    public void setId_pyme(String id_pyme) {
        this.id_pyme = id_pyme;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getPymeName() {
        return pymeName;
    }

    public void setPymeName(String pymeName) {
        this.pymeName = pymeName;
    }

    public String getComuna_pyme() {
        return comuna_pyme;
    }

    public void setComuna_pyme(String comuna_pyme) {
        this.comuna_pyme = comuna_pyme;
    }
}
