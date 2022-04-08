# Sense

![Eclipse Marketplace](https://img.shields.io/badge/license-Apache2.0-blue)
![Eclipse Marketplace](https://img.shields.io/badge/version-v1.1-green)<br/><br/>
Sense is a efficient chip for Activity.

为你的项目集成一个高效可复用的 Activity 实例片段。

## What's this

<strong>Sense</strong> 是一个依赖 <strong>Activity</strong> 运行的高效实例片段，它省去了为了因为一个界面而去 <strong>
AndroidManifests.xml</strong> 注册一个 <strong>Activity</strong> 的烦恼，如果你的项目并未使用一个
<strong>Activity+多个 Fragment</strong> 的组件方式又想快速实现界面构建，那 <strong>Sense</strong> 无疑是你一个好的选择。

## Get started

- 下载最新版本 AAR

- 依赖包导入到你的项目中 <a href='https://github.com/fankes/Sense/releases'>![Eclipse Marketplace](https://img.shields.io/badge/download-v1.1-green)</a>

- Sense 的使用非常简单，你只需要按照下面的方法开始创建你的第一个类

```kotlin
class YourSense : Sense() {

    override fun doOnCreate(savedInstanceState: Bundle?) {
        super.doOnCreate(savedInstanceState)
        addView(R.layout.your_xml_file)
    }
}
```

- 我们要启动它就可以这样在你的 Activity 中去调用它<br/>

- 第一种方法(推荐)

```kotlin
startSense<YourSense>()
```

- 第二种方法

```kotlin
startSense(YourSense::class.java)
```

- Java 使用方法

```java
startSense(YourSense.class);
```

- 如果需要传值到另一个 Sense，我们只需要再加一个参数

```kotlin
startSense<YourSense>(Bundle().apply { putString("simpleData", "something there") })
```

- 当然了，你也可以使用第二种方法

```kotlin
startSense(YourSense::class.java, Bundle().apply { putString("simpleData", "something there") })
```

- Java 使用方法

```java
Bundle bundle=new Bundle();
        bundle.putString("simpleData","something there");
        startSense(YourSense.class,bundle);
```

实现起来是不是非常简单？再也不需要去写烦人的 <strong>AndroidManifests.xml</strong> 了，赶紧试试吧。

## Custom

### 自定义 Sense 容器

- 你可以通过继承 SenseActivity 来自定义容器

```kotlin
class YourCustomSenseActivity : SenseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 这里不可以再调用 setContentView 方法
    }
}
```

- 然后通过下面的方法设置你的自定义容器

```kotlin
setCustomSenseContainerActivity<YourCustomSenseActivity>()
```

- 最后不要忘记在 AndroidManifests.xml 中注册你自定义的容器

```xml

<activity android:name="xxx.YourCustomSenseActivity" android:exported="false" />
```

## Demo

项目提供了 Demo，可参考 app 中的代码来实现一个简单的 Sense 界面。

## License

- [APACHE-2.0](https://www.apache.org/licenses/LICENSE-2.0)

```
Copyright (c) 2021. HighCapable

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```