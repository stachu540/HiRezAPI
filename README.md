[![Build Status](https://travis-ci.org/stachu540/HiRezAPI.svg)](https://travis-ci.org/stachu540/HiRezAPI)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ee16a92996a3425d87403780aa18f316)](https://www.codacy.com/app/stachu540/HiRezAPI?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=stachu540/HiRezAPI&amp;utm_campaign=Badge_Grade)
[![Download](https://api.bintray.com/packages/stachu540/Java/HiRezAPI/images/download.svg) ](https://bintray.com/stachu540/Java/HiRezAPI/_latestVersion)
##### Javadocs
| Package | Javadoc |
|---|---|
| Common | [![Javadocs](http://javadoc.io/badge/com.github.stachu540/HiRezApi-common.svg)](http://javadoc.io/doc/com.github.stachu540/HiRezApi-common) |
| Smite | [![Javadocs](http://javadoc.io/badge/com.github.stachu540/HiRezApi-smite.svg)](http://javadoc.io/doc/com.github.stachu540/HiRezApi-smite) |
| Paladins | [![Javadocs](http://javadoc.io/badge/com.github.stachu540/HiRezApi-paladins.svg)](http://javadoc.io/doc/com.github.stachu540/HiRezApi-paladins) |
| Core | [![Javadocs](http://javadoc.io/badge/com.github.stachu540/HiRezApi-core.svg)](http://javadoc.io/doc/com.github.stachu540/HiRezApi-core) |

# HiRezAPI
Java-Based API Wrapper for Hi-Rez Studios games. Currently supports:
 * [Smite](https://smitegame.com/)
 * [Paladins](https://paladins.com/)
 
## Features

All features will be on [Project](https://github.com/stachu540/HiRezAPI/projects/4) tab

## Getting Started

### Request API access
To access the APIs you'll need your own set of credentials which consist of a Developer ID (devId) and an Authentication Key (authKey). To getting those credentials, you need filling form on this link: https://fs12.formsite.com/HiRez/form48/secure_index.html

### Sample supported builds

#### Maven
Add to `pom.xml` build.
```xml
<repositories>
    <repository>
        <id>jcenter</id>
        <url>https://jcenter.bintray.com</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
          <groupId>com.github.stachu540</groupId>
          <!--for all games-->
          <artifactId>HiRezAPI-core</artifactId> 
          <!--for paladins-->
          <!--<artifactId>HiRezAPI-paladins</artifactId>-->
          <!--for smite-->
          <!--<artifactId>HiRezAPI-smite</artifactId>-->
          <version>2.1.0</version>
     </dependency>
</dependencies>
```
#### Gradle
Add to `build.gradle` build.
```groovy
repositories {
  jcenter()
}

dependencies {
    // for all games
  compile "com.github.stachu540:HiRezAPI-core:2.1.0"
    // for paladins
//  compile "com.github.stachu540:HiRezAPI-paladins:2.1.0"
    // for smite
//  compile "com.github.stachu540:HiRezAPI-smite:2.1.0"
}
```

For starting using script check [Wiki page](https://github.com/stachu540/HiRezAPI/wiki).

## Question and suggestions
Any more questions or some suggestions changes are welcome. Don't be shy, just open your [Issues](https://github.com/stachu540/HiRezAPI/issues) if you have some problem. [Pull Request's](https://github.com/stachu540/HiRezAPI/pulls) and any other contribution are welcome.
