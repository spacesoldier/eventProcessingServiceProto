package com.leaderhackdemo.servicerequests.features.parsedataset.model.manipulation;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data @Builder
public class DataFieldDescriptor {
    private int fieldNumber;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataFieldDescriptor planNodeStartupCost = (DataFieldDescriptor) o;
        return  Objects.equals(this.fieldNumber, planNodeStartupCost.fieldNumber) &&
                Objects.equals(this.name, planNodeStartupCost.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldNumber, name);
    }
}
