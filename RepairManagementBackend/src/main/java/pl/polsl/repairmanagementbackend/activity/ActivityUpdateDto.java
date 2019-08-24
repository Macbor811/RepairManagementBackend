package pl.polsl.repairmanagementbackend.activity;

public class ActivityUpdateDto {

    private Integer sequenceNum;

    private String description;
    private String result;
    private String status;
    private Integer workerId;
    private String type;

    public ActivityUpdateDto(){}
    public ActivityUpdateDto(Integer sequenceNum, String description, String result, String status, Integer workerId, String type) {
        this.sequenceNum = sequenceNum;
        this.description = description;
        this.result = result;
        this.status = status;
        this.workerId = workerId;
        this.type = type;
    }


    public Integer getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(Integer sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
