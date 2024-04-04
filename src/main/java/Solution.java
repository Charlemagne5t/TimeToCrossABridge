import java.util.Comparator;
import java.util.PriorityQueue;


class Solution {
    public int findCrossingTime(int n, int k, int[][] time) {
        PriorityQueue<Worker> workingL = new PriorityQueue<>(Comparator
                .comparingInt(w -> w.finishTime));
        PriorityQueue<Worker> waitingL = new PriorityQueue<>(Comparator
                .comparingInt((Worker w) -> -w.efficiency)
                .thenComparing((Worker w) -> -w.i)
        );
        PriorityQueue<Worker> workingR = new PriorityQueue<>(Comparator
                .comparingInt((Worker w) -> w.finishTime)
        );
        PriorityQueue<Worker> waitingR = new PriorityQueue<>(Comparator
                .comparingInt((Worker w) -> -w.efficiency)
                .thenComparing((Worker w) -> -w.i)
        );
        int t = 0;

        for(int i = 0; i < time.length; i++){
            waitingL.offer(new Worker(i, 0, time[i][0], time[i][2]));
        }
        return 0;
    }
}
class Worker{
    int i;
    int finishTime;
    int efficiency;

    Worker(int i, int finishTime, int leftToRight, int rightToLeft){
        this.i = i;
        this.finishTime = finishTime;
        efficiency = leftToRight + rightToLeft;
    }
}