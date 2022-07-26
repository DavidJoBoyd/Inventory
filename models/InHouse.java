package models;
/**In-house version of part*/
public class InHouse extends Part {
    private int machineId;
    /**In-house part constructor*/
    public InHouse(int id, String name, double price,int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    /** Sets the machineId
     * @param machineId The machine id for you in house part*/
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
    /** Gets the machineId
     * @return machineId The machine id of your in house part*/
    public int getMachineId(){
        return machineId;
    }
}
