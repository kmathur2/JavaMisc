public class HelloWorld{

     public static void main(String []args){
        System.out.println("Hello World");
        int[] V={1,5,8,9,10,17,17,20};
        int[] M={-999,-999,-999,-999,-999,-999,-999,-999};
        System.out.println(cutRod(8,V,M));
     }
     
    static int cutRod(int i,int[] V, int[] m){
	    
	if(i==1){
        m[1]=V[0];
	    return V[0];
    }

	for(int k=1; k<(i-2); k++){
		if (m[i-k] != -999){
			m[k]=max(V[k], cutRod(k,V,m) + m[i-k]);      
		}else{
		    m[k]=max(V[k], cutRod(k,V,m) + cutRod(i-k,V,m));
		}
	}
	
	return highest(m);

}

    static int max(int a,int b){
        if(a>b)
            return a;
        return b;
    }
    
    static int highest(int[] a){
        int max=-999;
        for(int i=0;i < a.length;i++){
            if(a[i]>max)
                max=a[i];
        }
        
        return max;
    }

}
