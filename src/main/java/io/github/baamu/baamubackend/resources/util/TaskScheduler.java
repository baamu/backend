package io.github.baamu.baamubackend.resources.util;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * @author oshan
 */
public class TaskScheduler {

    private static TaskScheduler taskScheduler;

    private List<TimerTask> timedTasks;

    private Date downloadStaringTime;
    private Date downloadEndTime;

    private Date systemCheckTime;

    private int nextDownloadIn = 60 * 24; //in minutes  - 24 hrs
    private int downloadDuration = 60 * 12; //duration in minutes - 12 hrs

    private int nextSystemCheckIn = 60 * 24; //next system check will be scheduled in 24 hours

    private DownloadManager downloadManager;

    private SystemChecker systemChecker;

    private TaskScheduler() {
        downloadManager = new DownloadManager();
        systemChecker = new SystemChecker();
    }

    public static TaskScheduler getInstance() {
        if(taskScheduler == null)
            taskScheduler = new TaskScheduler();

        return taskScheduler;
    }

    public Date getDownloadStaringTime() {
        return downloadStaringTime;
    }

    public void setDownloadStaringTime(Date downloadStaringTime) {
        this.downloadStaringTime = downloadStaringTime;
        //set the download starting task
    }

    public Date getDownloadEndTime() {
        return downloadEndTime;
    }

    public void setDownloadEndTime(Date downloadEndTime) {
        this.downloadEndTime = downloadEndTime;
        //set the download ending task
    }

    public DownloadManager getDownloadManager() {
        return downloadManager;
    }

    public void setDownloadManager(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }

    public SystemChecker getSystemChecker() {
        return systemChecker;
    }

    public void setSystemChecker(SystemChecker systemChecker) {
        this.systemChecker = systemChecker;
    }

    public Date getSystemCheckTime() {
        return systemCheckTime;
    }

    public void setSystemCheckTime(Date systemCheckTime) {
        this.systemCheckTime = systemCheckTime;
    }

    public int getNextDownloadIn() {
        return nextDownloadIn;
    }

    public void setNextDownloadIn(int nextDownloadIn) {
        this.nextDownloadIn = nextDownloadIn;
    }

    public int getDownloadDuration() {
        return downloadDuration;
    }

    public void setDownloadDuration(int downloadDuration) {
        this.downloadDuration = downloadDuration;
    }

    public int getNextSystemCheckIn() {
        return nextSystemCheckIn;
    }

    public void setNextSystemCheckIn(int nextSystemCheckIn) {
        this.nextSystemCheckIn = nextSystemCheckIn;
    }
}
