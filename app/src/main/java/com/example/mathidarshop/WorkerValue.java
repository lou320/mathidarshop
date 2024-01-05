package com.example.mathidarshop;

public class WorkerValue {
    private int id;
    private String name;
    private float atoneAmount;
    private float gin;
    private float mouseteeth;
    private float sheet;
    private float kunar;
    private float total;
    private String date;

    private final float atoneValue = 350;
    private final  float ginValue = 400;
    private final  float mouseTeethValue = 450;
    private  final  float sheetValue = 500;
    private  final  float kunarValue = 700;


    public WorkerValue(int id, String name, float atoneAmount, float gin, float mouseteeth, float sheet,float kunar) {
        this.id = id;
        this.name = name;
        this.atoneAmount = atoneAmount;
        this.gin = gin;
        this.mouseteeth = mouseteeth;
        this.sheet = sheet;
        this.kunar = kunar;

    }

    public WorkerValue(int id, String name, float atoneAmount, float gin, float mouseteeth, float sheet,float kunar, float total, String date) {
        this.id = id;
        this.name = name;
        this.atoneAmount = atoneAmount;
        this.gin = gin;
        this.mouseteeth = mouseteeth;
        this.sheet = sheet;
        this.kunar = kunar;
        this.total = total;
        this.date = date;
    }


    //to string


    @Override
    public String toString() {
        return "WorkerValue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", atoneAmount=" + atoneAmount +
                ", gin=" + gin +
                ", mouseteeth=" + mouseteeth +
                ", sheet=" + sheet +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAtoneAmount() {
        return atoneAmount;
    }

    public void setAtoneAmount(float atoneAmount) {
        this.atoneAmount = atoneAmount;
    }

    public float getGin() {
        return gin;
    }

    public void setGin(float gin) {
        this.gin = gin;
    }

    public float getMouseteeth() {
        return mouseteeth;
    }

    public void setMouseteeth(float mouseteeth) {
        this.mouseteeth = mouseteeth;
    }

    public float getSheet() {
        return sheet;
    }

    public void setSheet(float sheet) {
        this.sheet = sheet;
    }

    public void setKunar(float kunar){this.kunar = kunar;}

    public float getKunar(){return kunar;}

    public float caculate(){
        float total = ((this.atoneAmount * atoneValue) + (this.gin * ginValue) + (this.mouseteeth * mouseTeethValue)+(this.sheet * sheetValue)+(this.kunar * kunarValue));
        return  total;
    }
}
