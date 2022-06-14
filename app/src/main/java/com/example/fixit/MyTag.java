package com.example.fixit;

import java.io.Serializable;

public class MyTag implements Serializable {

    String id_pyme;
    String pymeName;

    public MyTag() {
        this.id_pyme = id_pyme;
        this.pymeName = pymeName;
    }

    public MyTag(String id_pyme, String pymeName) {
        this.id_pyme = id_pyme;
        this.pymeName = pymeName;
    }

    public String getId_pyme() {
        return id_pyme;
    }

    public void setId_pyme(String id_pyme) {
        this.id_pyme = id_pyme;
    }

    public String getPymeName() {
        return pymeName;
    }

    public void setPymeName(String pymeName) {
        this.pymeName = pymeName;
    }
}
