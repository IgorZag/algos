//https://www.hackerrank.com/challenges/find-the-nearest-clone/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
/*
* In this challenge, there is a connected undirected graph where each of the nodes is a color. Given a color, find the shortest path connecting any two nodes of that color. Each edge has a weight of . If there is not a pair or if the color is not found, print .

For example, given , and  edges  and  and colors for each node are  we can draw the following graph:

image

Each of the nodes is labeled [node]/[color] and is colored appropriately. If we want the shortest path between color , blue, we see there is a direct path between nodes  and . For green, color , we see the path length  from . There is no pair for node  having color , red.

Function Description

Complete the findShortest function in the editor below. It should return an integer representing the length of the shortest path between two nodes of the same color, or  if it is not possible.

findShortest has the following parameter(s):

g_nodes: an integer, the number of nodes
g_from: an array of integers, the start nodes for each edge
g_to: an array of integers, the end nodes for each edge
ids: an array of integers, the color id per node
val: an integer, the id of the color to match
*
* */

package com.igorzag;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //findShortest(5, new int[]{1,1,2,3}, new int[]{2,3,4,5}, new long[]{2,2,3,3,2}, 2);
        System.out.println(findShortest(4, new int[]{1,1,4}, new int[]{2,3,2}, new long[]{1,2,3,4}, 2));
    }
    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        // solve here
        //
        boolean[] visited = new boolean[graphNodes];
        Arrays.fill(visited, false);

        int min = graphNodes + 1;
        for(int i = 0; i < ids.length; i++){
            if(ids[i] == val){
                min =  findShortestPathFromId(i  + 1, graphNodes, graphFrom, graphTo, ids, val, visited, min);
            }
        }

        return min < graphNodes ? min : -1;

    }
    static ArrayList<Integer> getEdgesTo(long from, int[] graphFrom, int[] graphTo){
        ArrayList<Integer> res = new ArrayList();
        for(int i = 0; i < graphFrom.length; i++){
            if(graphFrom[i] == from) res.add(graphTo[i]);
        }
        for(int i = 0; i < graphTo.length; i++){
            if(graphTo[i] == from) res.add(graphFrom[i]);
        }
        return res;
    }
    static int findShortestPathFromId(int from, int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val, boolean[] visited, int currentMin){
        if(visited[from - 1]) return currentMin;
        if(currentMin == 1) return 1; //cannot be shorter
        Queue<Integer> q = new LinkedList<>();
        q.add(from);

        int[] distances = new int[graphNodes];
        Arrays.fill(distances, -1);
        distances[from - 1] = 0;

        while(!q.isEmpty()){
            int node = q.poll();

            if(visited[node - 1] == true) continue;
            visited[node - 1] = true;
            if(distances[node - 1] > currentMin) return -1; //return if current min stays, no point to look further

            if(ids[node - 1] == val && distances[node - 1] > 0) {
                return distances[node - 1] < currentMin ? distances[node - 1] : currentMin;
            }

            ArrayList<Integer> nodesTo = getEdgesTo(node, graphFrom, graphTo);


            for(int nodeTo : nodesTo){
                if(visited[nodeTo - 1] == true) {
                    continue;  //do not add node we already visited
                }
                distances[nodeTo - 1] = distances[node - 1] + 1;
                q.add(nodeTo);
            }
        }
        return currentMin;
    }
}
