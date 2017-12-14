package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.denominations = new HashMap<>();
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount(){
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> pair: denominations.entrySet()){
            totalAmount = totalAmount + pair.getKey()*pair.getValue();
        }
        return totalAmount;
    }

    public boolean hasMoney(){
        return denominations.size()>0;
    }

    public boolean isAmountAvailable(int expectedAmount){

        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        ArrayList<Integer> denominationsOnly = new ArrayList<>();
        Map<Integer, Integer> withdrawAmountMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        denominationsOnly.addAll(denominations.keySet());
        Collections.sort(denominationsOnly, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int mny = expectedAmount;
        for (int i = 0; i < denominationsOnly.size(); i++) {
            if (mny / denominationsOnly.get(i) > 0) {
                int n = mny / denominationsOnly.get(i);
                if (n < denominations.get(denominationsOnly.get(i))) {
                    mny = mny - n * denominationsOnly.get(i);
                    withdrawAmountMap.put(denominationsOnly.get(i), n);
                } else {
                    mny = mny - denominations.get(denominationsOnly.get(i)) * denominationsOnly.get(i);
                    withdrawAmountMap.put(denominationsOnly.get(i), denominations.get(denominationsOnly.get(i)));
                }
            }

        }
        if (mny!=0)throw new NotEnoughMoneyException();
        for (Map.Entry<Integer, Integer> pair : withdrawAmountMap.entrySet()){
            System.out.println(pair.getKey() + " - " + pair.getValue());
            denominations.put(pair.getKey(),denominations.get(pair.getKey())- pair.getValue());
        }
        return withdrawAmountMap;
    }


}
