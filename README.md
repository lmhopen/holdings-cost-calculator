# 持仓成本计算

纯前端单文件小工具：输入多次买入的价格和金额，自动算出摊薄后的平均持仓成本；可选填当前/目标价格，计算持仓市值和浮盈浮亏。数据自动保存在浏览器本地（localStorage），无需联网、无需后端。

比特币、指数基金、股票等任何按价格×金额买入的品种都适用。

## 在线使用

https://lmhopen.github.io/holdings-cost-calculator/

或直接下载 `index.html` 用浏览器打开。

## 安卓 APK

下载 [`holdings-calc.apk`](holdings-calc.apk)，传到手机安装即可（首次需允许「安装未知来源应用」）。

- 支持 Android 5.0+
- debug 签名，仅供自用，无法上架应用商店
- 包名 `com.example.holdings`，不申请任何权限

## 功能

- 任意添加 / 删除补仓行
- 摊薄后平均成本（最后成本价）
- 持仓价值、浮盈浮亏、收益率
- 输入自动保存，可一键清空

## 自己重新打包 APK

`apk-build/` 里是最小 WebView 壳工程源码（`AndroidManifest.xml` + `MainActivity.java`），用 Android SDK build-tools 直接打包，无需 Gradle：

```bash
# 伪命令流程，工具位于 $ANDROID_HOME/build-tools/<ver>/
aapt2 compile --dir apk-build/res -o res.flat.zip
aapt2 link -o app.unsigned.apk -I $ANDROID_HOME/platforms/android-33/android.jar \
  --manifest apk-build/AndroidManifest.xml -A assets -R res.flat.zip \
  --min-sdk-version 21 --target-sdk-version 33
# 将 classes.dex 加入 apk 后：
zipalign -f 4 app.unsigned.apk app.aligned.apk
apksigner sign --ks debug.keystore --out holdings-calc.apk app.aligned.apk
```

改完页面后，把新的 html 复制为 `assets/index.html` 再重新打包。
