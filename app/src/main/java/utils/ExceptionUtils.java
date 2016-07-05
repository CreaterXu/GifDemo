package utils;

import android.content.Context;
import android.widget.Toast;

/**异常处理工具类    处理全局异常
 * Created by Administrator on 2016/7/5.
 */
public class ExceptionUtils implements Thread.UncaughtExceptionHandler{
    //---------------单例模式获取对象
    private Context context;
    private ExceptionUtils(){}
    private static ExceptionUtils instance;
    public static ExceptionUtils getInstance(){
        if(instance==null){
            synchronized (ExceptionUtils.class){
                instance=new ExceptionUtils();
            }
        }
        return instance;
    }

    private Thread.UncaughtExceptionHandler carshHandler;

    /**
     * 初始化
     * @param context
     * */
    public void init(Context context){
        this.context=context;
        Thread.setDefaultUncaughtExceptionHandler(this);
        carshHandler=Thread.getDefaultUncaughtExceptionHandler();
    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (carshHandler!=null&&!handleException(ex))
            carshHandler.uncaughtException(thread,ex);
        else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 异常处理方法
     * @param ex
     * */
    private boolean handleException(final Throwable ex){
        Toast.makeText(context,"chux",Toast.LENGTH_LONG).show();
        return true;
    }
}
