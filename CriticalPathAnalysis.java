//The program contains a graph for which critical path analysis has to be done.
//The task is read one vertex at a time, calculated the earliest completion time and the latest completion time.
//Slack time is calculated. And critical path is analysed.

import java.util.*;

//Author: Ashwin Ravishankar


public class CriticalPathAnalysis {
    public static void main(String[] args) {
        
        int[][] graphWeight = new int[18][18];
        
        //Initialize entire graph weight to 0.
        for(int i=0;i<18;i++) {
            for(int j=0;j<18;j++) {
                graphWeight[i][j]=0;
            }
        }
        
        //Initialize graph weight to correct vertice pairs.
        graphWeight[1][2] = 3;
        graphWeight[1][3] = 6;
        graphWeight[1][4] = 5;
        graphWeight[2][5] = 2;
        graphWeight[3][6] = 4;
        graphWeight[4][7] = 8;
        graphWeight[5][8] = 4;
        graphWeight[6][8] = 7;
        graphWeight[6][9] = 1;
        graphWeight[7][9] = 3;
        graphWeight[8][10] = 4;
        graphWeight[9][11] = 5;
        graphWeight[9][12] = 3;
        graphWeight[10][14] = 6;
        graphWeight[7][13] = 12;
        graphWeight[11][14] = 4;
        graphWeight[12][15] = 9;
        graphWeight[13][15] = 8;
        graphWeight[14][16] = 2;
        graphWeight[15][16] = 3;
        graphWeight[16][17] = 2;
        
         
        int[] earliestCompletionTime = new int[18];
        int[] latestCompletionTime = new int[18];
        int[] slackTime = new int[18];
        
        //Calculate earliest completion time for each task
        for(int i=0;i<=17;i++) {
            earliestCompletionTime[i]=0;    //set all the earliest completion time to 0
            for(int j=1;j<i;j++) {
                if (graphWeight[j][i] != 0) //If there exists a path from A to B
                    if (earliestCompletionTime[j] + graphWeight[j][i] > earliestCompletionTime[i])      //EC[2]+ WEIGHT(2)> 0 EC[5]
                        earliestCompletionTime[i] = earliestCompletionTime[j] + graphWeight[j][i];
            }
        }
            
        for (int i = 17; i >= 1; i--)
            latestCompletionTime[i] = earliestCompletionTime[17];   //Set latest completion time for all the vertices
        
        //Calculate latest completion time for each task
        for (int i = 17; i >= 1; i--) {
            for (int j = 1; j < i; j++) {
                if (graphWeight[j][i] != 0)
                    if (latestCompletionTime[i] - graphWeight[j][i] < latestCompletionTime[j])
                        latestCompletionTime[j] = latestCompletionTime[i] - graphWeight[j][i];
            }
        }
        
        //Calculate slack time
        for (int i=0; i<=17;i++) {
            slackTime[i]=latestCompletionTime[i] - earliestCompletionTime[i];
        }
        
        //Display critical Path
        System.out.println("\n--------- Critical Path for the Graph ---------");
        for(int i=1; i<18; i++) {
            if(slackTime[i]==0) {
                if(i!=0) {
                    System.out.print(i);
                    if(i!=17) {
                        System.out.print(" ---> ");
                    }
                }
            }
        }
        
        //Display earliest and latest completion time
        System.out.println("\n\n--------- Earliest & Latest Completion Time For Task ---------");
        for(int i=1; i<18; i++) {
            System.out.println("For Task #" + i);
            System.out.println("Earliest Completion Time: " + earliestCompletionTime[i] + " weeks\t Latest Completion Time: " + latestCompletionTime[i] + " weeks\n");
        }
        
        //Display earliest comepletion time of project
        System.out.println("\n--------- Earlist Completion Time For Entire Project---------");
        System.out.println("Project's Earliest Completion Time: "  + earliestCompletionTime[17] + " weeks");
        
        //Display slack time for each task
        System.out.println("\n\n--------- Slack Time For Task ---------");
        for (int i=1;i<18;i++) {
            System.out.println("Slack Time for Task "+ i + ": " + slackTime[i]+ " weeks");
        }
        
    }
}
