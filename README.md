# ioc
简单的ioc注解框架
### 如何使用
```java
//第一步
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```


```java
//第二步
	dependencies {
	        compile 'com.github.livesun:ioc:V1.0'
	}

```

```java

//查找属性
  @ViewId(R.id.left_iv)
 private ImageView imageView;

//在Activity的onCreat方法中调用

 protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ViewUtils.init(this);
    }
 
 //点击事件，第一种方式无参
 
  @OnClick(R.id.left_iv)
    public void test(){
        
    }
    
     //点击事件，第二种方式有参
    @OnClick({R.id.left_iv,R.id.left_tv})
    public void test(View v){
        switch (v.getId()){
            case R.id.left_iv:
                
                break;
            
            case R.id.left_tv:
                
                break;
        }
    }
    
```

博客地址：
