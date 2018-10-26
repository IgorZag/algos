//https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
package com.igorzag;

public class Main {

    public static void main(String[] args) {
	// write your code here
        climbingLeaderboard(new int[]{100,100,50,40,40,20,10}, new int[]{5,25,50,120});
        climbingLeaderboard(new int[]{100,100,50,40,40,20,10}, new int[]{0,0,1,2});
    }
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        if(scores == null) return null;
        int[] result = new int[alice.length];

        int index = 0;
        int place = 1;

        for(int i = alice.length - 1; i >= 0; i--){
//if alice's score is higher or equal to current score we are keeping current place same
//ELSE we are iterating scores until we find score which is less than Alice's
            if(index < scores.length){
                if(alice[i] < scores[index]){
                    boolean placed = false;
                    int prev_score = scores[index];
                    int curr_index = index + 1;
                    while(curr_index < scores.length){
                        if(scores[curr_index] != prev_score){
                            ++place;
                            prev_score = scores[curr_index];
                            if(alice[i] >= scores[curr_index]){
                                placed = true;
                                break;
                            }
                        }
                        ++curr_index;
                    }
                    index = curr_index;

                    if(!placed){
                        //last place
                        ++place;
                    }
                }
            }

            result[i] = place;
        }




        return result;

    }
}
