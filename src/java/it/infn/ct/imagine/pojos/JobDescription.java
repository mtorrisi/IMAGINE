/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.infn.ct.imagine.pojos;

/**
 *
 * @author mario
 */
public class JobDescription {
    
    String executable;
    String arguments;
    String output;
    String error;
    String inputFiles;
    String outputFiles;
    int totalCPUCount;
    String spdmVariation;
    int numberOfProcess;
    String jdlRequirements;

    public JobDescription() {
    }

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(String inputFiles) {
        this.inputFiles = inputFiles;
    }

    public String getOutputFiles() {
        return outputFiles;
    }

    public void setOutputFiles(String outputFiles) {
        this.outputFiles = outputFiles;
    }

    public int getTotalCPUCount() {
        return totalCPUCount;
    }

    public void setTotalCPUCount(int totalCPUCount) {
        this.totalCPUCount = totalCPUCount;
    }

    public String getSpdmVariation() {
        return spdmVariation;
    }

    public void setSpdmVariation(String spdmVariation) {
        this.spdmVariation = spdmVariation;
    }

    public String getJDLRequirements() {
        return jdlRequirements;
    }

    public void setJDLRequirements(String jdlRequirements) {
        this.jdlRequirements = jdlRequirements;
    }

    public int getNumberOfProcess() {
        return numberOfProcess;
    }

    public void setNumberOfProcess(int numberOfProcess) {
        this.numberOfProcess = numberOfProcess;
    }
   
    @Override
    public String toString() {
        return "JobDescription{" + "executable=" + executable + ", arguments=" + arguments + ", output=" + output + ", error=" + error + ", inputFiles=" + inputFiles + ", outputFiles=" + outputFiles + ", totalCPUCount=" + totalCPUCount + ", spdmVariation=" + spdmVariation + ", jdlRequirements=" + jdlRequirements + '}';
    }
    
}