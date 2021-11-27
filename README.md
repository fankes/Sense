# Sense
![Eclipse Marketplace](https://img.shields.io/badge/license-Apache2.0-blue)
![Eclipse Marketplace](https://img.shields.io/badge/version-v1.1-green)<br/><br/>
Sense is a efficient chip for Activity.<br/><br/>
为你的项目集成一个高效可复用的 Activity 实例片段。
# What's this
Sense 是一个运行在 Activity 中的高效实例片段，它省去了为了因为一个界面而去 AndroidManifests.xml 注册一个 Activity 的烦恼，如果你的项目并未使用一个 Activity+多个 Fragment 的组件方式又想快速实现界面构建，那 Sense 无疑是你一个好的选择。
# Get started
下载最新版本 AAR 依赖包导入到你的项目中 <a href='https://github.com/fankes/Sense/releases'>![Eclipse Marketplace](https://img.shields.io/badge/download-v1.1-green)</a><br/>
#### Sense 的使用非常简单，你只需要按照下面的方法开始创建你的第一个类
```kotlin
class YourSense : Sense() {

    override fun doOnCreate(savedInstanceState: Bundle?) {
        super.doOnCreate(savedInstanceState)
        addView(R.layout.your_xml_file)
    }
}
```
我们要启动它就可以这样在你的 Activity 中去调用它：<br/>
#### 第一种方法(推荐)
```kotlin
startSense<YourSense>()
```
#### 第二种方法
```kotlin
startSense(YourSense::class.java)
```
#### Java 使用方法
```java
startSense(YourSense.class);
```
#### 如果需要传值到另一个 Sense，我们只需要再加一个参数
```kotlin
startSense<YourSense>(Bundle().apply { putString("simpleData", "something there") })
```
#### 当然了，你也可以使用第二种方法
```kotlin
startSense(YourSense::class.java, Bundle().apply { putString("simpleData", "something there") })
```
#### Java 使用方法
```java
Bundle bundle = new Bundle();
bundle.putString("simpleData", "something there");
startSense(YourSense.class, bundle);
```
实现起来是不是非常简单？再也不需要去写烦人的 AndroidManifests.xml 了，赶紧试试吧。
# Demo
项目提供了 Demo，可参考 app 中的代码来实现一个简单的 Sense 界面。
