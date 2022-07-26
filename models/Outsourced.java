package models;
/**Outsourced version of Part*/
public class Outsourced extends Part{
    private String companyName;
    /**Outsourced constructor*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id,name,price,stock,min,max);
        this.companyName = companyName;
    }
    /** Sets the company name for your part.
     * @param companyName The name of the company that makes your part*/
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    /** Gets your company name
     * @return companyName The name of the company that makes your part*/
    public String getCompanyName(){
        return companyName;
    }

}
