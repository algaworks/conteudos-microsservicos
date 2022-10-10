package com.algaworks.example.spring.cloud.stream.metrics.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.UUID;

@Data
public class AppMetricModel {
    private UUID id;
    private String upTime;
    private String downTime;
    private UUID appId;

    @JsonProperty
    public String getUpTimeDescription() {
        if (StringUtils.isBlank(this.upTime)) {
            return "";
        }

        if (StringUtils.isBlank(this.downTime)) {
            return "";
        }

        Duration upDays = Duration.parse(this.upTime);
        Duration downDays = Duration.parse(this.downTime);
        Duration totalDays = upDays.plus(downDays);

        try {
            float upMinutes = upDays.toMinutes();
            float totalMinutes = totalDays.toMinutes();

            if (totalMinutes == 0) {
                return "";
            }

            return NumberFormat.getPercentInstance().format(upMinutes / totalMinutes);
        } catch (ArithmeticException e) {
            return "";
        }
    }

    @JsonProperty
    public String getDownTimeDescription() {
        if (StringUtils.isBlank(this.downTime)) {
            return "";
        }

        if (StringUtils.isBlank(this.upTime)) {
            return "";
        }

        Duration upDays = Duration.parse(this.upTime);
        Duration downDays = Duration.parse(this.downTime);
        Duration totalDays = upDays.plus(downDays);

        try {
            float downMinutes = downDays.toMinutes();
            float totalMinutes = totalDays.toMinutes();

            if (totalMinutes == 0) {
                return "";
            }

            return NumberFormat.getPercentInstance().format(downMinutes / totalMinutes);
        } catch (ArithmeticException e) {
            return "";
        }
    }
}
