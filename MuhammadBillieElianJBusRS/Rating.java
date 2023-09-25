package MuhammadBillieElianJBusRS;


/**
 * Write a description of class Rating here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rating {
    private long count;
    private long total;

    public Rating(){
        this.total = 0;
        this.count = 0;
    }
    
    public void insert(int rating){
        this.total=this.total+rating;
        this.count++;
    }   
    
    public double getAverage(){
        double rating;
        
        if(this.count == 0){
            return 0;
        }
        
        rating = this.total / this.count;
        return rating;
    }
    
    public long getCount(){
        return this.count;
    }
    
    public long getTotal(){
        return this.total;
    }
    
    public String toString(){
        return  "Count: " + count +" Total: " + total;
    }
}
