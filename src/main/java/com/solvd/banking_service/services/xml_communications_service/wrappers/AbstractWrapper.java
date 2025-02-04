package com.solvd.banking_service.services.xml_communications_service.wrappers;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
@XmlTransient
public abstract class AbstractWrapper<T> {
    @XmlTransient
    protected List<T> entities = new ArrayList<>();

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities.clear();
        this.entities.addAll(entities);
    }
}