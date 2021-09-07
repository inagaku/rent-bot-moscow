package com.javaschool.restapiserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementWrapper {
    List<Long> chatIds;
    Advertisement advertisement;

    public static AdvertisementWrapperBuilder builder() {
        return new AdvertisementWrapperBuilder();
    }

    public static class AdvertisementWrapperBuilder {
        private List<Long> chatIds;
        private Advertisement advertisement;

        AdvertisementWrapperBuilder() {
        }

        public AdvertisementWrapperBuilder chatIds(List<Long> chatIds) {
            this.chatIds = chatIds;
            return this;
        }

        public AdvertisementWrapperBuilder advertisement(Advertisement advertisement) {
            this.advertisement = advertisement;
            return this;
        }

        public AdvertisementWrapper build() {
            return new AdvertisementWrapper(chatIds, advertisement);
        }

        public String toString() {
            return "AdvertisementWrapper.AdvertisementWrapperBuilder(chatIds=" + this.chatIds + ", advertisement=" + this.advertisement + ")";
        }
    }
}
