package com.solvd.bankingservice.repo.impl.xml.wrappers;

import com.solvd.bankingservice.models.account.Card;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadym Spitsyn
 * @created 2025-02-04
 */
@XmlRootElement(name = "banking_service")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardsWrapper extends AbstractWrapper<Card> {

    @XmlElementWrapper(name = "cards")
    @XmlElement(name = "card")
    private List<Card> cardList = new ArrayList<>();

    public CardsWrapper() {}

    @Override
    public List<Card> getEntities() {
        return cardList;
    }

    @Override
    public void setEntities(List<Card> entities) {
        this.cardList.clear();
        this.cardList.addAll(entities);
    }
}
