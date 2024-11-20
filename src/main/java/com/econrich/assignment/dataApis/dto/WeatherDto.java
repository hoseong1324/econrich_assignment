package com.econrich.assignment.dataApis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class WeatherDto {

    @Getter
    @Setter
    public static class ReturnWeatherApi {
        private Response response;

        @Getter
        @Setter
        public static class Response {
            private Header header;
            private Body body;

            @Getter
            @Setter
            public static class Header {
                private String resultCode;
                private String resultMsg;
            }

            @Getter
            @Setter
            public static class Body {
                private String dataType;
                private Items items;

                @Getter
                @Setter
                public static class Items {
                    private List<Item> item;

                    @Getter
                    @Setter
                    public static class Item {
                        private String baseDate;
                        private String baseTime;
                        private String category;
                        private String nx;
                        private String ny;
                        private String obsrValue;
                    }
                }
            }
        }
    }

    @Builder
    @Getter
    public static class LocationsSummary {
        private String si;
        private String gu;
        private String dong;
        private List<WeatherSummary> weatherSummary;
    }

    @Getter
    @Builder
    public static class WeatherSummary {
        private String category;
        private String obsrValue;

    }

    @Getter
    public enum Category {
        T1H("기온", "℃"),
        RN1("1시간 강수량", "mm"),
        UUU("동서바람성분", "m/s"),
        VVV("남북바람성분", "m/s"),
        REH("습도", "%"),
        PTY("강수형태", ""),
        VEC("풍향", "deg"),
        WSD("풍속", "m/s");

        private final String text;
        private final String value;

        Category(String text, String value) {
            this.text = text;
            this.value = value;
        }
    }
}
