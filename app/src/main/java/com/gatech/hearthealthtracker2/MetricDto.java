package com.gatech.hearthealthtracker2;

import android.icu.util.Calendar;

import java.time.LocalDateTime;
import java.util.Date;

public class MetricDto implements Comparable<MetricDto> {

    private boolean uploaded;
    private Integer weight;
    private Integer systolic;
    private Integer diastolic;
    private Integer sleep;
    private Integer activity;
    private Integer sleepQuality; //1,2,3 could be enum but int is fine for npw
    private Integer foodQuality; //1,2,3 could be enum but int is fine for npw
    private Integer activityQuality; //1,2,3 could be enum but int is fine for npw
    private Date dateTime;
    private Integer age;
    private Integer height;
    private String guid;

    //dashboard
    //BMI
    //blood pressure
    //sleep
    //activity
    //food quality

    @Override
    public int compareTo(MetricDto m) {
        return -1 * getDateTime().compareTo(m.getDateTime());
    }

    public MetricDto() {
    }

    public MetricDto(Boolean doIt) {
        this.dateTime = Calendar.getInstance().getTime();
    }


    public MetricDto(Integer weight, Integer systolic, Integer diastolic, Integer sleep, Integer activity, Integer sleepQuality, Integer foodQuality, Integer activityQuality, Integer age, Integer height, String guid) {
        this.weight = weight;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.sleep = sleep;
        this.activity = activity;
        this.sleepQuality = sleepQuality;
        this.foodQuality = foodQuality;
        this.activityQuality = activityQuality;
        this.dateTime = Calendar.getInstance().getTime();
        this.age = age;
        this.height = height;
        this.guid = guid;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public Integer getSleep() {
        return sleep;
    }

    public Integer getActivity() {
        return activity;
    }

    public Integer getSleepQuality() {
        return sleepQuality;
    }

    public Integer getFoodQuality() {
        return foodQuality;
    }

    public Integer getActivityQuality() {
        return activityQuality;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getHeight() {
        return height;
    }

    public String getGuid() {
        return guid;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setSystolic(Integer systolic) {
        this.systolic = systolic;
    }

    public void setDiastolic(Integer diastolic) {
        this.diastolic = diastolic;
    }

    public void setSleep(Integer sleep) {
        this.sleep = sleep;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public void setSleepQuality(Integer sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public void setFoodQuality(Integer foodQuality) {
        this.foodQuality = foodQuality;
    }

    public void setActivityQuality(Integer activityQuality) {
        this.activityQuality = activityQuality;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
