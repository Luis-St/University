# Java Syntaxfehler - Code-Beispiele

## 1. Typfehler

### 1.1 Zuweisung falscher Datentypen

```java
public class Test {
    public static void main(String[] args) {
        boolean flag = 42;
    }
}
```
**Erklärung:** In Java müssen Datentypen kompatibel sein. Ein `int`-Wert kann nicht direkt einer `boolean`-Variable zugewiesen werden.

### 1.2 Vergleich inkompatibler Typen

```java
public class Test {
    public static void main(String[] args) {
        String text = "Hello";
        double zahl = 3.14;
        if (text == zahl) {
            System.out.println("gleich");
        }
    }
}
```
**Erklärung:** Verschiedene Datentypen können nicht direkt mit `==` verglichen werden.

### 1.3 Verwendung von Operatoren mit falschen Typen

```java
public class Test {
    public static void main(String[] args) {
        boolean a = true;
        int result = a + 5;
    }
}
```
**Erklärung:** Der `+`-Operator kann nicht zwischen `boolean` und `int` verwendet werden.

### 1.4 Methodenaufrufe mit falschen Datentypen

```java
public class Test {
    public static void main(String[] args) {
        double x = 3.14;
        boolean result = isOdd(x);
    }
	
    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }
}
```
**Erklärung:** Primitive Datentypen wie `double` haben keine `.equals()`-Methode.

## 2. Variable und Scope-Fehler

### 2.1 Verwendung nicht deklarierter Variablen

```java
public class Test {
    public static void main(String[] args) {
        System.out.println(name);
    }
}
```
**Erklärung:** Jede Variable muss vor ihrer Verwendung deklariert werden.

### 2.2 Verwendung nicht initialisierter Variablen

```java
public class Test {
    public static void main(String[] args) {
        int x;
        int y = x + 5;
    }
}
```
**Erklärung:** Lokale Variablen müssen vor ihrer Verwendung initialisiert werden.

### 2.3 Zugriff auf nicht-statische Variablen aus statischem Kontext

```java
public class Test {
    int instanceVar = 10;
    
    public static void main(String[] args) {
        System.out.println(instanceVar);
    }
}
```
**Erklärung:** Aus statischen Methoden kann nicht direkt auf Instanzvariablen zugegriffen werden.

### 2.4 Zugriff auf lokale Variablen außerhalb ihres Gültigkeitsbereichs

```java
public class Test {
    public static void main(String[] args) {
        if (true) {
            int lokalVar = 5;
        }
        System.out.println(lokalVar);
    }
}
```
**Erklärung:** Variablen sind nur innerhalb des Blocks verfügbar, in dem sie deklariert wurden.

## 3. Methodenfehler

### 3.1 Aufruf nicht-statischer Methoden aus statischem Kontext

```java
public class Test {
    public void instanceMethod() {
        System.out.println("Instance method");
    }
    
    public static void main(String[] args) {
        instanceMethod();
    }
}
```
**Erklärung:** Nicht-statische Methoden können nur über Objektinstanzen aufgerufen werden.

### 3.2 Falsche Anzahl von Parametern bei Methodenaufrufen

```java
public class Test {
    public static int add(int a, int b) {
        return a + b;
    }
    
    public static void main(String[] args) {
        int result = add(5);
    }
}
```
**Erklärung:** Die Anzahl der Parameter beim Methodenaufruf muss mit der Methodendefinition übereinstimmen.

### 3.3 Falsche Parametertypen bei Methodenaufrufen

```java
public class Test {
    public static void printMessage(String message) {
        System.out.println(message);
    }
    
    public static void main(String[] args) {
        printMessage(42);
    }
}
```

### 3.4 Fehlende return-Anweisungen in Methoden mit Rückgabewert

```java
public class Test {
    public static int getValue() {
        int x = 5;
      
    }
}
```
**Erklärung:** Methoden mit einem Rückgabetyp (außer `void`) müssen eine `return`-Anweisung enthalten.

### 3.5 Nicht alle Codepfade führen zu einem return

```java
public class Test {
    public static int getValue(boolean flag) {
        if (flag) {
            return 1;
        }
      
    }
}
```
**Erklärung:** Alle möglichen Ausführungspfade müssen zu einer `return`-Anweisung führen.

### 3.6 Rückgabe eines falschen Datentyps

```java
public class Test {
    public static String getNumber() {
        return 42;
    }
}
```
**Erklärung:** Der Rückgabetyp der Methode muss mit dem tatsächlichen Rückgabewert übereinstimmen.

### 3.7 Rückgabe eines Wertes in einer Methode ohne Rückgabe

```java
public class Test {
    public static void printMessage() {
        return "Hello";
    }
}
```
**Erklärung:** Methoden, die als `void` deklariert sind, dürfen keinen Rückgabewert haben.

### 3.8 Verschachtelte Methodendefinitionen

```java
public class Test {
    public static void outerMethod() {
        public static void innerMethod() {
            System.out.println("Inner");
        }
    }
}
```
**Erklärung:** In Java können Methoden nicht innerhalb anderer Methoden definiert werden.

## 4. Kontrollstruktur-Fehler

### 4.1 Verwendung von nicht-boolean Ausdrücken in if-Bedingungen

```java
public class Test {
    public static void main(String[] args) {
        int x = 5;
        if (x) {
            System.out.println("true");
        }
    }
}
```
**Erklärung:** `if`-Bedingungen müssen einen `boolean`-Ausdruck enthalten.

### 4.2 Verwendung von nicht-boolean Ausdrücken in while-Schleifen

```java
public class Test {
    public static void main(String[] args) {
        while (3) {
            System.out.println("loop");
        }
    }
}
```
**Erklärung:** `while`-Schleifen benötigen einen `boolean`-Ausdruck als Bedingung.

## 5. Zugriffsmodifikator-Fehler

### 5.1 Zugriff auf private Variablen von außen

```java
class MyClass {
    private int secret = 42;
}

public class Test {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        System.out.println(obj.secret);
    }
}
```
**Erklärung:** `private` Variablen sind nur innerhalb der eigenen Klasse zugänglich.

### 5.2 Aufruf nicht-statischer Elemente aus statischem Kontext

```java
public class Test {
    int instanceVar = 10;
    
    public static void main(String[] args) {
        System.out.println(instanceVar);
    }
}
```
**Erklärung:** Statische Methoden können nicht direkt auf Instanzvariablen zugreifen.

## 6. Array-Fehler

### 6.1 Falsche Array-Deklaration

```java
public class Test {
    public static void main(String[] args) {
        int[5] numbers;
    }
}
```
**Erklärung:** Die Array-Größe wird bei der Initialisierung, nicht bei der Deklaration angegeben.

### 6.2 Falsche Array-Initialisierung

```java
public class Test {
    public static void main(String[] args) {
        int[] numbers = new int[3] {1, 2, 3};
    }
}
```
**Erklärung:** Bei der Array-Initialisierung mit Werten darf die Größe nicht angegeben werden.

## 7. Klammer und Syntax-Fehler

### 7.1 Fehlende geschweifte Klammern

```java
public class Test {
    public static void main(String[] args) {
        if (true) {
            System.out.println("test");
      
}
```
**Erklärung:** Jede öffnende geschweifte Klammer braucht eine entsprechende schließende Klammer.

### 7.2 Fehlende Semikolons

```java
public class Test {
    public static void main(String[] args) {
        int x = 5
        System.out.println(x);
    }
}
```
**Erklärung:** Java-Anweisungen müssen mit einem Semikolon beendet werden.

### 7.3 Fehlende runde Klammern

```java
public class Test {
    public static void main(String[] args) {
        if true {
            System.out.println("test");
        }
    }
}
```
**Erklärung:** `if`-Bedingungen müssen in runde Klammern gesetzt werden.
