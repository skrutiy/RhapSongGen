public class Prefix{

   private String preL = "";
   private String preR = "";
   
   public Prefix(){
      preL = "\n";
      preR = "\n";
   }
   
   public Prefix(String str){
      preL = str;
   }
   
   public Prefix(String strL, String strR){
      preL = strL;
      preR = strR;
   }
   
   public String getL(){
      return preL;
   }
   
   public String getR(){
      return preR;
   }
   
   public void setL(String str){
      preL = str;
   }
   
   public void setR(String str){
      preR = str;
   }
   

   /*/public int compareTo(Object o){
      System.out.println("In Compare");
      if (o instanceof Prefix){
         Prefix pre = (Prefix) o;
         if(this.preL.equals(pre.getL()) && this.preR.equals(pre.getR())){
            return 0;
         }
         else{
            return 1;
         }
      }
      else{
         return 1;
      }
   }*/
   
   @Override
   public int hashCode(){
      String str = (this.preL.toLowerCase() + "" + this.preR.toLowerCase());
      return str.hashCode();
   }
   
   @Override
   public boolean equals(Object o){
      if (o instanceof Prefix) {
         Prefix pre = (Prefix) o;
         return(this.preL.toLowerCase().equals(pre.getL().toLowerCase()) && this.preR.toLowerCase().equals(pre.getR().toLowerCase()));
      }
      else{
         return false;
      }
   }
}