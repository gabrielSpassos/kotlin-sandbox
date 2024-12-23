# Kotlin Java Core Generated POC

> This poc is to show the java code generated from kotlin code

## How to do

1. On the IntelliJ menu select tools > Kotlin > Show Kotlin Bytecode
2. Once the Kotlin Bytecode window is open, click on the Decompile button

## Data class
* Kotlin
```kotlin
data class Person(val name: String, val lastName: String)
```

* Java
```java
package com.gabrielspassos;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 0, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"},
   d2 = {"Lcom/gabrielspassos/Person;", "", "name", "", "lastName", "(Ljava/lang/String;Ljava/lang/String;)V", "getLastName", "()Ljava/lang/String;", "getName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "kotlin-bytecode-poc"}
)
public final class Person {
   @NotNull
   private final String name;
   @NotNull
   private final String lastName;

   public Person(@NotNull String name, @NotNull String lastName) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(lastName, "lastName");
      super();
      this.name = name;
      this.lastName = lastName;
   }

   @NotNull
   public final String getName() {
      return this.name;
   }

   @NotNull
   public final String getLastName() {
      return this.lastName;
   }

   @NotNull
   public final String component1() {
      return this.name;
   }

   @NotNull
   public final String component2() {
      return this.lastName;
   }

   @NotNull
   public final Person copy(@NotNull String name, @NotNull String lastName) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(lastName, "lastName");
      return new Person(name, lastName);
   }

   // $FF: synthetic method
   public static Person copy$default(Person var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.name;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.lastName;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "Person(name=" + this.name + ", lastName=" + this.lastName + ')';
   }

   public int hashCode() {
      int result = this.name.hashCode();
      result = result * 31 + this.lastName.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Person)) {
         return false;
      } else {
         Person var2 = (Person)other;
         if (!Intrinsics.areEqual(this.name, var2.name)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.lastName, var2.lastName);
         }
      }
   }
}
```

## Class

* Kotlin

```kotlin
class Animal(private val name: String)
```

* Java

```java
package com.gabrielspassos;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 0, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"},
   d2 = {"Lcom/gabrielspassos/Animal;", "", "name", "", "(Ljava/lang/String;)V", "kotlin-bytecode-poc"}
)
public final class Animal {
   @NotNull
   private final String name;

   public Animal(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      super();
      this.name = name;
   }
}
```

## Object

* Kotlin

```kotlin
package com.gabrielspassos

object Singleton {
    val name = "MyCustomSingleton"
}
```

* Java

```java
package com.gabrielspassos;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 0, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
   d2 = {"Lcom/gabrielspassos/Singleton;", "", "()V", "name", "", "getName", "()Ljava/lang/String;", "kotlin-bytecode-poc"}
)
public final class Singleton {
   @NotNull
   public static final Singleton INSTANCE = new Singleton();
   @NotNull
   private static final String name = "MyCustomSingleton";

   private Singleton() {
   }

   @NotNull
   public final String getName() {
      return name;
   }
}
```

## Data class with nullable attribute

* Kotlin

```kotlin
package com.gabrielspassos

data class Game(val name: String, val owner: String?)
```

* Java

```java
package com.gabrielspassos;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 0, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"},
   d2 = {"Lcom/gabrielspassos/Game;", "", "name", "", "owner", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getOwner", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "kotlin-bytecode-poc"}
)
public final class Game {
   @NotNull
   private final String name;
   @Nullable
   private final String owner;

   public Game(@NotNull String name, @Nullable String owner) {
      Intrinsics.checkNotNullParameter(name, "name");
      super();
      this.name = name;
      this.owner = owner;
   }

   @NotNull
   public final String getName() {
      return this.name;
   }

   @Nullable
   public final String getOwner() {
      return this.owner;
   }

   @NotNull
   public final String component1() {
      return this.name;
   }

   @Nullable
   public final String component2() {
      return this.owner;
   }

   @NotNull
   public final Game copy(@NotNull String name, @Nullable String owner) {
      Intrinsics.checkNotNullParameter(name, "name");
      return new Game(name, owner);
   }

   // $FF: synthetic method
   public static Game copy$default(Game var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.name;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.owner;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "Game(name=" + this.name + ", owner=" + this.owner + ')';
   }

   public int hashCode() {
      int result = this.name.hashCode();
      result = result * 31 + (this.owner == null ? 0 : this.owner.hashCode());
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Game)) {
         return false;
      } else {
         Game var2 = (Game)other;
         if (!Intrinsics.areEqual(this.name, var2.name)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.owner, var2.owner);
         }
      }
   }
}
```

## Using elvis operator

* Kotlin

```kotlin
package com.gabrielspassos

class GameService {

    fun getGame(owner: String?): Game? {
        return if (owner == "Gabriel") {
            Game("Game 1", owner)
        } else {
            null
        }
    }

    fun updateGameOwner(game: Game?, newOwner: String?): Game? {
        return game?.copy(owner = newOwner)
    }

    fun updateGameByOwner(oldOwner: String, newOwner: String): Game? {
        return getGame(oldOwner)?.let {
            updateGameOwner(it, newOwner)
        }
    }
}
```

* Java

```java
package com.gabrielspassos;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 0, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006J\u001c\u0010\n\u001a\u0004\u0018\u00010\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0006¨\u0006\f"},
   d2 = {"Lcom/gabrielspassos/GameService;", "", "()V", "getGame", "Lcom/gabrielspassos/Game;", "owner", "", "updateGameByOwner", "oldOwner", "newOwner", "updateGameOwner", "game", "kotlin-bytecode-poc"}
)
public final class GameService {
   @Nullable
   public final Game getGame(@Nullable String owner) {
      return Intrinsics.areEqual(owner, "Gabriel") ? new Game("Game 1", owner) : null;
   }

   @Nullable
   public final Game updateGameOwner(@Nullable Game game, @Nullable String newOwner) {
      return game != null ? Game.copy$default(game, (String)null, newOwner, 1, (Object)null) : null;
   }

   @Nullable
   public final Game updateGameByOwner(@NotNull String oldOwner, @NotNull String newOwner) {
      Intrinsics.checkNotNullParameter(oldOwner, "oldOwner");
      Intrinsics.checkNotNullParameter(newOwner, "newOwner");
      Game it = this.getGame(oldOwner);
      Game var10000;
      if (it != null) {
         int var6 = 0;
         var10000 = this.updateGameOwner(it, newOwner);
      } else {
         var10000 = null;
      }

      return var10000;
   }
}

```