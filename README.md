# AndroidGeneral
Android Library for general functions

[![](https://jitpack.io/v/mathiazhagan01/AndroidGeneral.svg)](https://jitpack.io/#mathiazhagan01/AndroidGeneral)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AndroidGeneral-blue.svg?style=flat-square)](https://android-arsenal.com/details/1/5979)
[![Codix](https://codix.io/gh/badge/mathiazhagan01/AndroidGeneral)](https://codix.io/gh/repo/mathiazhagan01/AndroidGeneral)

If you like the library, please rate us on <a href="https://codix.io/gh/repo/mathiazhagan01/AndroidGeneral">codix.io!</a>

### Gradle

#### Step 1. Add the JitPack repository to your build file
  Add it in your root build.gradle at the end of repositories:
  
``` gradle
allprojects {
	repositories {
		// ...
		maven { url "https://jitpack.io" }
	}
}
```

#### Step 2. Add the dependency

``` gradle
dependencies {
	compile 'com.github.mathiazhagan01:AndroidGeneral:v1.0'
}
```	  

### Maven

#### Step 1. Add the JitPack repository to your build file

``` xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```  

#### Step 2. Add the dependency

``` xml	
<dependency>
	<groupId>com.github.mathiazhagan01</groupId>
	<artifactId>ProminentColor</artifactId>
	<version>V1.0</version>
</dependency>
```
### Example

``` java
GeneralFunctions generalFunctions = GeneralFunctions.getInstance();
generalFunctions.openBrowser(context, ConstantVariables.URL);
```

### Functions

	1. Save, Read, Remove shared preference functions.
	2. Fragment operations.
	3. Directory, file operations.
	4. View, Share files functions.

### LICENSE

	AndroidGeneral library for Android
	Copyright (c) 2017 Mathiazhagan Dinesh (http://github.com/mathiazhagan01).

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
