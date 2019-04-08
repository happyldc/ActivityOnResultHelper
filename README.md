#  ActivityOnResultHelper
Android Activity OnResult 工具类
## 使用
### 添加依赖
 1.在Project下的gradle中添加添加JitPack仓库
 

    allprojects {
        repositories {
          //...其他仓库
            maven { url 'https://www.jitpack.io' }
        }
    }
 2.在项目中添加OnResultHelper的依赖,其中XYZ表示版本号 如 1.0.0
 

    dependencies {
            implementation 'com.github.happyldc:OnResultHelper:X.Y.Z'
    }
### 调用
   
在代码中调用  ActivityOnResultHelper.startForResult()即可。
 * 不需要手动传入requestCode,  ActivityOnResultHelper 会自动生成
 * WrapOnResultCallback 根据 Activity.RESULT 进行了处理，RESULT_OK、RESULT_CANCELED会回调 onResultOk()、 onResultCancel()
 ```java
      ActivityOnResultHelper
                .with(this)
                .target(TargetActivity.class)//跳转的页面
                .putExtra("test", "testRequest")//携带参数
                .requestCode(100)//请求码
                .startForResult(new WrapOnResultCallback() {
                    @Override
                    public void onResultOk(int requestCode, Intent data) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("requestCode=" + requestCode + "\t");
                        sb.append("intent Data (key test)=" + data.getStringExtra("test"));
                        Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                    }

                });
```
 