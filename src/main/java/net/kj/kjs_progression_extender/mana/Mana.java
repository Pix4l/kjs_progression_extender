package net.kj.kjs_progression_extender.mana;

public class Mana implements IMana{
    private double mana = 0;

    @Override
    public double getMana() {
        return mana;
    }

    @Override
    public void setMana(double amount) {
        mana = Math.max(0, amount);
    }

    @Override
    public void addMana(double amount) {
        setMana(mana + amount);
    }

    @Override
    public void removeMana(double amount) {
        setMana(mana - amount);
    }

    public void copyFrom(Mana other) {
        this.mana = other.mana;
    }
}
