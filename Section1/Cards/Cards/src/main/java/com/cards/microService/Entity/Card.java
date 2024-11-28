package com.cards.microService.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor @ToString
@Entity
public class Card extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private long cardId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name="card_type")
    private String cardType;

    @Column(name = "total_limit")
    private long totalLimit;

    @Column(name="amount_used")
    private long amountUsed;

    @Column(name = "available_amount")
    private long availableAmount;

}
