package org.uit.rit.entity;


import java.util.Map;

public class NonMonetary extends Active {
    int primary;
    int residual;
    int valuation;
    Map<String, Object> measurement;

    public NonMonetary(int id, String name, int primary, int residual, int valuation, Map<String, Object> measurement) {
        super(id, name);
        this.primary = primary;
        this.residual = residual;
        this.valuation = valuation;
        this.measurement = measurement;
    }

    public int getPrimary() {
        return primary;
    }

    public void setPrimary(int primary) {
        this.primary = primary;
    }

    public int getResidual() {
        return residual;
    }

    public void setResidual(int residual) {
        this.residual = residual;
    }

    public int getValuation() {
        return valuation;
    }

    public void setValuation(int valuation) {
        this.valuation = valuation;
    }

    public Map<String, Object> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Map<String, Object> measurement) {
        this.measurement = measurement;
    }

}
