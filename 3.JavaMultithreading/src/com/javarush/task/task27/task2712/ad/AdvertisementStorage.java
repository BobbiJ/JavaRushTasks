package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage ourInstance = new AdvertisementStorage();

    private final List<Advertisement> videos = new ArrayList<>();



    public static AdvertisementStorage getInstance() {

        return ourInstance;

    }



    private AdvertisementStorage() {

        Object someContent = new Object();
        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60));
        add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));
        add(new Advertisement(someContent, "1", 10000, 0, 3 * 60));
        add(new Advertisement(someContent, "2", 5, 2, 5 * 60));
        add(new Advertisement(someContent, "3", 3, 2, 3 * 60));

    }



    public List<Advertisement> list() {return videos;}



    public void add(Advertisement advertisement) {

        videos.add(advertisement);

    }

}
