package pl.edu.p.ftims.sidejobs.model;

import java.util.UUID;

public class JobApplication {
    private String employerId;
    private String employeeId;
    private String jobOfferId;
    private String applicationID;

    private String employeeMail;

    public String getEmployeeMail() {
        return employeeMail;
    }

    public JobApplication(String employerId, String employeeId, String jobOfferId, String employeeMail) {
        this.employerId = employerId;
        this.employeeId = employeeId;
        this.jobOfferId = jobOfferId;
        this.employeeMail = employeeMail;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getEmployerId() {
        return employerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getJobOfferId() {
        return jobOfferId;
    }

    public String getApplicationID() {
        return applicationID;
    }
}



