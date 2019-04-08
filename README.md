#  ActivityOnResultHelper
Android Activity OnResult ������
## ʹ��
### �������
 1.��Project�µ�gradle��������JitPack�ֿ�
 

    allprojects {
        repositories {
          //...�����ֿ�
            maven { url 'https://www.jitpack.io' }
        }
    }
 2.����Ŀ�����OnResultHelper������,����XYZ��ʾ�汾�� �� 1.0.0
 

    dependencies {
            implementation 'com.github.happyldc:OnResultHelper:X.Y.Z'
    }
### ����
   
�ڴ����е���  ActivityOnResultHelper.startForResult()���ɡ�
 * ����Ҫ�ֶ�����requestCode,  ActivityOnResultHelper ���Զ�����
 * WrapOnResultCallback ���� Activity.RESULT �����˴���RESULT_OK��RESULT_CANCELED��ص� onResultOk()�� onResultCancel()
 ```java
      ActivityOnResultHelper
                .with(this)
                .target(TargetActivity.class)//��ת��ҳ��
                .putExtra("test", "testRequest")//Я������
                .requestCode(100)//������
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
 