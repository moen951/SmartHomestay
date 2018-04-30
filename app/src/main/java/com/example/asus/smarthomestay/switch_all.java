package com.example.asus.smarthomestay;

/**
 * Created by ASUS on 25/3/2018.
 */

public class switch_all {
    private boolean allSwitch;
    private boolean livingRoom;
    private boolean Fan;
    private boolean Light;
    private boolean Bilik1;
    private boolean Bilik2;
    private boolean Bilik3;

    private boolean LampuLuar;

    private String Name;
    private String Password;



    public String getName() {

        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public switch_all() {

    }

    public boolean isAllSwitch() {
        return allSwitch;
    }

    public boolean isLivingRoom() {
        return livingRoom;
    }

    public boolean isFan() {
        return Fan;
    }

    public boolean isLight() {
        return Light;
    }

    public boolean isBilik1() {
        return Bilik1;
    }

    public boolean isBilik2() {
        return Bilik2;
    }

    public boolean isBilik3() {
        return Bilik3;
    }

    public boolean isLampuLuar() {
        return LampuLuar;
    }

    public switch_all(boolean Bilik1, boolean Bilik2, boolean Bilik3, boolean LampuLuar,String name, String password) {

        this.Bilik1 = Bilik1;
        this.Bilik2 = Bilik2;
        this.Bilik3 = Bilik3;
        this.LampuLuar=LampuLuar;
        Name = name;
        Password = password;
    }

    public switch_all(boolean livingRoom, boolean fan, boolean light, boolean bilik1, boolean bilik2, boolean bilik3) {
        this.livingRoom = livingRoom;
        Fan = fan;
        Light = light;
        Bilik1 = bilik1;
        Bilik2 = bilik2;
        Bilik3 = bilik3;
    }

    public void setLivingRoom(boolean livingRoom) {
        this.livingRoom = livingRoom;
    }



    public void setFan(boolean fan) {
        Fan = fan;
    }



    public void setLight(boolean light) {
        Light = light;
    }

    public void setAllSwitch(boolean allSwitch_state) {
        this.allSwitch = allSwitch_state;
    }




    public void setBilik1(boolean bilik1_state) {
        this.Bilik1 = bilik1_state;
    }



    public void setBilik2(boolean bilik2_state) {
        this.Bilik2 = bilik2_state;
    }



    public void setBilik3(boolean bilik3_state) {
        this.Bilik3 = bilik3_state;
    }

    public void setLampuLuar(boolean lampuLuar) {
        LampuLuar = lampuLuar;
    }
}
