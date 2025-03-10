package br.com.danilo.parallel.model;


public class CommonData {

    private Car car;
    private Table table;
    private Chair chair;
    private Pen pen;

    public Car getCar() {
        return car;
    }

    public synchronized void setCar(Car car) {
        this.car = car;
    }

    public Table getTable() {
        return table;
    }

    public synchronized void setTable(Table table) {
        this.table = table;
    }

    public Chair getChair() {
        return chair;
    }

    public synchronized void setChair(Chair chair) {
        this.chair = chair;
    }

    public Pen getPen() {
        return pen;
    }

    public synchronized void setPen(Pen pen) {
        this.pen = pen;
    }

    @Override
    public String toString() {
        return "CommonData{" +
                "car=" + car +
                ", table=" + table +
                ", chair=" + chair +
                ", pen=" + pen +
                '}';
    }
}
