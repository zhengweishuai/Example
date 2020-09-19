/**
 * author : zhengweishuai
 * date : 2020/9/18 0018.
 * e-mail : zhengws@chinacarbon-al.com
 * description ：依赖库
 */
object LibDeps {

    //检测内存泄露
    const val leakcanary = "com.squareup.leakcanary:leakcanary-support-fragment:1.6.3"
    const val debugLeakcanary = "com.squareup.leakcanary:leakcanary-android:2.2"
    const val releaseLeakcanary = "com.squareup.leakcanary:leakcanary-android-no-op:1.6.3"

    //kt
    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${VersionDeps.kotlinVersion}"

    //banner
    const val banner = "com.youth.banner:banner:1.4.10"

    //协调布局
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:1.1.3"

    //导航插件
    const val navigationFragmentKts = "androidx.navigation:navigation-fragment-ktx:${VersionDeps.navigation}"
    const val navigationUiKts = "androidx.navigation:navigation-ui-ktx:${VersionDeps.navigation}"

    //屏幕适配
    const val autosize = "me.jessyan:autosize:1.2.1"

    //appcompat
    const val appcompat = "androidx.appcompat:appcompat:1.1.0"

    //图片加载
    const val glide = "com.github.bumptech.glide:glide:4.11.0"

    //日志打印
    const val logger = "com.orhanobut:logger:2.1.1"

    //网络请求
    const val retrofit = "com.squareup.retrofit2:retrofit:2.8.1"
    const val rxjava = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0"

    //gson解析
    const val gson = "com.squareup.retrofit2:converter-gson:2.7.2"

    //RxJava生命周期感知
    const val rxlifecycle = "com.trello.rxlifecycle2:rxlifecycle-components:2.1.0"

    //lifecycle
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:2.2.0"

    //协程支持
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:2.2.0"

    // liveData支持
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"

    // viewModel支持
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"

    //协程
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.1"
    const val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

    // 基础依赖包，必须要依赖
    const val immersionbar = "com.gyf.immersionbar:immersionbar:3.0.0"

    // kotlin扩展（可选）
    const val immersionbarKtx = "com.gyf.immersionbar:immersionbar-ktx:3.0.0"

    //刷新加载
    const val refresh = "com.scwang.smart:refresh-layout-kernel:2.0.1"
    const val refreshFooter = "com.scwang.smart:refresh-footer-classics:2.0.1"
    const val refreshHeader = "com.scwang.smart:refresh-header-material:2.0.1"

    //弹窗
    const val xpopup = "com.lxj:xpopup:1.8.10"

    //腾讯开放ui
    const val qmui = "com.qmuiteam:qmui:2.0.0-alpha10"

    //黄油到
    const val butterknife = "com.jakewharton:butterknife:${VersionDeps.butterknife}"
}