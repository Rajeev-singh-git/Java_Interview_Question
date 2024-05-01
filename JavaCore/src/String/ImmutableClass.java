package String;

final class ImmutableClass {

    private int i;

    ImmutableClass(int i){
        this.i=i;
    }

    public ImmutableClass modify(int i){
        if(this.i==i){
            return this;
        }else{
            return new ImmutableClass(i);
        }
   }

   public static void main(String []args){
        ImmutableClass i1 = new ImmutableClass(10);
        ImmutableClass i2 = i1.modify(100);
        ImmutableClass i3 = i1.modify(10);

        System.out.println(i1==i2);  // false
        System.out.println(i1==i3);  // true
   }



}
