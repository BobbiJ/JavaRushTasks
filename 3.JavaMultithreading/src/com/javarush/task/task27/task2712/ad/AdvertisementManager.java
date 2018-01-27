package com.javarush.task.task27.task2712.ad;



import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private List<Advertisement> bestList=null;
    private long bestAmount=0;
    private int bestDuration=0;
    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
    public void processVideos() {
        makeAllSets(getHitsVideo(storage.list()));
        if (bestList.isEmpty()){
            throw new NoVideoAvailableException();
        }
        Collections.sort(bestList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                if ((o1.getAmountPerOneDisplaying() - o2.getAmountPerOneDisplaying()) == 0) {
                    return (int) (o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration() - o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
                } else {
                    return (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
                }
            }
            });
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(bestList,getTotalAmount(bestList),getTotalDuration(bestList)));
        for (Advertisement ad : bestList) {
            ConsoleHelper.writeMessage(String.format("%s  is displaying... %d, %d", ad.getName(), ad.getAmountPerOneDisplaying(), ad.getAmountPerOneDisplaying() * 1000 / ad.getDuration()));
            ad.revalidate();
        }

    }

    private long getTotalAmount(List<Advertisement> list){
        long res = 0;
        for (Advertisement a : list){
            res+=a.getAmountPerOneDisplaying();
        }
        return res;
    }

    private int getTotalDuration(List<Advertisement> list){
        int res =0;
        for (Advertisement a :list){
            res+=a.getDuration();
        }
        return res;
    }


    private void checkBestList(List<Advertisement> list){
        int currentDuration=getTotalDuration(list);
        long currentAmount=getTotalAmount(list);

        if (bestList==null){
            if (currentDuration<=timeSeconds){
                bestList=new ArrayList<>(list);
                bestAmount=currentAmount;
                bestDuration=currentDuration;
            }
        }
        else {
            if (currentDuration<=timeSeconds && currentAmount>bestAmount){
                bestList=new ArrayList<>(list);
                bestAmount=currentAmount;
                bestDuration=currentDuration;
            }
            else
            if (currentDuration<=timeSeconds && currentAmount==bestAmount){
                if (currentDuration>bestDuration){
                    bestList=new ArrayList<>(list);
                    bestDuration=currentDuration;
                }
                else
                if (currentDuration==bestDuration){
                    if (list.size()<bestList.size()){
                        bestList=new ArrayList<>(list);
                    }
                }

            }
        }

    }

    private List getHitsVideo(List<Advertisement> list){
        List<Advertisement> res = new ArrayList();
        for (Advertisement a : list){
            if (a.getHits()>0){
                res.add(a);
            }
        }
        return res;
    }

    private void makeAllSets(List list){

        checkBestList(list);

        for (int i=0;i<list.size();i++){
            List<Advertisement> newList = new ArrayList(list);
            if (newList.size()>1){
                newList.remove(i);
                makeAllSets(newList);}
        }
    }
}



