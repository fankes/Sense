# Sense
Sense is a efficient chip for Activity.
为你的项目集成一个高效可复用的 Activity 实例片段。
# What's this
Sense 是一个运行在 Activity 中的高效实例片段，它省去了为了因为一个界面而去 AndroidManifests.xml 注册一个 Activity 的烦恼，如果你的项目并未使用一个 Activity+多个 Fragment 的组件方式又想快速实现界面构建，那 Sense 无疑是你一个好的选择。
# Get started
Clone 仓库，将 sense 依赖到你的项目中，只需要这样：
Kotlin
```
class YourSense : Sense() {

    override fun doOnCreate(savedInstanceState: Bundle?) {
        super.doOnCreate(savedInstanceState)
        addView(R.layout.your_xml_file)
    }
}
```
即可创建一个简单的 Sense，我们要启动它就可以这样在你的 Activity 中去调用它：
Kotlin
```
startSense(YourSense::class.java)
```
如果需要传值到另一个 Sense，我们只需要再加一个参数：
Kotlin
```
startSense(YourSense::class.java, Bundle().apply { putString("simpleData", "something there") })
```
实现起来是不是非常简单？再也不需要去写烦人的 AndroidManifests.xml 了，赶紧试试吧。
# Demo
项目提供了 Demo，可参考 app 中的代码来实现一个简单的 Sense 界面。
