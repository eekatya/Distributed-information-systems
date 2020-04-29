package dao;
public class SpeedCalculation {
    private long time;
    private int numOfRecords;
    SpeedCalculation()
    {
        time = 0;
        numOfRecords = 0;
    }
    public void reset()
    {
        time = 0;
        numOfRecords = 0;
    }
    public long getTime()
    {
        return time;
    }
    public long getNumOfRecords()
    {
        return numOfRecords;
    }
    public void addRecords(int numOfRecords)
    {
        this.numOfRecords += numOfRecords;
    }
    public long timeCalculate()
    {
        if (time == 0)
            return 0;
        else return numOfRecords*1000/time;
    }


    public void setConsumedMillis(long time) {
        this.time+=time;
    }
}
