# 繼承
- 使用extends關鍵詞: `public class SavingAccount extends Account{...}`
- 被繼承的class稱為superclass，新的class稱為subclass。
- subclass繼承superclass的結構與方法，但superclass中被設為private的參數，無法在subclass直接被存取。
  - 以`public class B extends A{...}`來說，當創建實體化的B物件時，內部同時創建了一個A物件；但對於該A物件，B的call屬於來自"外部"，因此無法存取private parameter。
  - 繼承後可以進行擴充和覆寫(override)，注意constructor不會被繼承(因為同名function就指向創建superclass)要再自己定義一個與subclass同名的constructor。
  - 使用`super.{functionName}()`取superclass中被複寫的方法；`super({params})`則相當於superclass的 constructor。
  - 編譯器規定subclass的constructor中第一行必須有`super();`
- 當superclass有修改/維護需求，需盡量減少subclass受影響(連帶需要修改)的程度，盡量不碰既存方法的名字，可以編寫多載函式(overloading)並重新導向。
- ***polymorphism***: 例如同一個superclass的Array裡可以放不同subclass物件，呼叫時再根據存儲的類別呼叫對應的方法；此概念透過dynamic binding實現，缺點是較吃效能。
  - 不過對於現代的處理器來說，dynamic binding已經和喝水一樣了。

# 抽象類別
- 使用abstract關鍵字: 
  - `public abstract class AbstractClass{`
    - `public abstract type abstractFunction();}`
- 抽象方法是沒有`{}`的方法，意思是交給subclass把它寫完。
  - 繼承抽象類別的subclass，必須implement所有inherited abstract methods
  - 只要包含一個抽象方法，就必須定義為抽象類別。
  - subclass可以繼續當抽象類別；implement方式可以就照抄一遍，讓抽象方法維持抽象。
- 抽象類別無法使用`new`關鍵字實體化(instantiate)

# interface
- 使用interface關鍵字，其內只有抽象方法:
  - `public interface Interface{`
    - `public abstract type abstractMethod();...}` 
- 最初是為了像C++一樣實現多重繼承e.g. `public class A extends B,C,...{};`而發展。
- 實作interface使用`implements`關鍵字: `public class Class inplements Interface{}`