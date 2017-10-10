[![Build Status](https://travis-ci.org/stachu540/HiRezAPI.svg)](https://travis-ci.org/stachu540/HiRezAPI)
[![Javadocs](http://javadoc.io/badge/pl.stachuofficial/HiRezAPI.svg?color=brightgreen)](http://javadoc.io/doc/pl.stachuofficial/HiRezAPI)

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

2. For next step. Add dependency to `pom.xml` build.
```xml
<dependency>
    <groupId>pl.stachuofficial</groupId>
    <artifactId>HiRezAPI</artifactId>
    <version>1.0</version>
</dependency>
```

3. To starting using script try to using example below:
    
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