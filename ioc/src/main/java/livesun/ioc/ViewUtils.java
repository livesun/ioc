package livesun.ioc;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 29028 on 2017/5/17.
 */

public class ViewUtils {

    public static void init(Activity activity){
        init(new ViewFinder(activity),activity);
    }

    public static void init(View view){
        init(new ViewFinder(view),view);
    }

    public static void init(View view,Object o){
        init(new ViewFinder(view),o);
    }

    /**
     * 实现共同
     * @param finder
     * @param o
     */
    private static void init(ViewFinder finder,Object o){
        //事件监听
        initEvent(finder,o);
        //属性监听
        initField(finder,o);
    }

    private static void initField(ViewFinder finder, Object o) {
        Class<?> aClass = o.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            ViewId annotation = field.getAnnotation(ViewId.class);
            if(annotation!=null){
                int viewId = annotation.value();
                View view = finder.findViewById(viewId);
                if(view!=null){
                    try {
                        field.set(o,view);//设置属性
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void initEvent(ViewFinder finder, Object o) {
        Class<?> aClass = o.getClass();
        Method[] methods = aClass.getDeclaredMethods();

        for (Method method : methods) {
            method.setAccessible(true);
            OnClick annotation = method.getAnnotation(OnClick.class);
            if(annotation!=null){
                int[] values = annotation.value();
                for (int value : values) {
                    View view = finder.findViewById(value);
                    if(view!=null){
                        //点击时间
                        view.setOnClickListener(new DeclaredClickListener(method,o));
                    }
                }
            }
        }
    }

    public static class DeclaredClickListener implements View.OnClickListener{

        private final Method method;
        private final Object o;

        public DeclaredClickListener(Method method, Object o) {

            this.method = method;
            this.o = o;
        }

        @Override
        public void onClick(View v) {
            try {
                method.invoke(o,v);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    method.invoke(o,new Object[]{});//不传值走这个
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
