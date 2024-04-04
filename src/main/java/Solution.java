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

        int boxNum = 1;
        for(int i = 0; i < time.length; i++){
            waitingL.offer(new Worker(i, 0, time[i][0], time[i][2]));
        }


        int t = 0;
        int bridgeOccupiedUntil = 0;
        boolean lastMoving = false;
        int res = 0;
        int processed = 0;
        while (true){
            while(!workingL.isEmpty() && workingL.peek().finishTime <= t){
                Worker worker = workingL.poll();
                processed++;

                res = Math.max(res, t - time[worker.i][3]);
                if(processed == n){
                    return res;
                }
                waitingL.offer(worker);
            }

            while(!workingR.isEmpty() && workingR.peek().finishTime <= t){
                waitingR.offer(workingR.poll());
            }
            if(!waitingR.isEmpty() && t >= bridgeOccupiedUntil){
                Worker worker = waitingR.poll();
                bridgeOccupiedUntil = t + time[worker.i][2];
                worker.finishTime = t + time[worker.i][2] + time[worker.i][3];
                workingL.offer(worker);
            }
            if(!waitingL.isEmpty() && t >= bridgeOccupiedUntil){
                Worker worker = waitingL.poll();
                worker.boxNumber = boxNum++;
                if(worker.boxNumber == n){
                    lastMoving = true;
                }
                if(worker.boxNumber <= n){
                    bridgeOccupiedUntil = t + time[worker.i][0];
                    worker.finishTime = t + time[worker.i][0] + time[worker.i][1];
                    workingR.offer(worker);
                }

            }

            t++;
        }

    }
}
class Worker{
    int i;
    int finishTime;
    int efficiency;
    int boxNumber = -1;

    Worker(int i, int finishTime, int leftToRight, int rightToLeft){
        this.i = i;
        this.finishTime = finishTime;
        efficiency = leftToRight + rightToLeft;
    }
}