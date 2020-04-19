package com.dvaltrick.flipmemory.models;

public class StatsResponse {
    private Card card;
    private Long total;
    private Long correct;
    private Long incomplete;
    private Long wrong;

    public StatsResponse(Card card, Long total, Long correct, Long incomplete, Long wrong) {
        this.card = card;
        this.total = total;
        this.correct = correct;
        this.incomplete = incomplete;
        this.wrong = wrong;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCorrect() {
        return correct;
    }

    public void setCorrect(Long correct) {
        this.correct = correct;
    }

    public Long getIncomplete() {
        return incomplete;
    }

    public void setIncomplete(Long incomplete) {
        this.incomplete = incomplete;
    }

    public Long getWrong() {
        return wrong;
    }

    public void setWrong(Long wrong) {
        this.wrong = wrong;
    }
}