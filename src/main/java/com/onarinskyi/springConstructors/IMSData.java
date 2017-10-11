package com.onarinskyi.springConstructors;

public class IMSData {

    private String imsURL;
    private String imsOpenApiConnection;
    private String imsCasino;
    private String imsLogin;
    private String imsPass;

    public String getImsURL() {
        return imsURL;
    }

    public void setImsURL(String imsURL) {
        this.imsURL = imsURL;
    }

    public void setImsOpenApiConnection(String imsOpenApiConnection) {
        this.imsOpenApiConnection = imsOpenApiConnection;
    }

    public String getImsOpenApiConnection () {
        return imsOpenApiConnection;
    }

    public String getImsCasino() {
        return imsCasino;
    }

    public void setImsCasino(String imsCasino) {
        this.imsCasino = imsCasino;
    }

    public String getImsLogin() {
        return imsLogin;
    }

    public void setImsLogin(String imsLogin) {
        this.imsLogin = imsLogin;
    }

    public String getImsPass() {
        return imsPass;
    }

    public void setImsPass(String imsPass) {
        this.imsPass = imsPass;
    }

}
