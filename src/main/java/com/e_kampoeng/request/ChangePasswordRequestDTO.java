package com.e_kampoeng.request;

public class ChangePasswordRequestDTO {
    private String oldPassword;
    private String newPassword;
    private String currentNewPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCurrentNewPassword() {
        return currentNewPassword;
    }

    public void setCurrentNewPassword(String currentNewPassword) {
        this.currentNewPassword = currentNewPassword;
    }
}
