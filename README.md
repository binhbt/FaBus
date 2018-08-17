
```sh
//All project
allprojects {
    repositories {
        google()
        maven { url "https://jitpack.io" }
    }
}
//App project
implementation 'com.github.binhbt:FaBus:$version'
```
