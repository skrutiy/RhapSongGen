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
      System.out.println("In hashcode");
      String str = (this.preL + "" + this.preR);
      return str.hashCode();
   }
   
   @Override
   public boolean equals(Object o){
      System.out.println("In equals");
      if (o instanceof Prefix) {
         Prefix pre = (Prefix) o;
         return(this.preL.equals(pre.getL()) && this.preR.equals(pre.getR()));
      }
      else{
         return false;
      }
   }
}