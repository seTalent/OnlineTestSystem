package com.lovejava.pojo;

public class Paper {
    private String major;

    private Integer singleNumber;

    private Integer multiNumber;

    private Integer judgeNumber;

    private Integer saqNumber;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Integer getSingleNumber() {
        return singleNumber;
    }

    public void setSingleNumber(Integer singleNumber) {
        this.singleNumber = singleNumber;
    }

    public Integer getMultiNumber() {
        return multiNumber;
    }

    public void setMultiNumber(Integer multiNumber) {
        this.multiNumber = multiNumber;
    }

    public Integer getJudgeNumber() {
        return judgeNumber;
    }

    public void setJudgeNumber(Integer judgeNumber) {
        this.judgeNumber = judgeNumber;
    }

    public Integer getSaqNumber() {
        return saqNumber;
    }

    public void setSaqNumber(Integer saqNumber) {
        this.saqNumber = saqNumber;
    }

    public Paper(String major, Integer singleNumber, Integer multiNumber, Integer judgeNumber, Integer saqNumber) {
        this.major = major;
        this.singleNumber = singleNumber;
        this.multiNumber = multiNumber;
        this.judgeNumber = judgeNumber;
        this.saqNumber = saqNumber;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "major='" + major + '\'' +
                ", singleNumber=" + singleNumber +
                ", multiNumber=" + multiNumber +
                ", judgeNumber=" + judgeNumber +
                ", saqNumber=" + saqNumber +
                '}';
    }
}