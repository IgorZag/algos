package com.igorzag;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        TowerHopperTest();
    }
    static void TowerHopperTest(){

        int[] arrOk = {1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,0,2,0,4,3,2,0,5,0,0,4,0,0,2,0,2,0};
        int[] arrFail = {1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,0,2,0,4,3,2,0,5,0,0,4,0,0,2,0,2,0,0};
        System.out.println(IsHoppableBfs(arrOk) + " "  + IsHoppableDfs(arrOk) + " " + IsHoppableGreedy(arrOk));
        System.out.println(IsHoppableBfs(arrFail) + " "  + IsHoppableDfs(arrFail) + " " + IsHoppableGreedy(arrFail));
        System.out.println(IsHoppableBfs(new int[]{4, 2, 0, 2, 0}) + " "  + IsHoppableDfs(new int[]{4, 2, 0, 2, 0}) + " " + IsHoppableGreedy(new int[]{4, 2, 0, 2, 0}));
        System.out.println(IsHoppableBfs(new int[]{1, 3, 5, 2, 1}) + " "  + IsHoppableDfs(new int[]{1, 3, 5, 2, 1}) + " " + IsHoppableGreedy(new int[]{1, 3, 5, 2, 1}));
        System.out.println(IsHoppableBfs(new int[]{4, 2, 0, 0, 2, 0}) + " "  + IsHoppableDfs(new int[]{4, 2, 0, 0, 2, 0}) + " " + IsHoppableGreedy(new int[]{4, 2, 0, 0, 2, 0}));


       // IsHoppableGreedy(new int[]{4, 2, 0, 2, 0});
    }
    static boolean IsHoppableBfs(int[] towers){
        boolean result = false;

        if(towers.length > 0){
            HashSet<Integer> visited = new HashSet<>();
            Queue<Integer> toVisit = new LinkedList<>();

            toVisit.add(0);

            while(!toVisit.isEmpty()){
                int towerId = toVisit.remove();

                if(!visited.contains(towerId)){
                    int towerHeight = towers[towerId];
                    if(towerHeight > 0){
                        if(towerId + towerHeight >= towers.length ){
                            result = true;
                            break;    // escaped
                        }

                        //add new tower to be checked into the queue
                        for(int i = 1; i <= towerHeight; i++){
                            toVisit.add(towerId + i);
                        }


                        //add this
                        visited.add(towerId);
                    }
                }
            }

        }


        return result;
    }
    static boolean IsHoppableBackTracking(int[] towers){
        boolean result = false;

        if(towers.length > 0){
            for(int i = towers.length; i >= 0; i--){

            }


        }


        return result;
    }

    static boolean IsHoppableDfs(int[] towers){
        boolean result = false;

        if(towers.length > 0){
            HashSet<Integer> visited = new HashSet<>();
            Stack<Integer> toVisit = new Stack<>();

            toVisit.push(0);

            while(!toVisit.isEmpty()){
                int towerId = toVisit.pop();

                if(!visited.contains(towerId)){
                    int towerHeight = towers[towerId];
                    if(towerHeight > 0){
                        if(towerId + towerHeight >= towers.length ){
                            result = true;
                            break;    // escaped
                        }

                        //add new tower to be checked into the queue
                        for(int i = 1; i <= towerHeight; i++){
                            toVisit.push(towerId + i);
                        }


                        //add this
                        visited.add(towerId);
                    }
                }
            }

        }


        return result;
    }

    static boolean IsHoppableGreedy(int[] towers){

        if(towers.length == 0) return false;

        int towerId = 0;  //start with first one
        do{
            int max = towerId + towers[towerId];
            int maxId = towerId;
            for(int i = 1; i <= towers[towerId]; i++){
                int tmpId = towerId + i;

                if(tmpId >= towers.length){
                    return true;
                }
                if(tmpId  + towers[tmpId] > max){
                    max = tmpId + towers[tmpId];
                    maxId = tmpId;
                }
            }
            if(towerId == maxId){
                return false;
            }
            towerId = maxId;
        } while(true);

    }
}
