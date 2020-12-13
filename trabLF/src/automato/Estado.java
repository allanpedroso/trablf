package automato;

class Estado {
   private String nome;
   private static int num = -1;
   Estado() {
   num++;
   nome = "Q"+String.valueOf(num);
   }
  
   
   
   public String getNome() {
      return nome;
   }
   
   public void setNome(String nome) {
      this.nome = nome;
   }
}
