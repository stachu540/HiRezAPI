[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ee16a92996a3425d87403780aa18f316)](https://www.codacy.com/app/stachu540/HiRezAPI?utm_source=github.com&utm_medium=referral&utm_content=stachu540/HiRezAPI&utm_campaign=badger)
[![Build Status](https://travis-ci.org/stachu540/HiRezAPI.svg)](https://travis-ci.org/stachu540/HiRezAPI)
[![codecov](https://codecov.io/gh/stachu540/HiRezAPI/branch/master/graph/badge.svg)](https://codecov.io/gh/stachu540/HiRezAPI)
[![Javadocs](https://img.shields.io/badge/Javadoc-v2.0-brightgreen.svg)](https://jitpack.io/com/github/stachu540/HiRezAPI/v2.0/javadoc/)
[![Release](https://jitpack.io/v/stachu540/HiRezAPI.svg)](https://jitpack.io/#stachu540/HiRezAPI)

# HiRezAPI

Java-Based API Wrapper for Hi-Rez Studios games. Currently supports:
 * [Smite](https://smitegame.com/)
 * [Paladins](https://paladinsgame.com/)
 
## Features

All features will be on [Projects](https://github.com/stachu540/HiRezAPI/projects) tab:
 * [Core of Script](https://github.com/stachu540/HiRezAPI/projects/3)
 * [Smite API](https://github.com/stachu540/HiRezAPI/projects/1)
 * [Paladins API](https://github.com/stachu540/HiRezAPI/projects/2)

## Usage

1. To access the APIs you'll need your own set of credentials which consist of a Developer ID (**devId**) and an Authentication Key (**authKey**). To getting those credentials, you need filling form on this link: 
https://fs12.formsite.com/HiRez/form48/secure_index.html

2. Choose your Build
#### Maven
Add to `pom.xml` build.
```xml
<repositories>
    ...
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
...
<dependencies>
    ...
    <dependency>
          <groupId>com.github.stachu540</groupId>
          <artifactId>HiRezAPI</artifactId>
          <version>2.0</version>
     </dependency>
</dependencies>
...
```
#### Gradle
Add to `build.gradle` build.
```groovy
...
repositories {
  ...
  maven {
    url  "https://jitpack.io"
  }
}
...
dependencies {
  ...
  compile "com.github.stachu540:HiRezAPI:2.0"
}
...
```

4. To starting using script try to using example below:
    
    For examples I will use example credentials
    **DevId**: 1004
    **AuthKey**: 23DF3C7E9BD14D84BF892AD206B6755C
    
    a. For Smite **PC** i want getting player info for "HiRezTina"
```java
package example;

import com.github.stachu540.hirezapi.HiRezAPI; // Main class
import com.github.stachu540.hirezapi.api.Smite; // for importing enum platforms

class SmitePlayer {
    public static void main(String[] args) {
        HiRezAPI api = new HiRezAPI("1004", "23DF3C7E9BD14D84BF892AD206B6755C");
        Smite smite = api.getFor(com.github.stachu540.hirezapi.enums.url.Smite.PC);
        // cause data returning as JSONArray i will use toJsonArray() method
        System.out.println(smite.getPlayer("HiRezTina"));
    }
}
```
    
    b. For Paladins **XBOX** i want know about server status.
    
    
```java
package example;

import com.github.stachu540.hirezapi.HiRezAPI; // Main class
import com.github.stachu540.hirezapi.api.Paladins; // for importing enum platforms

class PaladinsStatusServer {
    public static void main(String[] args) {
        HiRezAPI api = new HiRezAPI("1004", "23DF3C7E9BD14D84BF892AD206B6755C");
        Paladins paladins = api.getFor(com.github.stachu540.hirezapi.enums.url.Paladins.XBOX);
        // cause data returning as JSONArray i will use toJsonArray() method
        System.out.println(mydata.getServerStatus());
    }
}
```

For some more detail's, check out [source documentation](https://stachu540.github.io/HiRezAPI/).

## Question and suggestions
Any more questions or some suggestions changes are welcome. Don't be shy, just open your [Issues](https://github.com/stachu540/HiRezAPI/issues) if you have some problem. [Pull Request's](https://github.com/stachu540/HiRezAPI/pulls) and any other contribution are welcome.