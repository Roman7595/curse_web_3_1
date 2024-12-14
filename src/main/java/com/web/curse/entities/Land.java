package com.web.curse.entities;

import com.web.curse.entities.enums.Meter;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "lands")
public class Land extends BaseEntity{
    private String number;
    private int sizeInArs;
    private Meter electricMeter;
    private Client client;
    private Set<TariffPayment> tariffPayments;
    private Set<MembershipFeePayment> membershipFeePayments;
    private Set<TargetFeePayment> targetFeePayments;

    public Land(String number, int sizeInArs, Meter electricMeter) {
        this.number = number;
        this.sizeInArs = sizeInArs;
        this.electricMeter = electricMeter;
    }

    @Column(name = "number", unique = true,nullable = false)
    public String getNumber() {
        return number;
    }

    @Column(name = "size_in_ars", nullable = false)
    public int getSizeInArs() {
        return sizeInArs;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "electric_meter_type", nullable = false)
    public Meter getElectricMeter() {
        return electricMeter;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    public Client getClient() {
        return client;
    }

    @OneToMany(mappedBy = "land", fetch = FetchType.LAZY)
    public Set<TariffPayment> getTariffPayments() {
        return tariffPayments;
    }

    @OneToMany(mappedBy = "land",fetch = FetchType.LAZY)
    public Set<MembershipFeePayment> getMembershipFeePayments() {
        return membershipFeePayments;
    }

    @OneToMany(mappedBy = "land",fetch = FetchType.LAZY)
    public Set<TargetFeePayment> getTargetFeePayments() {
        return targetFeePayments;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSizeInArs(int sizeInArs) {
        this.sizeInArs = sizeInArs;
    }

    public void setElectricMeter(Meter electricMeter) {
        this.electricMeter = electricMeter;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setTariffPayments(Set<TariffPayment> tariffPayments) {
        this.tariffPayments = tariffPayments;
    }

    public void setMembershipFeePayments(Set<MembershipFeePayment> membershipFeePayments) {
        this.membershipFeePayments = membershipFeePayments;
    }

    public void setTargetFeePayments(Set<TargetFeePayment> targetFeePayments) {
        this.targetFeePayments = targetFeePayments;
    }

    protected Land() {
    }
}
