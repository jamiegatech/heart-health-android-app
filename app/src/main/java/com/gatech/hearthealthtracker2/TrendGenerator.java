package com.gatech.hearthealthtracker2;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TrendGenerator {
    public static MetricDto getFirst(ArrayList<MetricDto> metricDtos){
        Collections.sort(metricDtos);
        return metricDtos.get(0);
    }

    public static String bmiLabel(MetricDto metricDto){
        //Healthy BMI range: 18.5 kg/m2 - 25 kg/m2
        double BMI = getBmi(metricDto);
        if(BMI > 25.0){
           return "Overweight";
       }
        if(BMI < 18.5){
            return "Underweight";
        }
        return "Normal";
    }

    private static double getBmi(MetricDto metricDto) {
        return metricDto.getWeight() / ( 0.01 * 0.01 * metricDto.getHeight() * metricDto.getHeight());
    }

    public static Integer bmiColor(ArrayList<MetricDto> metricDtos){
        if(metricDtos.size() >= 2) {
            Collections.sort(metricDtos);
            MetricDto newest = metricDtos.get(0);
            MetricDto second = metricDtos.get(1);
            double BMI1 = getBmi(newest);
            double BMI2 = getBmi(second);

            if(BMI1 > 25.0 && BMI2 > BMI1){
                return Color.GREEN;
            }
            if(BMI1 < 18.5 && BMI2 < BMI1){
                return  Color.GREEN;
            }
            if(BMI1 > 25.0 && BMI2 < BMI1){
                return Color.RED;
            }
            if(BMI1 < 18.5 && BMI2 > BMI1){
                return Color.RED;
            }
        }
        return Color.GRAY;
    }

    public static String bpLabel(MetricDto metricDto){
       return metricDto.getSystolic() + "/" + metricDto.getDiastolic();
    }

    public static Integer bpColor(ArrayList<MetricDto> metricDtos){
        if(metricDtos.size() >= 2) {
            Collections.sort(metricDtos);
            MetricDto newest = metricDtos.get(0);
            MetricDto second = metricDtos.get(1);
            Integer systolic1 = newest.getSystolic();
            Integer diastolic1 = newest.getDiastolic();
            Integer systolic2 = second.getSystolic();
            Integer diastolic2 = second.getDiastolic();

            if(systolic1 < systolic2 && diastolic1 < diastolic2){
                return Color.GREEN;
            }
            if(systolic1 > systolic2 && systolic1 < 135  && diastolic1 > diastolic2 && diastolic1 < 85){
                return Color.GREEN;
            }
            if(systolic1 > systolic2 || diastolic1 > diastolic2){
                return Color.RED;
            }
        }
        return Color.GRAY;
    }

    private static String quality(Integer number){
        if(number == 1) {
            return "low";
        }else if(number == 2){
            return "neutral";
        }else{
            return "high";
        }
    }

    public static String sleepLabel(MetricDto metricDto){
        return metricDto.getSleep() + " hr (" + quality(metricDto.getSleepQuality())+")";
    }

    public static Integer sleepColor(ArrayList<MetricDto> metricDtos){
        if(metricDtos.size() >= 2) {
            Collections.sort(metricDtos);
            MetricDto newest = metricDtos.get(0);
            MetricDto second = metricDtos.get(1);
            Integer hrs1 = newest.getSleep();
            Integer quality1 = newest.getSleepQuality();
            Integer hrs2 = second.getSleep();
            Integer quality2 = second.getSleepQuality();

            if(hrs1 > hrs2 && quality1 > quality2){
                return Color.GREEN;
            }
            if(hrs1 > hrs2 && quality1.equals(quality2)){
                return Color.GREEN;
            }
            if(hrs1.equals(hrs2) && quality1 > quality2){
                return Color.GREEN;
            }
            if(hrs1 < hrs2 || quality1 < quality2){
                return Color.RED;
            }
        }
        return Color.GRAY;
    }

    public static String activityLabel(MetricDto metricDto){
        return metricDto.getActivity() + " min (" + quality(metricDto.getActivityQuality())+")";
    }

    public static Integer activityColor(ArrayList<MetricDto> metricDtos){
        if(metricDtos.size() >= 2) {
            Collections.sort(metricDtos);
            MetricDto newest = metricDtos.get(0);
            MetricDto second = metricDtos.get(1);
            Integer hrs1 = newest.getActivity();
            Integer quality1 = newest.getActivityQuality();
            Integer hrs2 = second.getActivity();
            Integer quality2 = second.getActivityQuality();

            if(hrs1 > hrs2 && quality1 > quality2){
                return Color.GREEN;
            }
            if(hrs1 > hrs2 && quality1.equals(quality2)){
                return Color.GREEN;
            }
            if(hrs1.equals(hrs2) && quality1 > quality2){
                return Color.GREEN;
            }
            if(hrs1 < hrs2 || quality1 < quality2){
                return Color.RED;
            }
        }
        return Color.GRAY;
    }


    public static String foodLabel(MetricDto metricDto){
        return quality(metricDto.getFoodQuality()) + " quality";
    }

    public static Integer foodColor(ArrayList<MetricDto> metricDtos){
        if(metricDtos.size() >= 2) {
            Collections.sort(metricDtos);
            MetricDto newest = metricDtos.get(0);
            MetricDto second = metricDtos.get(1);
            Integer quality1 = newest.getFoodQuality();
            Integer quality2 = second.getFoodQuality();

            if(quality1 > quality2){
                return Color.GREEN;
            }
            if(quality1 < quality2){
                return Color.RED;
            }
        }
        return Color.GRAY;
    }
}
