public class Prefix{

   String preL = "";
   String preR = "";
   
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
}