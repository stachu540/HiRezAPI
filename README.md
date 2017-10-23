[![Build Status](https://travis-ci.org/stachu540/HiRezAPI.svg)](https://travis-ci.org/stachu540/HiRezAPI)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/ee16a92996a3425d87403780aa18f316)](https://www.codacy.com/app/stachu540/HiRezAPI?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=stachu540/HiRezAPI&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/ee16a92996a3425d87403780aa18f316)](https://www.codacy.com/app/stachu540/HiRezAPI?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=stachu540/HiRezAPI&amp;utm_campaign=Badge_Coverage)
[![Javadocs](https://img.shields.io/badge/Javadoc-v2.0.1-brightgreen.svg)](https://jitpack.io/com/github/stachu540/HiRezAPI/v2.0.1/javadoc/)
[![Release](https://jitpack.io/v/stachu540/HiRezAPI.svg)](https://jitpack.io/#stachu540/HiRezAPI)

# HiRezAPI

Java-Based API Wrapper for Hi-Rez Studios games. Currently supports:
 * [Smite](https://smitegame.com/)
 * [Paladins](https://paladinsgame.com/)
 
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
          <version>2.0.1</version>
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
  compile "com.github.stachu540:HiRezAPI:2.0.1"
}
...
```

For starting using script check [Wiki page](https://github.com/stachu540/HiRezAPI/wiki/Example-Usage).

## Question and suggestions
Any more questions or some suggestions changes are welcome. Don't be shy, just open your [Issues](https://github.com/stachu540/HiRezAPI/issues) if you have some problem. [Pull Request's](https://github.com/stachu540/HiRezAPI/pulls) and any other contribution are welcome.
