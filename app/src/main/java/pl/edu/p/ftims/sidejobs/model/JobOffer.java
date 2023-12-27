package pl.edu.p.ftims.sidejobs.model;

import java.util.UUID;

public class JobOffer {
    String jobName;
    String jobDesc;
    int requiredAge;
    String  requiredExperience;
    int hourlyPay;
    int numberOfPlaces;
    String  jobId;

    public JobOffer(){};

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public void setRequiredAge(int requiredAge) {
        this.requiredAge = requiredAge;
    }

    public void setRequiredExperience(String requiredExperience) {
        this.requiredExperience = requiredExperience;
    }

    public void setHourlyPay(int hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    String userID;

    public void setJobId(String  jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public int getRequiredAge() {
        return requiredAge;
    }

    public String  getRequiredExperience() {
        return requiredExperience;
    }

    public int getHourlyPay() {
        return hourlyPay;
    }

    public String  getJobId() {
        return jobId;
    }

    public String getUserID() {
        return userID;
    }

    public JobOffer(String jobName, String jobDesc, int requiredAge, String requiredExperience, int hourlyPay, String  userID, int numberPlaces) {
        this.jobName = jobName;
        this.jobDesc = jobDesc;
        this.requiredAge = requiredAge;
        this.requiredExperience = requiredExperience;
        this.hourlyPay = hourlyPay;
        this.userID = userID;
        this.numberOfPlaces = numberPlaces;
    }
}
