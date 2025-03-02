import java.util.*;

public class A2_Q3 {
    public static String directions(int[] distances){
        int numOfSteps = distances.length;

        // dp[i] minumum max height for step i at current height h
        Map<Integer, String> dp = new HashMap<>();
        dp.put(0, "");  // Start at height 0 with an empty path

        for (int i = 0; i < numOfSteps; i++) {  //looping for each distance of the array
            Map<Integer, String> nextDp = new HashMap<>();

            for (int height : dp.keySet()) {    //updating U or D for each height
                int upHeight = height + distances[i];
                int downHeight = height - distances[i];

                //for possible up height
                if (upHeight >= 0 && (nextDp.get(upHeight) == null || nextDp.get(upHeight).length() > dp.get(height).length() + 1)) {
                    nextDp.put(upHeight, dp.get(height) + "U");
                }

                //possible down height
                if (downHeight >= 0 && (nextDp.get(downHeight) == null || nextDp.get(downHeight).length() > dp.get(height).length() + 1)) {
                    nextDp.put(downHeight, dp.get(height) + "D");
                }
            }
            dp = nextDp;
        }
        //check for starting point return
        if (dp.get(0) != null) { //exists a valid path
            return dp.get(0);
        } else {
            return "IMPOSSIBLE";
        }
    }
}

