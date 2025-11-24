## 1

### 1 Multiplikation

Basisfall: $\text{mult}(1,n)=n$

Rekursionsfall: $\text{mult}(m+1,n)=\text{mult}(m,n)+n$

### 2 Differenz

Basisfall: $\text{diff}(m,0)=m$

Rekursionsfall: $\text{diff}(m,n+1)=\text{diff}(m,n)-1$

### 3 Absolute Differenz

Rekursionsfall:  $\text{absdiff}(m,n)=\text{diff}(m,n)+\text{diff}(n,m)$

### 4 Ist ungerade

Basisfall: $\text{isOdd}(0)=0$

Rekursionsfall: $\text{isOdd}(n+1)= 1-\text{isOdd}(n)$

### 5 min()

Verwendet [sign(n)](../../../../Vorlesung/Vorlesung11%20Rekursion.md#sign())

Rekursionsfall: $\text{min}(m,n)=sign(\text{diff}(m,n))*n + sign(\text{diff}(n,m))*m$


## 2 Ackermann

```java
public class Ackermann {
  public static int ack(int x, int y){
    if(x==0){
      return y+1;
    }else if(y==0){
      return ack(x-1,1);
    }else{
      return ack(x-1,ack(x,y-1));
    }
  }

  public static void main(String[] args){
    System.out.println(ack(3,2));
    System.out.println(ack(3,3));
    System.out.println(ack(4,1));
  }
}
```


