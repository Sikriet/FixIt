package com.example.fixit;

import java.io.Serializable;

public class MyTag implements Serializable {

    String id_pyme;
    String pymeName;
    String telefono_pyme;

    public MyTag() {
        this.id_pyme = id_pyme;
        this.pymeName = pymeName;
        this.telefono_pyme = telefono_pyme;
    }

    public MyTag(String id_pyme, String pymeName, String telefono_pyme) {
        this.id_pyme = id_pyme;
        this.pymeName = pymeName;
        this.telefono_pyme = telefono_pyme;
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

    public String getTelefono_pyme() {
        return telefono_pyme;
    }

    public void setTelefono_pyme(String telefono_pyme) {
        this.telefono_pyme = telefono_pyme;
    }
}
