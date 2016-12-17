package com.std.user.dto.res;

public class XN807900Res {
    // 上传凭证
    private String uploadToken;

    public XN807900Res() {
    }

    public XN807900Res(String uploadToken) {
        this.uploadToken = uploadToken;
    }

    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }
}
